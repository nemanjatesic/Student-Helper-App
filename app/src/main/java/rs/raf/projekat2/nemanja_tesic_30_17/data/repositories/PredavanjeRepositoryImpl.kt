package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.PredavanjaDataSource
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje
import timber.log.Timber

class PredavanjeRepositoryImpl(private val predavanjaDataSource: PredavanjaDataSource) : PredavanjeRepository {

    override fun getAllPredavanja(): Observable<List<Predavanje>> {
        var i = 0
        return predavanjaDataSource
            .getAll()
            .map {
                val lista = mutableListOf<Predavanje>()
                it.forEach{pred ->
                    val predavanje = Predavanje(
                        i++,
                        pred.predmet,
                        pred.tip,
                        pred.nastavnik,
                        pred.ucionica,
                        pred.grupe,
                        pred.dan,
                        pred.termin
                    )
                    lista.add(predavanje)
                }
                lista
            }
        }

    override fun getFilteredPredavanja(grupa: String, dan: String, profesor: String, predmet: String): Observable<List<Predavanje>> {
        TODO("Not yet implemented")
    }
}