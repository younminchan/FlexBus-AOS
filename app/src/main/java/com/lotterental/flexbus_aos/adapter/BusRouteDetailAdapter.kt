package com.lotterental.flexbus_aos.adapter

import android.util.Log
import android.view.LayoutInflater
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
        holder.bind(items[position], mainViewModel, fragment)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(private val binding: BusRouteDetailItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: BusRouteDetatilItem_item, mainViewModel: MainViewModel, fragment: BusRouteDetailFragment) {

            Log.e("YMC", "data: ${data}")
            binding.tvBusRouteDetailBusArsName.text = data.stNm
            binding.tvBusRouteDetailBusArsNumber.text = data.arsId


//            binding.clBusRouteItemMain.setOnClickListener {
//                mainViewModel.busRouteDetailItem.value = data
//
//                //버스도착정보 디테일로 이동
//                MainActivity.getInstance().moveFragment(fragment.requireActivity(), BusRouteDetatilFragment(), true)
//            }
//
//
//            binding.tvRouteType.text = when(data.routeType){
//                "1" -> "공항"
//                "2" -> "마을"
//                "3" -> "간선"
//                "4" -> "지선"
//                "5" -> "순환"
//                "6" -> "광역"
//                "7" -> "인천"
//                "8" -> "경기"
//                "9" -> "폐지"
//                "0" -> "공용"
//                else -> "  "
//            }
//            binding.tvBusRouteNm.text = data.busRouteNm //노선 번호
//            binding.tvStationNm.text = "${data.stStationNm} ↔ ${data.edStationNm}" //기점 ↔ 종점
        }
    }

    /** Item 추가 */
    fun setItems(items: ArrayList<BusRouteDetatilItem_item>){
        //TODO: 방법1
        this.items = items
        notifyDataSetChanged()
    }
}