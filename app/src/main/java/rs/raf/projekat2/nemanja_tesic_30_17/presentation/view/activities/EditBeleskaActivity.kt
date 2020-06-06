package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_change_beleska.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments.BeleskeFragment
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.states.beleska.EditBeleskaState
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel
import timber.log.Timber

class EditBeleskaActivity : AppCompatActivity(R.layout.activity_change_beleska) {

    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()

    private lateinit var beleska: Beleska

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
            val newBeleskaNaslov = noteTitleEditTV.text.toString()
            val newBeleskaSadrzaj = noteContentEditTV.text.toString()

            val moshi: Moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<Korisnik> = moshi.adapter(Korisnik::class.java)
            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

            val korisnik = adapter.fromJson(sharedPreferences.getString(LoginActivity.USER,""))

            val beleska = Beleska(beleska.id, newBeleskaNaslov, newBeleskaSadrzaj,beleska.arhivirana,"", korisnik!!.id)
            beleskaViewModel.update(beleska.naslov, beleska.sadrzaj, beleska.arhivirana, beleska.id)
            finish()
        }
    }

    private fun initObservers() {
        beleskaViewModel.editDone.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: EditBeleskaState) {
        when(state) {
            is EditBeleskaState.Success -> Toast.makeText(this, "Beleska edited", Toast.LENGTH_SHORT).show()
            is EditBeleskaState.Error -> Toast.makeText(this, "Error happened", Toast.LENGTH_SHORT).show()
        }
    }
}