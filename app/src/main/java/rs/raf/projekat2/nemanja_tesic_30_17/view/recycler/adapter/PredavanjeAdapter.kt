package rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.model.Predavanje
import rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.diff.PredavanjeDiffItemCallback
import rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.viewholder.PredavanjeViewHolder

class PredavanjeAdapter(predavanjeDiffItemCallback: PredavanjeDiffItemCallback) : ListAdapter<Predavanje, PredavanjeViewHolder>(predavanjeDiffItemCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredavanjeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_predmet_list_item, parent, false)
        return PredavanjeViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: PredavanjeViewHolder, position: Int) {
        val predavanje = getItem(position)
        holder.bind(predavanje)
    }


}