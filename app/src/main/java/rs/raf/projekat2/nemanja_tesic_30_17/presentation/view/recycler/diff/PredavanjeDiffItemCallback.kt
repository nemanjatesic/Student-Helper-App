package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Predavanje

class PredavanjeDiffItemCallback : DiffUtil.ItemCallback<Predavanje>() {

    override fun areItemsTheSame(oldItem: Predavanje, newItem: Predavanje): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Predavanje, newItem: Predavanje): Boolean {
        return oldItem.predmet == newItem.predmet &&
                oldItem.tip == newItem.tip &&
                oldItem.profesor == newItem.profesor &&
                oldItem.ucionica == newItem.ucionica &&
                oldItem.grupe == newItem.grupe &&
                oldItem.dan == newItem.dan &&
                oldItem.vreme == newItem.vreme
    }
}