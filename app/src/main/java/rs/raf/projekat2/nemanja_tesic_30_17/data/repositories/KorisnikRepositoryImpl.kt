package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.KorisnikDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik

class KorisnikRepositoryImpl(private val korisnikDao: KorisnikDao): KorisnikRepository {

    override fun insert(korisnik: Korisnik): Completable {
        return korisnikDao.insert(korisnik)
    }

    override fun getKorisnikByUsername(username: String): Maybe<Korisnik> {
        return korisnikDao.getKorisnikByUsername(username)
    }

    override fun getKorisnikById(id: Long): Maybe<Korisnik> {
        return korisnikDao.getKorisnikById(id)
    }

    override fun verify(username: String, pin: String): Maybe<Korisnik> {
        return korisnikDao.verify(username, pin)
    }
}