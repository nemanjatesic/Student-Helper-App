package rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain

import androidx.room.Embedded
import androidx.room.Relation

data class KorisnikWithBeleske(
    @Embedded val korisnik: Korisnik,
    @Relation(
        parentColumn = "id",
        entityColumn = "korisnik_id"
    )
    val beleske: List<Beleska>?
)