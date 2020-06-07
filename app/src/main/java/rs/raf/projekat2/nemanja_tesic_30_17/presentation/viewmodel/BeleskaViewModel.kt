package rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.BeleskaRepository
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.BeleskeState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BeleskaViewModel(
    private val beleskaRepository: BeleskaRepository
) : ViewModel(), BeleskaContract.ViewModel {

    override val beleskeState: MutableLiveData<BeleskeState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    private val subscriptions = CompositeDisposable()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                val filter = it.split("|||")
                beleskaRepository
                    .filter(filter[0], filter[1].toInt(), filter[2].toLong())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Success(it)
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insert(beleska: Beleska) {
        val subscription = beleskaRepository
            .insert(beleska)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Add("Added")
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while adding beleska")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun update(beleskaNaslov: String, beleskaSadrzaj: String, arhivirana: Int, id: Long) {
        val subscription = beleskaRepository
            .update(beleskaNaslov, beleskaSadrzaj, arhivirana, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Edit("Edited")
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while updating beleska")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(idBeleske: Long) {
        val subscription = beleskaRepository
            .delete(idBeleske)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Delete("Deleted")
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while deleting beleska")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getChartData(id: Long) {
        val subscription = beleskaRepository
            .getCharData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.LoadedChartData(it)
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while getting chart data")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun filter(beleskaNaslov: String, arhivirana: Int, id: Long) {
        publishSubject.onNext("$beleskaNaslov|||$arhivirana|||$id")
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}