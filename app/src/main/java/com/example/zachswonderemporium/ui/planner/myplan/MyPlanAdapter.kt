package com.example.zachswonderemporium.ui.planner.myplan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.zachswonderemporium.data.model.PlanItemPointOfInterest
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.databinding.ListItemPlanItemBinding

interface OnItemClickedListener {
    fun onDeleteClicked(pointOfInterest: PlanItemPointOfInterest)
}

class MyPlanDiffCallback: DiffUtil.ItemCallback<PlanItemPointOfInterest>() {

    override fun areItemsTheSame(oldItem: PlanItemPointOfInterest, newItem: PlanItemPointOfInterest): Boolean {
        return oldItem.planItem.id == newItem.planItem.id
    }

    override fun areContentsTheSame(oldItem: PlanItemPointOfInterest, newItem: PlanItemPointOfInterest): Boolean {
        return oldItem == newItem
    }

}

class MyPlanAdapter(private val itemClickedListener: OnItemClickedListener): ListAdapter<PlanItemPointOfInterest, MyPlanAdapter.ViewHolder>(
    MyPlanDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickedListener)
    }

    class ViewHolder private constructor(private val binding: ListItemPlanItemBinding): RecyclerView.ViewHolder(binding.root) {

        lateinit var planItem: PlanItemPointOfInterest

        fun bind(planItem: PlanItemPointOfInterest, itemClickedListener: OnItemClickedListener) {
            this.planItem = planItem
            binding.planItem = planItem
            binding.clickListener = itemClickedListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPlanItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}