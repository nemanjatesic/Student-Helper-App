package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska

class BeleskaDiffItemCallback : DiffUtil.ItemCallback<Beleska>() {

    override fun areItemsTheSame(oldItem: Beleska, newItem: Beleska): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beleska, newItem: Beleska): Boolean {
        return oldItem.naslov == newItem.naslov &&
                oldItem.sadrzaj == newItem.sadrzaj
    }
}