package rs.raf.projekat2.nemanja_tesic_30_17.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.BeleskaDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.KorisnikDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.PredavanjeDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.BeleskaEntity
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje

@Database(
    entities = [BeleskaEntity::class, Predavanje::class, Korisnik::class],
    version = 2,
    exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun getBeleskaDao(): BeleskaDao
    abstract fun getKorisnikDao(): KorisnikDao
    abstract fun getPredavanjeDao(): PredavanjeDao
}