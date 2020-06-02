package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.BeleskaDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska

class BeleskaRepositoryImpl(private val beleskaDao: BeleskaDao) : BeleskaRepository{

    override fun insert(beleska: Beleska): Completable {
        return beleskaDao.insert(beleska)
    }

    override fun getAllByKorisnikId(id: Long): Observable<List<Beleska>> {
        return beleskaDao.getAllByKorisnikId(id)
    }

    override fun update(beleska: Beleska): Completable {
        return beleskaDao.update(beleska)
    }

    override fun delete(beleska: Beleska): Completable {
        return beleskaDao.delete(beleska)
    }

}