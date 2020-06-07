package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Korisnik (
    val id: Long,
    val username: String,
    val pin: String
) : Parcelable