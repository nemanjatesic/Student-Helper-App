package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.custom.ChartData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska

interface BeleskaRepository {

    fun insert(beleska: Beleska): Completable

    fun getAllByKorisnikId(id: Long) : Observable<List<Beleska>>

    fun update(beleskaNaslov: String, beleskaSadrzaj: String, arhivirana: Int, id: Long): Completable

    fun delete(idBeleske: Long): Completable

    fun filter(beleskaNaslov: String, arhivirana: Int, id: Long): Observable<List<Beleska>>

    fun getCharData(id: Long) : Observable<List<ChartData>>
}