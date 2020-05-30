package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import rs.raf.projekat2.nemanja_tesic_30_17.R

class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    companion object {
        const val LOGGED_IN_KEY = "loggedIn"
        const val USER_NAME = "userName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        button_login_prijava.setOnClickListener {
            if (checkText(et_user_name, "Molimo vas unesite ime") && checkPin(et_pin.text)) {
                val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean(LOGGED_IN_KEY, true)
                editor.putString(USER_NAME,et_user_name.text.toString())
                editor.apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun checkText(text: EditText, textOut: String): Boolean {
        if (text.text.isEmpty()) {
            Toast.makeText(applicationContext,textOut,Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun checkPin(pin: Editable): Boolean {
//        val pinSize: Int = pin.length
//        val pinString = pin.toString()

        return true
    }
}