package rs.raf.projekat2.nemanja_tesic_30_17.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import kotlinx.android.synthetic.main.fragment_raspored.*
import kotlinx.android.synthetic.main.fragment_raspored.listRv
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.adapter.PredavanjeAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.diff.PredavanjeDiffItemCallback

class RasporedFragment : Fragment(R.layout.fragment_raspored) {
    private lateinit var predavanjeAdapter: PredavanjeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        //initObservers()
        //initListeners()
        initRecycler()
    }

    private fun initRecycler() {
        listRv.layoutManager = LinearLayoutManager(activity)
        predavanjeAdapter = PredavanjeAdapter(PredavanjeDiffItemCallback())
        listRv.adapter = predavanjeAdapter
    }
}