package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska

sealed class AddBeleskaState {
    object Success: AddBeleskaState()
    data class Error(val message: String): AddBeleskaState()
}