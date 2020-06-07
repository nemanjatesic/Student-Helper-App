package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_raspored.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.RasporedContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.adapter.PredavanjeAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.diff.PredavanjeDiffItemCallback
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.predavanje.PredavanjaState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.PredavanjeViewModel
import timber.log.Timber

class RasporedFragment : Fragment(R.layout.fragment_raspored) {

    private lateinit var predavanjeAdapter: PredavanjeAdapter

    private val predavanjeViewModel: RasporedContract.ViewModel by viewModel<PredavanjeViewModel>()
    private var selectedDan = "Svi"
    private var selectedGrupa = "Sve"

    private var prviPut = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prviPut = true
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        initObservers()
        initListeners()
        initRecycler()
    }

    private fun initListeners() {
        traziButton.setOnClickListener {
            predavanjeViewModel.filterPredavanja(selectedGrupa, selectedDan, et_profesor.text.toString(), et_profesor.text.toString())
        }
    }

    private fun initSpinners(svaPredavanja: List<Predavanje>) {
        val options = arrayOf("Svi","Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak", "Subota", "Nedelja")

        sp_dan.adapter = object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val mojView = super.getView(position, convertView, parent)
                (mojView as TextView).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
                return mojView
            }
        }

        sp_dan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Timber.e("Nista nije selektovano")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedDan = options[position]
            }
        }

        val setGrupa: HashSet<String> = hashSetOf()
        svaPredavanja.forEach {
            val predavanjeGrupe = it.grupe.split(", ")
            predavanjeGrupe.forEach { grupa ->
                setGrupa.add(grupa)
            }
        }

        val options1: MutableList<String> = setGrupa.toMutableList()
        options1.sort()
        options1.add(0, "Sve")

        sp_grupa.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options1)

        sp_grupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Timber.e("Nista nije selektovano")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedGrupa = options1[position]
            }

        }
    }

    private fun initObservers() {
        predavanjeViewModel.predavanjaState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        predavanjeViewModel.getPredavanja()

        predavanjeViewModel.fetchAllPredavanja()
    }

    private fun renderState(state: PredavanjaState) {
        when (state) {
            is PredavanjaState.Success -> {
                showLoadingState(false)
                predavanjeAdapter.submitList(state.predavanja)
                if (prviPut) {
                    prviPut = false
                    initSpinners(state.predavanja)
                }
            }
            is PredavanjaState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is PredavanjaState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is PredavanjaState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun initRecycler() {
        listRv.layoutManager = LinearLayoutManager(activity)
        predavanjeAdapter = PredavanjeAdapter(PredavanjeDiffItemCallback())
        listRv.adapter = predavanjeAdapter
    }

    private fun showLoadingState(loading: Boolean) {
        progressBar.isVisible = loading
    }
}