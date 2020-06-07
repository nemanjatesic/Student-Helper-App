package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_create_beleska.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.KorisnikContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.BeleskeState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.KorisnikViewModel


class CreateBeleskaActivity : AppCompatActivity(R.layout.activity_create_beleska) {

    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()
    private val korisnikViewModel: KorisnikContract.ViewModel by viewModel<KorisnikViewModel>()

    private var korisnikId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        odustaniBtn.setOnClickListener {
            finish()
        }
        kreirajBtn.setOnClickListener {
            if (korisnikId == (-1).toLong())
                return@setOnClickListener
            val newBeleskaNaslov = newNoteTitleTV.text.toString()
            val newBeleskaSadrzaj = newContentTitleeTV.text.toString()

            val beleska = Beleska(0, newBeleskaNaslov, newBeleskaSadrzaj,0,"", korisnikId)
            beleskaViewModel.insert(beleska)
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
            is BeleskeState.Add -> Toast.makeText(this, "Beleska added", Toast.LENGTH_SHORT).show()
            is BeleskeState.Error -> Toast.makeText(this, "Error happened", Toast.LENGTH_SHORT).show()
        }
    }
}