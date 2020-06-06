package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
