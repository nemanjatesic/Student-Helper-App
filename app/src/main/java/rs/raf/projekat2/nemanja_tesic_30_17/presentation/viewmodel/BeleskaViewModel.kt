package rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.BeleskaRepository
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import timber.log.Timber

class BeleskaViewModel(
    private val beleskaRepository: BeleskaRepository
) : ViewModel(), BeleskaContract.ViewModel {

    override val beleske : MutableLiveData<List<Beleska>> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

//    init {
//        for (i in 1..15) {
//            var str = ""
//            for (j in 1..30) {
//                str += "Ovo je moja recenica. "
//            }
//            val beleska =
//                Beleska(
//                    i.toLong(),
//                    "Naslov$i",
//                    str + i,
//                    0
//                )
//            beleskeList.add(beleska)
//        }
//        val listToSubmit = mutableListOf<Beleska>()
//        listToSubmit.addAll(beleskeList)
//        beleske.value = listToSubmit
//    }

    override fun getBeleskeByKorisnikId(id: Long) {
        Timber.e("getBeleskeByKorisnikId")
        val subscription = beleskaRepository
            .getAllByKorisnikId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("getBeleskeByKorisnikId + prva lambda $it")
                    beleske.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insert(beleska: Beleska) {
        Timber.e("insert")
        val subscription = beleskaRepository
            .insert(beleska)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("insert + prva lambda")
                    //beleske.value = null
                    getBeleskeByKorisnikId(beleska.korisnikId)
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun update(beleska: Beleska) {
        val subscription = beleskaRepository
            .update(beleska)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    getBeleskeByKorisnikId(beleska.korisnikId)
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(beleska: Beleska) {
        val subscription = beleskaRepository
            .delete(beleska)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    getBeleskeByKorisnikId(beleska.korisnikId)
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}