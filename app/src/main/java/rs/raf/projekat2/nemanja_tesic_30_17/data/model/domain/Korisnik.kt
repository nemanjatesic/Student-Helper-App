package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "korisnici")
data class Korisnik (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val username: String,
    val pin: String
) : Parcelable