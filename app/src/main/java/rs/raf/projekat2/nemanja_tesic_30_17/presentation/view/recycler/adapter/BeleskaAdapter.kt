package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.domain.Beleska
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.diff.BeleskaDiffItemCallback
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.recycler.viewholder.BeleskaViewHolder

class BeleskaAdapter(
    beleskaDiffItemCallback: BeleskaDiffItemCallback,
    private val onDeleteClicked: (Beleska) -> Unit,
    private val onEditClicked: (Beleska) -> Unit,
    private val onArchiveClicked: (Beleska) -> Unit) : ListAdapter<Beleska, BeleskaViewHolder>(beleskaDiffItemCallback){


    private val functionDelete: (Int) -> Unit =  {
        if (it >= 0) {
            val beleska = getItem(it)
            onDeleteClicked.invoke(beleska)
        }
    }

    private val functionEdit: (Int) -> Unit =  {
        if (it >= 0) {
            val beleska = getItem(it)
            onEditClicked.invoke(beleska)
        }
    }

    private val functionArchive: (Int) -> Unit =  {
        if (it >= 0) {
            val beleska = getItem(it)
            onArchiveClicked.invoke(beleska)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeleskaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_beleska_list_item, parent, false)
        return BeleskaViewHolder(containerView, functionDelete, functionEdit, functionArchive)
    }

    override fun onBindViewHolder(holder: BeleskaViewHolder, position: Int) {
        val beleska = getItem(position)
        holder.bind(beleska)
    }

}