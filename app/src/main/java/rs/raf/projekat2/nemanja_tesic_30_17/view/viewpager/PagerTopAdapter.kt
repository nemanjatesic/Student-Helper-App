package rs.raf.projekat2.nemanja_tesic_30_17.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.view.fragments.BeleskeFragment
import rs.raf.projekat2.nemanja_tesic_30_17.view.fragments.RasporedFragment
import rs.raf.projekat2.nemanja_tesic_30_17.view.fragments.StatistikaFragment

class PagerTopAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_RASPORED = 0
        const val FRAGMENT_BELESKE = 1
        const val FRAGMENT_STATISTIKA = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_RASPORED -> RasporedFragment()
            FRAGMENT_BELESKE -> BeleskeFragment()
            else -> StatistikaFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_RASPORED -> "Raspored"
            FRAGMENT_BELESKE -> "Beleske"
            else -> "Statistika"
        }
    }

}