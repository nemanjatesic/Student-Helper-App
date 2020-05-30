package rs.raf.projekat2.nemanja_tesic_30_17.data.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PredavanjeResponse(
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
)