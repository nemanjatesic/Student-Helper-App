package rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.KorisnikWithBeleske

interface KorisnikContract {

    interface ViewModel {
        val ulogovani: LiveData<Korisnik>

        fun insert(korisnik: Korisnik)
        fun getKorisnikByUsername(username: String)
        fun getKorisnikById(id: Long)
        fun verify(username: String, pin: String)
        fun getKorisnikWithBeleskeByKorisnikId(id: Long)
    }
}