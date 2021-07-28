package com.application.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.notesapp.R
import com.application.notesapp.database.Notes
import com.application.notesapp.databinding.DisplayRecycularViewBinding

//class DisplayAdapter : RecyclerView.Adapter<DisplayAdapter.ViewHolder>() {
class DisplayAdapter : ListAdapter<Notes, DisplayAdapter.ViewHolder>(NotesDiffCallBack()) {

//    var data = listOf<Notes>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    //class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class ViewHolder private constructor(val binding:  DisplayRecycularViewBinding) : RecyclerView.ViewHolder(binding.root) {

        val titleview: TextView = binding.titletextView
        val descriptionview: TextView = binding.descriptiontextView
        fun bind(
            item: Notes
        ) {
//            titleview.text = item.title.toString()
//            descriptionview.text = item.description.toString()
            binding.note= item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater
//                    .inflate(R.layout.display_recycular_view, parent, false)
//                return ViewHolder(view)
                    val binding = DisplayRecycularViewBinding.inflate(layoutInflater,parent,false)
                    return ViewHolder(binding)
            }
        }
    }
}

class NotesDiffCallBack : DiffUtil.ItemCallback<Notes>() {
    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem == newItem
    }

}

