package rs.raf.projekat2.nemanja_tesic_30_17.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tabs.*
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.view.viewpager.PagerTopAdapter

class MainActivity : AppCompatActivity(R.layout.activity_tabs) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initTabs()
    }

    private fun initTabs() {
        viewPagerTop.adapter = PagerTopAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPagerTop)
    }
}