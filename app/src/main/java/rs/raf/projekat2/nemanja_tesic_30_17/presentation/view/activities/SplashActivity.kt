package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.KorisnikContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.KorisnikViewModel

class SplashActivity : AppCompatActivity() {

    private val korisnikViewModel: KorisnikContract.ViewModel by viewModel<KorisnikViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        korisnikViewModel.ulogovaniId.observe(this, Observer {
            val intent: Intent = if (it == (-1).toLong()) {
                Intent(this, LoginActivity::class.java)
            }else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        })
        korisnikViewModel.getUlogovaniId()
    }
}