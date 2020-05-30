package rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska

class BeleskaViewModel() : ViewModel() {

    private val beleske : MutableLiveData<List<Beleska>> = MutableLiveData()

    private val beleskeList: MutableList<Beleska> = mutableListOf()

    init {
        for (i in 1..15) {
            var str = ""
            for (j in 1..30) {
                str += "Ovo je moja recenica. "
            }
            val beleska =
                Beleska(
                    i,
                    "Naslov$i",
                    str + i
                )
            beleskeList.add(beleska)
        }
        val listToSubmit = mutableListOf<Beleska>()
        listToSubmit.addAll(beleskeList)
        beleske.value = listToSubmit
    }

    fun getBeleske() : LiveData<List<Beleska>> {
        return beleske
    }
}