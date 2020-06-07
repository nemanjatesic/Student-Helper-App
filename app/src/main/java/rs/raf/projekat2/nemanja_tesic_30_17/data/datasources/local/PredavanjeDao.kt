package rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.PredavanjeEntity

@Dao
abstract class PredavanjeDao {

    @Query("SELECT * FROM predavanja")
    abstract fun getAll(): Observable<List<PredavanjeEntity>>

    //@Query("SELECT * FROM predavanja WHERE (grupe LIKE '%' || :grupa || '%' OR :grupa LIKE 'Sve') AND (dan LIKE :dan OR :dan LIKE 'Svi') AND (profesor LIKE :profesor || '%' OR predmet LIKE :predmet || '%')")
    @Query("SELECT * FROM predavanja WHERE (predmet LIKE :predmet || '%' OR profesor LIKE :profesor || '%') AND (dan LIKE :dan OR :dan LIKE 'Svi') AND (grupe LIKE '%' || :grupa || '%' OR :grupa LIKE 'Sve')")
    abstract fun filterAll(grupa: String, dan: String, profesor: String, predmet: String): Observable<List<PredavanjeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: PredavanjeEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<PredavanjeEntity>): Completable

    @Query("DELETE FROM predavanja")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<PredavanjeEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}