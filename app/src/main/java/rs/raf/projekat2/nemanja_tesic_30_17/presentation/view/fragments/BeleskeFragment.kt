package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_beleske.*
import kotlinx.android.synthetic.main.fragment_raspored.listRv
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities.CreateBeleskaActivity
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities.EditBeleskaActivity
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.adapter.BeleskaAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel
import timber.log.Timber


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
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    // Pritisnuo NE i ne zeli da obrise
                    DialogInterface.BUTTON_POSITIVE -> {
                    }
                    // Pritisnuo DA i hoce da obrise
                    DialogInterface.BUTTON_NEGATIVE -> {
                        Timber.e("Hocu da obrisemmmm")
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
                        Timber.e("Hocu da obrisemmmm")
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
            .setMessage("Da li ste sigurni da zelite da arhivirate ovu belesku?")
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