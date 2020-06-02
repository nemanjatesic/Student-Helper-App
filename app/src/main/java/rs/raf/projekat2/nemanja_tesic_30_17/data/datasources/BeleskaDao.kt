package rs.raf.projekat2.nemanja_tesic_30_17.data.datasources

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.KorisnikWithBeleske

@Dao
abstract class BeleskaDao {

    @Insert
    abstract fun insert(beleska: Beleska): Completable

    @Update
    abstract fun update(beleska: Beleska): Completable

    @Query("SELECT * FROM beleske WHERE korisnik_id == :id")
    abstract fun getAllByKorisnikId(id: Long): Observable<List<Beleska>>

    @Delete
    abstract fun delete(beleska: Beleska): Completable

}