package rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje

interface RasporedContract {

    interface ViewModel {
        val predavanja: LiveData<List<Predavanje>>
        val predavanjaFiltered: LiveData<List<Predavanje>>

        fun getPredavanja()
        fun getFilteredPredavanja(grupa: String, dan: String, profesor: String, predmet: String)
    }
}