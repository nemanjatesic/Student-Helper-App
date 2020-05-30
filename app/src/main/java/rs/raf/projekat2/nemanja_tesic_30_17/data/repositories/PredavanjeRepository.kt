package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje

interface PredavanjeRepository {

    fun getAllPredavanja(): Observable<List<Predavanje>>

    fun getFilteredPredavanja(grupa: String, dan: String, profesor: String, predmet: String): Observable<List<Predavanje>>
}