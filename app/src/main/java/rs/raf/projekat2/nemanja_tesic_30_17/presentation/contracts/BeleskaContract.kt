package rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.BeleskeState

interface BeleskaContract {

    interface ViewModel {
        val beleskeState: LiveData<BeleskeState>

        fun insert(beleska: Beleska)
        fun update(beleskaNaslov: String, beleskaSadrzaj: String, arhivirana: Int, id: Long)
        fun delete(idBeleske: Long)
        fun filter(beleskaNaslov: String, arhivirana: Int, id: Long)
        fun getChartData(id: Long)
    }

}