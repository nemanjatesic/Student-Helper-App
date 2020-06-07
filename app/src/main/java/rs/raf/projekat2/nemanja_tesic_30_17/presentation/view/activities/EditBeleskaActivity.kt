package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_change_beleska.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.extensions.getKorisnik
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.KorisnikContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments.BeleskeFragment
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.BeleskeState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.KorisnikViewModel

class EditBeleskaActivity : AppCompatActivity(R.layout.activity_change_beleska) {

    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()
    private val korisnikViewModel: KorisnikContract.ViewModel by viewModel<KorisnikViewModel>()

    private lateinit var beleska: Beleska
    private var korisnikId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initListeners()
        initObservers()
    }

    private fun init() {
        val long = -1
        beleska = intent.getParcelableExtra(BeleskeFragment.MESSAGE_BELESKA)?: Beleska(long.toLong(), "", "", 0, "", long.toLong())
        if (beleska.id == long.toLong())
            finish()
        noteTitleEditTV.setText(beleska.naslov)
        noteContentEditTV.setText(beleska.sadrzaj)
    }

    private fun initListeners() {
        odustaniBtn.setOnClickListener {
            finish()
        }
        izmeniBtn.setOnClickListener {
            if (korisnikId == (-1).toLong())
                return@setOnClickListener
            val newBeleskaNaslov = noteTitleEditTV.text.toString()
            val newBeleskaSadrzaj = noteContentEditTV.text.toString()

            val beleska = Beleska(beleska.id, newBeleskaNaslov, newBeleskaSadrzaj,beleska.arhivirana,"", korisnikId)
            beleskaViewModel.update(beleska.naslov, beleska.sadrzaj, beleska.arhivirana, beleska.id)
            finish()
        }
    }

    private fun initObservers() {
        beleskaViewModel.beleskeState.observe(this, Observer {
            renderState(it)
        })
        korisnikViewModel.ulogovaniId.observe(this, Observer {
            korisnikId = it
        })
        korisnikViewModel.getUlogovaniId()
    }

    private fun renderState(state: BeleskeState) {
        when(state) {
            is BeleskeState.Edit -> Toast.makeText(this, "Beleska edited", Toast.LENGTH_SHORT).show()
            is BeleskeState.Error -> Toast.makeText(this, "Error happened", Toast.LENGTH_SHORT).show()
        }
    }
}