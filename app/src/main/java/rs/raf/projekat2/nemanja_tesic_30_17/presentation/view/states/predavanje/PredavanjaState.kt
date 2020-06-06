package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.predavanje

import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje

sealed class PredavanjaState {
    object Loading: PredavanjaState()
    object DataFetched: PredavanjaState()
    data class Success(val predavanja: List<Predavanje>): PredavanjaState()
    data class Error(val message: String): PredavanjaState()
}