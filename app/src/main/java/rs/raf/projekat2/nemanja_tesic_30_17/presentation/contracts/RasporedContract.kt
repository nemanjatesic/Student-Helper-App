package rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.predavanje.PredavanjaState

interface RasporedContract {

    interface ViewModel {
        val predavanja: LiveData<List<Predavanje>>
        val predavanjaFiltered: LiveData<List<Predavanje>>

        val predavanjaState: LiveData<PredavanjaState>

        fun getPredavanja()
        fun filterPredavanja(grupa: String, dan: String, profesor: String, predmet: String)
        fun fetchAllPredavanja()
    }
}