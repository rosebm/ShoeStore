package com.rosalynbm.shoestoreinventory.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.models.Shoe
import kotlinx.android.synthetic.main.shoe_item_layout.view.*

class ShoeListAdapter(private val listener: ListItemClickListener<Shoe>):
    ListAdapter<Shoe, ShoeListAdapter.ShoeListViewHolder>(DIFF_CALLBACK) {

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<Shoe> = object : DiffUtil.ItemCallback<Shoe>() {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.shoe_item_layout, parent, false)
        return ShoeListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ShoeListViewHolder, position: Int) {
        val shoe = getItem(position)

        holder.name.text = shoe.name
        holder.designer.text = shoe.company
    }

    inner class ShoeListViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val name: TextView = view.shoe_name_text
        val designer: TextView = view.designer_text
    }

}