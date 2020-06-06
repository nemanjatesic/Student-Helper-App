package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska

sealed class EditBeleskaState {
    object Success: EditBeleskaState()
    data class Error(val message: String): EditBeleskaState()
}