package rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska

interface BeleskaContract {

    interface ViewModel {
        val beleske: LiveData<List<Beleska>>

        fun getBeleskeByKorisnikId(id: Long)

        fun insert(beleska: Beleska)

        fun update(beleska: Beleska)

        fun delete(beleska: Beleska)
    }

}