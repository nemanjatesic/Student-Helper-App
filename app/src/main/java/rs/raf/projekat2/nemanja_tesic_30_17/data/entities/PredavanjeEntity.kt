package rs.raf.projekat2.nemanja_tesic_30_17.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "predavanja")
data class PredavanjeEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val predmet: String,
    val tip: String,
    val profesor: String,
    val ucionica: String,
    val grupe: String,
    val dan: String,
    val vreme: String
)