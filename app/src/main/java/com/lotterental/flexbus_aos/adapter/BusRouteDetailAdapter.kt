package com.lotterental.flexbus_aos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lotterental.flexbus_aos.BusRouteDetailFragment
import com.lotterental.flexbus_aos.data.BusRouteDetatilItem_item
import com.lotterental.flexbus_aos.databinding.BusRouteDetailItemBinding
import com.lotterental.flexbus_aos.viewmodel.MainViewModel

class BusRouteDetailAdapter(private var fragment: BusRouteDetailFragment, private var mainViewModel: MainViewModel): RecyclerView.Adapter<BusRouteDetailAdapter.Holder>() {
    private var items =  ArrayList<BusRouteDetatilItem_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteDetailAdapter.Holder {
        val binding = BusRouteDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], mainViewModel, items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(private val binding: BusRouteDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusRouteDetatilItem_item, mainViewModel: MainViewModel, items: ArrayList<BusRouteDetatilItem_item>) {
            binding.tvBusRouteDetailBusArsName.text = data.stNm
            binding.tvBusRouteDetailBusArsNumber.text = data.arsId

            Log.e("YMC", "position: ${items.indexOf(data) }")

            //시작 / 끝
            if ((items.indexOf(data) == 0) || items.indexOf(data) == items.size) {
                binding.ivBusRouteDetailLineTop.visibility = View.INVISIBLE
            } else {
                binding.ivBusRouteDetailLineTop.visibility = View.VISIBLE
            }


            //버스 도착
            if (data.arrmsg1.contains("[0번째 전") || data.arrmsg1.contains("곧 도착")) {
                binding.ivBusRouteDetailLineBus.visibility = View.VISIBLE
            } else {
                binding.ivBusRouteDetailLineBus.visibility = View.INVISIBLE
            }

            //도착 버스정보
            if (binding.ivBusRouteDetailLineBus.visibility == View.VISIBLE && !data.plainNo1.trim().isNullOrEmpty()
                && data.plainNo1.trim().isNotEmpty() && data.plainNo1.trim().length >= 4) {
                binding.tvBusRouteDetailBusNumber.visibility = View.VISIBLE
                binding.tvBusRouteDetailBusNumber.text = data.plainNo1.trim().substring(data.plainNo1.length - 4, data.plainNo1.length)
            } else {
                binding.tvBusRouteDetailBusNumber.visibility = View.INVISIBLE
            }



        }
    }

    /** Item 추가 */
    fun setItems(items: ArrayList<BusRouteDetatilItem_item>){
        //TODO: 방법1
        this.items = items
        notifyDataSetChanged()
    }
}