package com.android.myweatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.myweatherapp.data.model.api.response.ForecastDay
import com.android.myweatherapp.databinding.ItemLayoutBinding
import com.android.myweatherapp.ui.listing.ListingAdapter

class RAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: MutableList<ForecastDay> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder)
            holder.bindTo(items[position], position)
    }

    fun addItems(forecastList: MutableList<ForecastDay>?) {
        forecastList?.let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(
        private val itemLayoutBinding: ItemLayoutBinding    // item_layout.xml
    ) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun bindTo(forecastDay: ForecastDay, position: Int) {
            itemLayoutBinding.forecast = forecastDay
            itemLayoutBinding.divider.visibility = if(position == items.size - 1)
                View.GONE
            else
                View.INVISIBLE
            itemLayoutBinding.executePendingBindings()
        }
    }
}