package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_statistika.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.extensions.getKorisnik
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.KorisnikContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.BeleskeState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.KorisnikViewModel
import timber.log.Timber

class StatistikaFragment : Fragment(R.layout.fragment_statistika) {
    private var korisnikId: Long = -1
    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()
    private val korisnikViewModel: KorisnikContract.ViewModel by viewModel<KorisnikViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        beleskaViewModel.beleskeState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BeleskeState.LoadedChartData -> {
                    Timber.e("Ovo je listaaa kolonaa $it")

                    prviDole.setText(it.chartData[0].dateCreated)
                    drugiDole.setText(it.chartData[1].dateCreated)
                    treciDole.setText(it.chartData[2].dateCreated)
                    cetvrtiDole.setText(it.chartData[3].dateCreated)
                    petiDole.setText(it.chartData[4].dateCreated)

                    prviGore.setText(it.chartData[0].count.toString())
                    drugiGore.setText(it.chartData[1].count.toString())
                    treciGore.setText(it.chartData[2].count.toString())
                    cetvrtiGore.setText(it.chartData[3].count.toString())
                    petiGore.setText(it.chartData[4].count.toString())

                    chartView.chartData.clear()
                    chartView.chartData.addAll(it.chartData)
                    chartView.invalidate()
                }
            }
        })
        korisnikViewModel.ulogovaniId.observe(viewLifecycleOwner, Observer {
            korisnikId = it
            beleskaViewModel.getChartData(korisnikId)
        })
        korisnikViewModel.getUlogovaniId()
    }
}