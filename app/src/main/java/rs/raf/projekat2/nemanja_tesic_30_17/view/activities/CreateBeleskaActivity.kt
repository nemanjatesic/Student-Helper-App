package rs.raf.projekat2.nemanja_tesic_30_17.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_beleska.*
import rs.raf.projekat2.nemanja_tesic_30_17.R

class CreateBeleskaActivity : AppCompatActivity(R.layout.activity_create_beleska) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        odustaniBtn.setOnClickListener {
            finish()
        }
    }
}