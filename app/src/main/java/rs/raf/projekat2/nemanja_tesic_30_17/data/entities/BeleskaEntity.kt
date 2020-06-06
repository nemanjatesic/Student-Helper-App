package rs.raf.projekat2.nemanja_tesic_30_17.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beleske")
data class BeleskaEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val naslov: String,
    val sadrzaj: String,
    val arhivirana: Int,
    val datumKreiranja: String,
    @ColumnInfo(name = "korisnik_id") val korisnikId: Long
)