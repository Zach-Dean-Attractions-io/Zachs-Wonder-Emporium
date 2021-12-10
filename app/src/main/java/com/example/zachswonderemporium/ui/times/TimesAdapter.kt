package com.example.zachswonderemporium.ui.times

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.data.model.PointOfInterestLiveTime
import com.example.zachswonderemporium.databinding.ListItemPoiWithTimesBinding

interface OnItemClickListener {
    fun onItemClicked(pointOfInterestLiveTime: PointOfInterestLiveTime)
}

class TimesDiffCallback: DiffUtil.ItemCallback<PointOfInterestLiveTime>() {

    override fun areItemsTheSame(oldItem: PointOfInterestLiveTime, newItem: PointOfInterestLiveTime): Boolean {
        return oldItem.liveTime?.id == newItem.liveTime?.id
    }

    override fun areContentsTheSame(oldItem: PointOfInterestLiveTime, newItem: PointOfInterestLiveTime): Boolean {
        return oldItem == newItem
    }

}

class TimesAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<PointOfInterestLiveTime, TimesAdapter.ViewHolder>(TimesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    class ViewHolder private constructor(private val binding: ListItemPoiWithTimesBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(poiWithLiveTime: PointOfInterestLiveTime, itemClickListener: OnItemClickListener) {
            binding.poiWithLiveTime = poiWithLiveTime
            binding.clickListener = itemClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPoiWithTimesBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

}