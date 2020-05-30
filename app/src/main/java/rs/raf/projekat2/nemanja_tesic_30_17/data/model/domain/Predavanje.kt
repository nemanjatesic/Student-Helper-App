package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Predavanje (
    val id: Int,
    val predmet: String,
    val tip: String,
    val profesor: String,
    val ucionica: String,
    val grupe: String,
    val dan: String,
    val vreme: String
): Parcelable