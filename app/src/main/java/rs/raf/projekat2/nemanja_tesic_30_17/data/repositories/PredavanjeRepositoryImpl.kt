package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.PredavanjeDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.remote.PredavanjaService
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.PredavanjeEntity
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Resource

class PredavanjeRepositoryImpl(
    private val localDataSource: PredavanjeDao,
    private val remoteDataSource: PredavanjaService
) : PredavanjeRepository {

    override fun getAllPredavanja(): Observable<List<Predavanje>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Predavanje(it.id,it.predmet,it.tip,it.profesor,it.ucionica,it.grupe,it.dan,it.vreme)
                }
            }
    }

    override fun getFilteredPredavanja(grupa: String, dan: String, profesor: String, predmet: String): Observable<List<Predavanje>> {
        return localDataSource
            .filterAll(grupa, dan, profesor, predmet)
            .map {
                it.map {
                    Predavanje(it.id,it.predmet,it.tip,it.profesor,it.ucionica,it.grupe,it.dan,it.vreme)
                }
            }
    }

    override fun fetchAllPredavanja(): Observable<Resource<Unit>> {
        var i = 0
        return remoteDataSource
            .getAll()
            .doOnNext {
                val lista = it.map {predavanjeResponse ->
                    val dan = when(predavanjeResponse.dan) {
                        "PON" -> "Ponedeljak"
                        "UTO" -> "Utorak"
                        "SRE" -> "Sreda"
                        "ČET" -> "Cetvrtak"
                        "PET" -> "Petak"
                        "SUB" -> "Subota"
                        "NED" -> "Nedelja"
                        else -> "Svi"
                    }
                    val predavanje = PredavanjeEntity(
                        i.toLong(),
                        predavanjeResponse.predmet,
                        predavanjeResponse.tip,
                        predavanjeResponse.nastavnik,
                        predavanjeResponse.ucionica,
                        predavanjeResponse.grupe,
                        dan,
                        predavanjeResponse.termin
                    )
                    i++
                    predavanje
                }
                localDataSource.deleteAndInsertAll(lista)
            }
            .map {
                Resource.Success(Unit)
            }
    }
}