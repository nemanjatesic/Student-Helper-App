package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beleska (
    val id: Long,
    val naslov: String,
    val sadrzaj: String,
    val arhivirana: Int,
    val datumKreiranja: String,
    val korisnikId: Long
): Parcelable
