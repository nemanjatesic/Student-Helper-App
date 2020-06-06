package rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.BeleskaEntity

@Dao
abstract class BeleskaDao {

    @Insert
    abstract fun insert(beleska: BeleskaEntity): Completable

    @Update
    abstract fun update(beleska: BeleskaEntity): Completable

    @Query("UPDATE beleske SET naslov = :beleskaNaslov, sadrzaj = :beleskaSadrzaj, arhivirana = :arhivirana WHERE id == :id")
    abstract fun update(beleskaNaslov: String, beleskaSadrzaj: String, arhivirana: Int, id: Long): Completable

    @Query("SELECT * FROM beleske WHERE korisnik_id == :id")
    abstract fun getAllByKorisnikId(id: Long): Observable<List<BeleskaEntity>>

    @Query("DELETE FROM beleske WHERE id = :idBeleske")
    abstract fun delete(idBeleske: Long): Completable

    @Query("SELECT * FROM beleske WHERE naslov LIKE :beleskaNaslov || '%' AND (arhivirana == :arhivirana OR :arhivirana == 1) AND korisnik_id == :id")
    abstract fun filter(beleskaNaslov: String, arhivirana: Int, id: Long): Observable<List<BeleskaEntity>>
}