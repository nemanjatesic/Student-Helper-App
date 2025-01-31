package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.contracts.KorisnikContract
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.KorisnikViewModel

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private val korisnikViewModel:KorisnikContract.ViewModel by viewModel<KorisnikViewModel>()

    companion object {
        const val LOGGED_IN_KEY = "loggedIn"
        const val USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        et_user_name.setText("nemanja")
        et_pin.setText("1234")
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        korisnikViewModel.ulogovani.observe(this, Observer {
            if (it == null) {
                Toast.makeText(applicationContext,"Pogresno ste ukucali username ili pin",Toast.LENGTH_SHORT).show()
                //korisnikViewModel.insert(Korisnik(0,et_user_name.text.toString(), et_pin.text.toString()))
            }else {
                korisnikViewModel.setUlogovani(it)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun initListeners() {
        button_login_prijava.setOnClickListener {
            if (checkText(et_user_name, "Molimo vas unesite ime")) {
                korisnikViewModel.verify(et_user_name.text.toString(), et_pin.text.toString())
            }
        }
//        button_login_prijava.setOnClickListener {
//            val username = et_user_name.text.toString()
//            val pin = et_pin.text.toString()
//
//            val korisnik = Korisnik(0, username, pin)
//            korisnikViewModel.insert(korisnik)
//        }
//        button_login_prijava.setOnClickListener {
//            val beleska = Beleska(0,"Moja beleska","Ovo je moja nova beleska",1)
//            beleskaViewModel.insert(beleska)
//        }
    }

    private fun checkText(text: EditText, textOut: String): Boolean {
        if (text.text.isEmpty()) {
            Toast.makeText(applicationContext,textOut,Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}