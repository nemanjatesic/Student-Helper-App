package rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.KorisnikEntity
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik

@Dao
abstract class KorisnikDao {

    @Insert
    abstract fun insert(korisnik: KorisnikEntity): Completable

    @Query("SELECT * FROM korisnici WHERE username == :username")
    abstract fun getKorisnikByUsername(username: String): Maybe<KorisnikEntity>

    @Query("SELECT * FROM korisnici WHERE id == :id")
    abstract fun getKorisnikById(id: Long): Maybe<KorisnikEntity>

    @Query("SELECT * FROM korisnici WHERE username == :username AND pin == :pin")
    abstract fun verify(username: String, pin: String): Maybe<KorisnikEntity>

}