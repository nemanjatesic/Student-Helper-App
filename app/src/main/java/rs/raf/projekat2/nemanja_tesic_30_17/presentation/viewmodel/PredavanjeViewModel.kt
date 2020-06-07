package rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Resource
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.PredavanjeRepository
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.RasporedContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.predavanje.PredavanjaState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class PredavanjeViewModel (
    private val predavanjaRepository: PredavanjeRepository
): ViewModel(), RasporedContract.ViewModel {

    override val predavanja: MutableLiveData<List<Predavanje>> = MutableLiveData()
    override val predavanjaFiltered: MutableLiveData<List<Predavanje>> = MutableLiveData()

    override val predavanjaState: MutableLiveData<PredavanjaState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    private val subscriptions = CompositeDisposable()

    init {
        val subscription = publishSubject
            .distinctUntilChanged()
            .switchMap {
                val filter = it.split("|||")
                predavanjaRepository
                    .getFilteredPredavanja(filter[0], filter[1], filter[2], filter[3])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    predavanjaState.value = PredavanjaState.Success(it)
                },
                {
                    predavanjaState.value = PredavanjaState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getPredavanja() {
        val subscription = predavanjaRepository
            .getAllPredavanja()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    predavanjaState.value = PredavanjaState.Success(it)
                },
                {
                    predavanjaState.value = PredavanjaState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun filterPredavanja(grupa: String, dan: String, profesor: String, predmet: String) {
        publishSubject.onNext("$grupa|||$dan|||$profesor|||$predmet")
    }

    override fun fetchAllPredavanja() {
        val subscription = predavanjaRepository
            .fetchAllPredavanja()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> predavanjaState.value = PredavanjaState.Loading
                        is Resource.Success -> predavanjaState.value = PredavanjaState.DataFetched
                        is Resource.Error -> predavanjaState.value = PredavanjaState.Error("Error happened while fetching data from the server, you are looking at cached data")
                    }
                },
                {
                    predavanjaState.value = PredavanjaState.Error("Error happened while fetching data from the server, you are looking at cached data")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}