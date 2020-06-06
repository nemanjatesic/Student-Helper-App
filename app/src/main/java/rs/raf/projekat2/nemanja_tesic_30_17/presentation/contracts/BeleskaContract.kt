package rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.*

interface BeleskaContract {

    interface ViewModel {
        val beleske: LiveData<List<Beleska>>

        val beleskeState: LiveData<BeleskeState>
        val addDone: LiveData<AddBeleskaState>
        val deleteDone: LiveData<DeleteBeleskaState>
        val editDone: LiveData<EditBeleskaState>

        fun insert(beleska: Beleska)
        fun update(beleskaNaslov: String, beleskaSadrzaj: String, arhivirana: Int, id: Long)
        fun delete(idBeleske: Long)
        fun filter(beleskaNaslov: String, arhivirana: Int, id: Long)
    }

}