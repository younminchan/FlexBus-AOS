package com.lotterental.flexbus_aos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lotterental.flexbus_aos.BusRouteDetailFragment
import com.lotterental.flexbus_aos.MainActivity
import com.lotterental.flexbus_aos.SearchFragment
import com.lotterental.flexbus_aos.data.BusRouteItem
import com.lotterental.flexbus_aos.databinding.BusRouteItemBinding
import com.lotterental.flexbus_aos.viewmodel.MainViewModel

class BusRouteListAdapter(private var fragment: SearchFragment, private var mainViewModel: MainViewModel): RecyclerView.Adapter<BusRouteListAdapter.Holder>() {
    private var items =  ArrayList<BusRouteItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteListAdapter.Holder {
        val binding = BusRouteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], mainViewModel, fragment)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(private val binding: BusRouteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: BusRouteItem, mainViewModel: MainViewModel, fragment: SearchFragment) {
            binding.clBusRouteItemMain.setOnClickListener {
                mainViewModel.busRouteDetailItem.value = data

                //버스도착정보 디테일로 이동
                MainActivity.getInstance().moveFragment(fragment.requireActivity(), BusRouteDetailFragment(), true, true)
            }


            binding.tvRouteType.text = when(data.routeType){
                "1" -> "공항"
                "2" -> "마을"
                "3" -> "간선"
                "4" -> "지선"
                "5" -> "순환"
                "6" -> "광역"
                "7" -> "인천"
                "8" -> "경기"
                "9" -> "폐지"
                "0" -> "공용"
                else -> "  "
            }
            binding.tvBusRouteNm.text = data.busRouteNm //노선 번호
            binding.tvStationNm.text = "${data.stStationNm} ↔ ${data.edStationNm}" //기점 ↔ 종점
        }
    }

    /** Item 추가 */
    fun setItems(items: ArrayList<BusRouteItem>){
        //TODO: 방법1
        this.items = items
        notifyDataSetChanged()

        //TODO: 방법2(안됨)
//        this.items = ArrayList<BusRouteItem>()
//        items.forEach {
//            this.items.add(it)
//            notifyItemInserted(this.items.size)
//
//        }
    }
}