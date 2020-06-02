package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska

interface BeleskaRepository {

    fun insert(beleska: Beleska): Completable

    fun getAllByKorisnikId(id: Long) : Observable<List<Beleska>>

    fun update(beleska: Beleska): Completable

    fun delete(beleska: Beleska): Completable
}