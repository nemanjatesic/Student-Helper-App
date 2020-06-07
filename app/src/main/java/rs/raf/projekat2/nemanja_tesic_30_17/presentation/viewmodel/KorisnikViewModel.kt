package rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.KorisnikRepository
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.KorisnikContract
import timber.log.Timber

class KorisnikViewModel(
    private val korisnikRepository: KorisnikRepository
): ViewModel(), KorisnikContract.ViewModel {

    override val ulogovani: MutableLiveData<Korisnik> = MutableLiveData()
    override val ulogovaniId: MutableLiveData<Long> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun insert(korisnik: Korisnik) {
        val subscription = korisnikRepository
            .insert(korisnik)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Koirsnik uspesno dodat")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun verify(username: String, pin: String) {
        val subscription = korisnikRepository
            .verify(username, pin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                ulogovani.value = it
            }
            .doOnError {
                Timber.e("doOnError")
            }
            .doOnComplete {
                ulogovani.value = null
            }
            .subscribe()
        subscriptions.add(subscription)
    }

    override fun getUlogovaniId() {
        val subscription = korisnikRepository
            .getUlogovaniId()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                ulogovaniId.value = it
            }
            .doOnError {
                Timber.e("doOnError")
            }
            .doOnComplete {
                ulogovaniId.value = (-1).toLong()
            }
            .subscribe()
        subscriptions.add(subscription)
    }

    override fun setUlogovani(korisnik: Korisnik) {
        val subscription = korisnikRepository
            .setUlogovani(korisnik)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Koirsnik uspesno dodat")
                },
                {
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