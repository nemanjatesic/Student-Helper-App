package rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik

interface KorisnikContract {

    interface ViewModel {
        val ulogovani: LiveData<Korisnik>

        fun insert(korisnik: Korisnik)
        fun verify(username: String, pin: String)
    }
}