package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_create_beleska.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.extensions.getKorisnik
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments.BeleskeFragment
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.AddBeleskaState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.EditBeleskaState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel


class CreateBeleskaActivity : AppCompatActivity(R.layout.activity_create_beleska) {

    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()
    private val sharedPreferences: SharedPreferences by inject()

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
            val newBeleskaNaslov = newNoteTitleTV.text.toString()
            val newBeleskaSadrzaj = newContentTitleeTV.text.toString()

            val korisnik = sharedPreferences.getKorisnik()

            val beleska = Beleska(0, newBeleskaNaslov, newBeleskaSadrzaj,0,"", korisnik!!.id)
            beleskaViewModel.insert(beleska)
            finish()
        }
    }

    private fun initObservers() {
        beleskaViewModel.addDone.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: AddBeleskaState) {
        when(state) {
            is AddBeleskaState.Success -> Toast.makeText(this, "Beleska added", Toast.LENGTH_SHORT).show()
            is AddBeleskaState.Error -> Toast.makeText(this, "Error happened", Toast.LENGTH_SHORT).show()
        }
    }
}