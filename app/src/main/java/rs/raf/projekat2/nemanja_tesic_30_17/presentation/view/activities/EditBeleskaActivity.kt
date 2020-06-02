package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_change_beleska.*
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.fragments.BeleskeFragment

class EditBeleskaActivity : AppCompatActivity(R.layout.activity_change_beleska) {

    private lateinit var beleska: Beleska

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val long = -1
        beleska = intent.getParcelableExtra(BeleskeFragment.MESSAGE_BELESKA)?: Beleska(
            long.toLong(),
            "",
            "",
            long.toLong()
        )
        init()
        initListeners()
    }

    private fun init() {
        val long = -1
        if (beleska.id == long.toLong())
            finish()
        noteTitleEditTV.setText(beleska.naslov)
        noteContentEditTV.setText(beleska.sadrzaj)
    }

    private fun initListeners() {
        odustaniBtn.setOnClickListener {
            finish()
        }
    }
}