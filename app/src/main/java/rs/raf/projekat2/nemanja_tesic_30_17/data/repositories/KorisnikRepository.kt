package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik

interface KorisnikRepository {

    fun insert(korisnik: Korisnik): Completable

    fun getKorisnikByUsername(username: String): Maybe<Korisnik>

    fun getKorisnikById(id: Long): Maybe<Korisnik>

    fun verify(username: String, pin: String): Maybe<Korisnik>

}