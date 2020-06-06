package rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.api.PredavanjeResponse

interface PredavanjaService {

    @GET("raspored/json.php")
    fun getAll() : Observable<List<PredavanjeResponse>>

}