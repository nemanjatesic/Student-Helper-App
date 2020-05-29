package rs.raf.projekat2.nemanja_tesic_30_17.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import kotlinx.android.synthetic.main.fragment_raspored.listRv
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.model.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.view.activities.CreateBeleskaActivity
import rs.raf.projekat2.nemanja_tesic_30_17.view.activities.EditBeleskaActivity
import rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.adapter.BeleskaAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.nemanja_tesic_30_17.viewmodel.BeleskaViewModel

class BeleskeFragment : Fragment(R.layout.fragment_beleske) {

    private val beleskaViewModel: BeleskaViewModel by activityViewModels()

    private lateinit var beleskaAdapter: BeleskaAdapter

    companion object {
        const val MESSAGE_BELESKA = "BELESKA"
        const val MESSAGE_REQUEST_CODE = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun initObservers() {
        beleskaViewModel.getBeleske().observe(viewLifecycleOwner, Observer {
            beleskaAdapter.submitList(it)
        })
    }

    private fun initListeners() {
        floatingButton.setOnClickListener {
            val intent = Intent(activity, CreateBeleskaActivity::class.java)
            startActivity(intent)
        }
    }

    private val clickOnDelete: (Beleska) -> Unit = {
//        val factory = PatientFactory()
//        sharedViewModel.addPatient(factory.copyPatient(it, dateOfHospitalization = Date()), SharedViewModel.HOSPITALIZOVANI)
//        sharedViewModel.removePatient(it, SharedViewModel.CEKAONICA)
//        sharedViewModel.filterPatients(et_search_cekaonica.text.toString(), SharedViewModel.CEKAONICA)
    }

    private val clickOnEdit: (Beleska) -> Unit = {
        val intent = Intent(activity, EditBeleskaActivity::class.java)
        intent.putExtra(MESSAGE_BELESKA, it)
        startActivity(intent)
    }

    private val clickOnArchive: (Beleska) -> Unit = {
//        val factory = PatientFactory()
//        sharedViewModel.addPatient(factory.copyPatient(it, dateOfHospitalization = Date()), SharedViewModel.HOSPITALIZOVANI)
//        sharedViewModel.removePatient(it, SharedViewModel.CEKAONICA)
//        sharedViewModel.filterPatients(et_search_cekaonica.text.toString(), SharedViewModel.CEKAONICA)
    }

    private fun initRecycler() {
        listRv.layoutManager = LinearLayoutManager(activity)
        beleskaAdapter = BeleskaAdapter(BeleskaDiffItemCallback(), clickOnDelete, clickOnEdit, clickOnArchive)
        listRv.adapter = beleskaAdapter
    }
}