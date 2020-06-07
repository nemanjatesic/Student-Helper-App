package rs.raf.projekat2.nemanja_tesic_30_17.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.local.BeleskaDao
import rs.raf.projekat2.nemanja_tesic_30_17.data.entities.BeleskaEntity
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.custom.ChartData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import timber.log.Timber
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

    override fun getCharData(id: Long): Observable<List<ChartData>> {
        return beleskaDao
            .getAllByKorisnikId(id)
            .map {lista ->
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val dateList: MutableList<String> = mutableListOf()
                val cal = Calendar.getInstance()

                for (i in 0..4) {
                    cal.time = Date()
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - i)
                    dateList.add(sdf.format(cal.time))
                }
                val wrapperList = lista.filter {
                    dateList.contains(it.datumKreiranja)
                }
                Timber.e("Ovo je listaaa $wrapperList")
                val mapa: HashMap<String, ChartData> = hashMapOf()

                wrapperList.forEach {
                    val dateStr = it.datumKreiranja
                    if (mapa.get(dateStr) == null) {
                        mapa.put(dateStr, ChartData(dateStr, 1))
                    }else {
                        val nesto: ChartData = mapa.get(dateStr)!!
                        nesto.count += 1
                        mapa.put(dateStr, nesto)
                    }
                }

                val listOfData: MutableList<ChartData> = mapa.values.toMutableList()
                dateList.forEach {
                    if (!listOfData.contains(ChartData(it,0))) {
                        listOfData.add(ChartData(it,0))
                    }
                }
                listOfData.sort()
                listOfData
            }
    }

}