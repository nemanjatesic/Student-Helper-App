package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_statistika.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.custom.ChartColumn
import rs.raf.projekat2.nemanja_tesic_30_17.extensions.getKorisnik
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.BeleskeState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class StatistikaFragment : Fragment(R.layout.fragment_statistika) {
    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()
    private val sharedPreferences: SharedPreferences by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        beleskaViewModel.beleskeState.observe(viewLifecycleOwner, Observer {
            Timber.e("USAO OVDE")
            when(it) {
                is BeleskeState.Success -> {
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    val dateList: MutableList<String> = mutableListOf()
                    val cal = Calendar.getInstance()

                    for (i in 0..4) {
                        cal.time = Date()
                        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - i)
                        dateList.add(sdf.format(cal.time))
                    }
                    val wrapperList = it.beleske.filter {
                        dateList.contains(it.datumKreiranja)
                    }
                    Timber.e("Ovo je listaaa $wrapperList")
                    val mapa: HashMap<String, ChartColumn> = hashMapOf()

                    wrapperList.forEach {
                        val dateStr = it.datumKreiranja
                        if (mapa.get(dateStr) == null) {
                            mapa.put(dateStr, ChartColumn(dateStr, 1))
                        }else {
                            val nesto: ChartColumn = mapa.get(dateStr)!!
                            nesto.count += 1
                            mapa.put(dateStr, nesto)
                        }
                    }

                    val listOfColumns: MutableList<ChartColumn> = mapa.values.toMutableList()
                    dateList.forEach {
                        if (!listOfColumns.contains(ChartColumn(it,0))) {
                            listOfColumns.add(ChartColumn(it,0))
                        }
                    }
                    listOfColumns.sort()

                    Timber.e("Ovo je listaaa kolonaa $listOfColumns")

                    prviDole.setText(listOfColumns[0].dateCreated)
                    drugiDole.setText(listOfColumns[1].dateCreated)
                    treciDole.setText(listOfColumns[2].dateCreated)
                    cetvrtiDole.setText(listOfColumns[3].dateCreated)
                    petiDole.setText(listOfColumns[4].dateCreated)

                    prviGore.setText(listOfColumns[0].count.toString())
                    drugiGore.setText(listOfColumns[1].count.toString())
                    treciGore.setText(listOfColumns[2].count.toString())
                    cetvrtiGore.setText(listOfColumns[3].count.toString())
                    petiGore.setText(listOfColumns[4].count.toString())

                    chartView.chartColumns.clear()
                    chartView.chartColumns.addAll(listOfColumns)
                    chartView.invalidate()
                }
                is BeleskeState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
            }
        })

        val korisnik = sharedPreferences.getKorisnik()
        beleskaViewModel.filter("",1, korisnik!!.id)
    }


}