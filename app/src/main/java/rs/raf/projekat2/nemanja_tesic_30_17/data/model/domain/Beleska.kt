package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beleska (
    val id: Int,
    val naslov: String,
    val sadrzaj: String
): Parcelable
