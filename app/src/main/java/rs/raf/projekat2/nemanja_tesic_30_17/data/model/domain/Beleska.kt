package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "beleske")
data class Beleska (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val naslov: String,
    val sadrzaj: String,
    @ColumnInfo(name = "korisnik_id") val korisnikId: Long
): Parcelable
