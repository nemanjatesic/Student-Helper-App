package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_beleska.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.BeleskaContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel

class CreateBeleskaActivity : AppCompatActivity(R.layout.activity_create_beleska) {

    private val beleskaViewModel: BeleskaContract.ViewModel by viewModel<BeleskaViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        odustaniBtn.setOnClickListener {
            finish()
        }
        kreirajBtn.setOnClickListener {
            val beleskaNaslov = newNoteTitleTV.text.toString()
            val beleskaSadrzaj = newContentTitleeTV.text.toString()

            val beleska = Beleska(0, beleskaNaslov, beleskaSadrzaj, 1)
            beleskaViewModel.insert(beleska)
            finish()
        }
    }
}