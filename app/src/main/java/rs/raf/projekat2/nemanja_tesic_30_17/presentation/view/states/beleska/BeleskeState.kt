package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska

import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska

sealed class BeleskeState {
    data class Success(val beleske: List<Beleska>): BeleskeState()
    data class Error(val message: String): BeleskeState()
}