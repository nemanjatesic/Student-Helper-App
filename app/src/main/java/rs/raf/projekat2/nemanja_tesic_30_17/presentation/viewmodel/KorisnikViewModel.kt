package rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel

import androidx.lifecycle.LiveData
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

    override fun getKorisnikByUsername(username: String) {
        TODO("Not yet implemented")
    }

    override fun getKorisnikById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun verify(username: String, pin: String) {
        TODO("Not yet implemented")
    }

    override fun getKorisnikWithBeleskeByKorisnikId(id: Long) {
        TODO("Not yet implemented")
    }

}