package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val loggedIn = sharedPreferences.getBoolean(LoginActivity.LOGGED_IN_KEY, false)
        val intent: Intent
//        intent = if (loggedIn) {
//            Intent(this, MainActivity::class.java)
//        }else {
//            Intent(this, LoginActivity::class.java)
//        }
//        intent = Intent(this, LoginActivity::class.java)
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}