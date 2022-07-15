package com.lotterental.flexbus_aos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lotterental.flexbus_aos.data.BusRouteItem
import com.lotterental.flexbus_aos.databinding.BusRouteItemBinding

class BusSearchAdapter(private val items: MutableList<BusRouteItem>): RecyclerView.Adapter<BusSearchAdapter.Holder>() {

    class Holder(private val binding: BusRouteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusRouteItem) {
//            binding.tvName.text = "Name: ${data.name}"
//            binding.tvNum.text = "Num: ${data.num}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = BusRouteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}