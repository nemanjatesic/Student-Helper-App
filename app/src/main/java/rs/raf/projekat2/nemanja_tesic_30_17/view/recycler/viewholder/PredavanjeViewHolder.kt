package rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_predmet_list_item.*
import rs.raf.projekat2.nemanja_tesic_30_17.model.Predavanje

class PredavanjeViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(predavanje: Predavanje) {
        profesorTV.text = predavanje.predmet
        tipTV.text = predavanje.tip
        ucionicaTV.text = predavanje.ucionica
        grupeTV.text = predavanje.grupe
        danTV.text = predavanje.dan
        vremeTV.text = predavanje.vreme
    }
}