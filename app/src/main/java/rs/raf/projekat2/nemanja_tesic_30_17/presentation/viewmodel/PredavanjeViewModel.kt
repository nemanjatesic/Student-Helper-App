package rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.PredavanjeRepository
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.RasporedContract
import timber.log.Timber

class PredavanjeViewModel (
    private val predavanjaRepository: PredavanjeRepository
): ViewModel(), RasporedContract.ViewModel {

    override val predavanja: MutableLiveData<List<Predavanje>> = MutableLiveData()
    override val predavanjaFiltered: MutableLiveData<List<Predavanje>> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun getPredavanja() {
        Timber.e("ovde sam")
        val subscription = predavanjaRepository
            .getAllPredavanja()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    predavanja.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getFilteredPredavanja(grupa: String, dan: String, profesor: String, predmet: String) {
        val subscription = predavanjaRepository
            .getFilteredPredavanja(grupa, dan, profesor, predmet)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    predavanja.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

}