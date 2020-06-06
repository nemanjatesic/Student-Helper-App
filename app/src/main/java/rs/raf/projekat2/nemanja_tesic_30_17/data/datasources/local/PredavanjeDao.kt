package rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje

@Dao
abstract class PredavanjeDao {

    @Query("SELECT * FROM predavanja")
    abstract fun getAll(): Observable<List<Predavanje>>

    //@Query("SELECT * FROM predavanja WHERE (grupe LIKE '%' || :grupa || '%' OR :grupa LIKE 'Sve') AND (dan LIKE :dan OR :dan LIKE 'Svi') AND (profesor LIKE :profesor || '%' OR predmet LIKE :predmet || '%')")
    @Query("SELECT * FROM predavanja WHERE (predmet LIKE :predmet || '%' OR profesor LIKE :profesor || '%') AND (dan LIKE :dan OR :dan LIKE 'Svi') AND (grupe LIKE '%' || :grupa || '%' OR :grupa LIKE 'Sve')")
    abstract fun filterAll(grupa: String, dan: String, profesor: String, predmet: String): Observable<List<Predavanje>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: Predavanje): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<Predavanje>): Completable

    @Query("DELETE FROM predavanja")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<Predavanje>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}