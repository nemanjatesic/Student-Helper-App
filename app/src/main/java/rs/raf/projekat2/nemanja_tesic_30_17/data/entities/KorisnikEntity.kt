package rs.raf.projekat2.nemanja_tesic_30_17.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "korisnici")
data class KorisnikEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val username: String,
    val pin: String
)