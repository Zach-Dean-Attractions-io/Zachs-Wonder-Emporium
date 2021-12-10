package com.example.zachswonderemporium.ui.planner.addtoplan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zachswonderemporium.data.model.PlanItemPointOfInterest
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.databinding.ListItemAddToPlanItemBinding
import com.example.zachswonderemporium.databinding.ListItemPlanItemBinding

interface OnItemCheckedListener {
    fun onItemCheckChanged(pointOfInterest: PointOfInterest, checkedValue: Boolean)
}

class AddToPlanDiffCallback: DiffUtil.ItemCallback<PointOfInterest>() {

    override fun areItemsTheSame(oldItem: PointOfInterest, newItem: PointOfInterest): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PointOfInterest, newItem: PointOfInterest): Boolean {
        return oldItem == newItem
    }

}

class AddToPlanAdapter(private val itemCheckedListener: OnItemCheckedListener): ListAdapter<PointOfInterest, AddToPlanAdapter.ViewHolder>(AddToPlanDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemCheckedListener)
    }

    class ViewHolder private constructor(private val binding: ListItemAddToPlanItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(pointOfInterest: PointOfInterest, itemCheckedListener: OnItemCheckedListener) {
            binding.pointOfInterest = pointOfInterest
            binding.listAddToPlanCheckbox.setOnCheckedChangeListener { _, checkboxValue ->
                println("Check Box Changed")
                itemCheckedListener.onItemCheckChanged(pointOfInterest, checkboxValue)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAddToPlanItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}