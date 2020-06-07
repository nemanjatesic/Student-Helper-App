package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.reactivex.Completable
import io.reactivex.Maybe
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.KorisnikDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.KorisnikEntity
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.extensions.getKorisnik
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities.LoginActivity
import timber.log.Timber

class KorisnikRepositoryImpl(
    private val korisnikDao: KorisnikDao,
    private val sharedPreferences: SharedPreferences
): KorisnikRepository {

    override fun insert(korisnik: Korisnik): Completable {
        return korisnikDao
            .insert(KorisnikEntity(korisnik.id,korisnik.username,korisnik.pin))
    }

    override fun getKorisnikByUsername(username: String): Maybe<Korisnik> {
        return korisnikDao
            .getKorisnikByUsername(username)
            .map {
                Korisnik(it.id, it.username, it.pin)
            }
    }

    override fun getKorisnikById(id: Long): Maybe<Korisnik> {
        return korisnikDao
            .getKorisnikById(id)
            .map {
                Korisnik(it.id, it.username, it.pin)
            }
    }

    override fun verify(username: String, pin: String): Maybe<Korisnik> {
        return korisnikDao
            .verify(username, pin)
            .map {
                Korisnik(it.id, it.username, it.pin)
            }
    }

    override fun getUlogovaniId(): Maybe<Long> {
        val korisnik = sharedPreferences.getKorisnik()
        return if (korisnik == null) {
            Maybe.just(-1)
        }else {
            Maybe.just(korisnik.id)
        }
    }

    override fun setUlogovani(korisnik: Korisnik): Completable {
        val editor = sharedPreferences.edit()
        editor.putBoolean(LoginActivity.LOGGED_IN_KEY, true)
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Korisnik> = moshi.adapter(Korisnik::class.java)
        editor.putString(LoginActivity.USER, adapter.toJson(korisnik).toString())
        Timber.e(adapter.toJson(korisnik).toString())
        editor.apply()
        return Completable.complete()
    }


}