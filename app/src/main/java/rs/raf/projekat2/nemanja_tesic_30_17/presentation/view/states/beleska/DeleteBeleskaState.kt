package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska

sealed class DeleteBeleskaState {
    object Success: DeleteBeleskaState()
    data class Error(val message: String): DeleteBeleskaState()
}