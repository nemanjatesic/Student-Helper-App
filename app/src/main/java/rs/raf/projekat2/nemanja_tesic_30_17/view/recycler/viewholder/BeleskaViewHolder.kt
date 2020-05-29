package rs.raf.projekat2.nemanja_tesic_30_17.view.recycler.viewholder

import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_beleska_list_item.*
import rs.raf.projekat2.nemanja_tesic_30_17.model.Beleska

class BeleskaViewHolder(
    override val containerView: View,
    onDeleteClicked: (Int) -> Unit,
    onEditClicked: (Int) -> Unit,
    onArchiveClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        deleteBtn.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                onDeleteClicked.invoke(adapterPosition)
        }
        editBtn.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                onEditClicked.invoke(adapterPosition)
        }
        archiveBtn.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                onArchiveClicked.invoke(adapterPosition)
        }
    }

    fun bind(beleska: Beleska) {
        noteTitleTV.text = beleska.naslov
        noteContentTV.text = beleska.sadrzaj
        // noteContentTV.movementMethod = ScrollingMovementMethod()
    }
}