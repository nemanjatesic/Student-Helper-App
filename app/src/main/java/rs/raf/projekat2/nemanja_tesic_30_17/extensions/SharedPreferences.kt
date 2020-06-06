package rs.raf.projekat2.nemanja_tesic_30_17.extensions

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Korisnik
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.activities.LoginActivity

fun SharedPreferences.getKorisnik() : Korisnik? {
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Korisnik> = moshi.adapter(Korisnik::class.java)
    val user = this.getString(LoginActivity.USER,"")
    if (user == "") return null
    return adapter.fromJson(user!!)
}