package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import kotlinx.android.synthetic.main.fragment_raspored.listRv
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.KorisnikContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities.CreateBeleskaActivity
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities.EditBeleskaActivity
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.adapter.BeleskaAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.BeleskeState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.KorisnikViewModel
import timber.log.Timber


class BeleskeFragment : Fragment(R.layout.fragment_beleske) {

    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()
    private val korisnikViewModel: KorisnikContract.ViewModel by viewModel<KorisnikViewModel>()

    private var korisnikId: Long = -1
    private lateinit var beleskaAdapter: BeleskaAdapter
    private var switchSelected = false

    companion object {
        const val MESSAGE_BELESKA = "BELESKA"

        const val BELESKA_NASLOV = "BELESKA_NASLOV"
        const val BELESKA_ARHIVIRANA = "BELESKA_ARHIVIRANA"
        const val BELESKA_ID = "BELESKA_ID"
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
        beleskaViewModel.beleskeState.observe(viewLifecycleOwner, Observer {
            Timber.e("Beleske updated")
            renderState(it)
        })
        korisnikViewModel.ulogovaniId.observe(viewLifecycleOwner, Observer {
            korisnikId = it
            beleskaViewModel.filter(et_search.text.toString(), if (switchSelected) 1 else 0, korisnikId)
        })
        korisnikViewModel.getUlogovaniId()
    }

    private fun renderState(state: BeleskeState) {
        when(state) {
            is BeleskeState.Success -> {
                beleskaAdapter.submitList(state.beleske)
            }
            is BeleskeState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
            is BeleskeState.Edit -> Toast.makeText(context, "Beleska updated", Toast.LENGTH_SHORT).show()
            is BeleskeState.Delete -> Toast.makeText(context, "Beleska deleted", Toast.LENGTH_SHORT).show()
            is BeleskeState.Add -> Toast.makeText(context, "Beleska added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListeners() {
        floatingButton.setOnClickListener {
            val intent = Intent(activity, CreateBeleskaActivity::class.java)
            startActivity(intent)
        }
        et_search.doAfterTextChanged {
            beleskaViewModel.filter(et_search.text.toString(), if (switchSelected) 1 else 0, korisnikId)
        }
        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            switchSelected = isChecked
            beleskaViewModel.filter(et_search.text.toString(), if (switchSelected) 1 else 0, korisnikId)
            Timber.e("Is checked %s", switchSelected.toString())
        }
    }

    private val clickOnDelete: (Beleska) -> Unit = {
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    // Pritisnuo NE i ne zeli da obrise
                    DialogInterface.BUTTON_POSITIVE -> {
                    }
                    // Pritisnuo DA i hoce da obrise
                    DialogInterface.BUTTON_NEGATIVE -> {
                        beleskaViewModel.delete(it.id)
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val blueColor = "#0066FF"
        val positiveStr = "<font color='${blueColor}'>Ne</font>"
        val negativeStr = "<font color='${blueColor}'>Yes</font>"
        val positive = if (Build.VERSION.SDK_INT < 24) Html.fromHtml(positiveStr) else Html.fromHtml(positiveStr, Html.FROM_HTML_MODE_LEGACY)
        val negative = if (Build.VERSION.SDK_INT < 24) Html.fromHtml(negativeStr) else Html.fromHtml(negativeStr, Html.FROM_HTML_MODE_LEGACY)

        builder
            .setMessage("Da li ste sigurni da zelite da obrisete ovu belesku?")
            .setPositiveButton(positive, dialogClickListener)
            .setNegativeButton(negative, dialogClickListener)
            .show()

    }

    private val clickOnEdit: (Beleska) -> Unit = {
        val intent = Intent(activity, EditBeleskaActivity::class.java)

        intent.putExtra(MESSAGE_BELESKA, it)
        intent.putExtra(BELESKA_NASLOV, et_search.text.toString())
        intent.putExtra(BELESKA_ARHIVIRANA, if (switchSelected) 1 else 0)
        intent.putExtra(BELESKA_ID, korisnikId)

        startActivity(intent)
    }

    private val clickOnArchive: (Beleska) -> Unit = {
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    // Pritisnuo NE i ne zeli da arhivira
                    DialogInterface.BUTTON_POSITIVE -> {
                    }
                    // Pritisnuo DA i hoce da arhivira
                    DialogInterface.BUTTON_NEGATIVE -> {
                        beleskaViewModel.update(it.naslov, it.sadrzaj, if (it.arhivirana == 1) 0 else 1, it.id)
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val blueColor = "#0066FF"
        val positiveStr = "<font color='${blueColor}'>Ne</font>"
        val negativeStr = "<font color='${blueColor}'>Yes</font>"
        val positive = if (Build.VERSION.SDK_INT < 24) Html.fromHtml(positiveStr) else Html.fromHtml(positiveStr, Html.FROM_HTML_MODE_LEGACY)
        val negative = if (Build.VERSION.SDK_INT < 24) Html.fromHtml(negativeStr) else Html.fromHtml(negativeStr, Html.FROM_HTML_MODE_LEGACY)

        if (it.arhivirana == 0) {
            builder.setMessage("Da li ste sigurni da zelite da arhivirate ovu belesku?")
        }else {
            builder.setMessage("Da li ste sigurni da zelite da un-arhivirate ovu belesku?")
        }

        builder
            .setPositiveButton(positive, dialogClickListener)
            .setNegativeButton(negative, dialogClickListener)
            .show()
    }

    private fun initRecycler() {
        listRv.layoutManager = LinearLayoutManager(activity)
        beleskaAdapter = BeleskaAdapter(BeleskaDiffItemCallback(), clickOnDelete, clickOnEdit, clickOnArchive)
        listRv.adapter = beleskaAdapter
    }
}