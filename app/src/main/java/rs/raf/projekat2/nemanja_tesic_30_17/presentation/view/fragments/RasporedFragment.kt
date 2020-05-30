package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_raspored.*
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.RasporedContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.adapter.PredavanjeAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.diff.PredavanjeDiffItemCallback
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.PredavanjeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RasporedFragment : Fragment(R.layout.fragment_raspored) {

    private lateinit var predavanjeAdapter: PredavanjeAdapter

    private val predavanjeViewModel: RasporedContract.ViewModel by viewModel<PredavanjeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        initSpinners()
        initObservers()
        //initListeners()
        initRecycler()
    }

    private fun initSpinners() {
        val options = arrayOf("Svaki","Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak", "Subota", "Nedelja")

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
                Timber.e(options[position])
            }
        }


    }

    private fun initObservers() {
        predavanjeViewModel.predavanja.observe(viewLifecycleOwner, Observer {
            predavanjeAdapter.submitList(it)
        })
        predavanjeViewModel.getPredavanja()
    }

    private fun initRecycler() {
        listRv.layoutManager = LinearLayoutManager(activity)
        predavanjeAdapter = PredavanjeAdapter(PredavanjeDiffItemCallback())
        listRv.adapter = predavanjeAdapter
    }
}