package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.BeleskaDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.BeleskaEntity
import java.text.SimpleDateFormat
import java.util.*

class BeleskaRepositoryImpl(private val beleskaDao: BeleskaDao) : BeleskaRepository{

    override fun insert(beleska: Beleska): Completable {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val beleskaEntity =
            BeleskaEntity(
                beleska.id,
                beleska.naslov,
                beleska.sadrzaj,
                beleska.arhivirana,
                sdf.format(Date()),
                beleska.korisnikId
            )
        return beleskaDao.insert(beleskaEntity)
    }

    override fun getAllByKorisnikId(id: Long): Observable<List<Beleska>> {
        return beleskaDao
            .getAllByKorisnikId(id)
            .map {
                it.map {beleska ->
                    Beleska(beleska.id, beleska.naslov, beleska.sadrzaj, beleska.arhivirana, beleska.datumKreiranja, beleska.korisnikId)
                }
            }
    }

    override fun update(beleskaNaslov: String, beleskaSadrzaj: String, arhivirana: Int, id: Long): Completable {
        return beleskaDao.update(beleskaNaslov, beleskaSadrzaj, arhivirana, id)
    }

    override fun delete(idBeleske: Long): Completable {
        return beleskaDao.delete(idBeleske)
    }

    override fun filter(beleskaNaslov: String, arhivirana: Int, id: Long): Observable<List<Beleska>> {
        return beleskaDao
            .filter(beleskaNaslov, arhivirana, id)
            .map {
                it.map {beleska ->
                    Beleska(beleska.id, beleska.naslov, beleska.sadrzaj, beleska.arhivirana, beleska.datumKreiranja, beleska.korisnikId)
                }
            }
    }

}