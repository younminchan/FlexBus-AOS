package com.lotterental.flexbus_aos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lotterental.flexbus_aos.adapter.BusRouteDetailAdapter
import com.lotterental.flexbus_aos.adapter.BusRouteListAdapter
import com.lotterental.flexbus_aos.data.BusRouteDetatilItem
import com.lotterental.flexbus_aos.data.BusRouteListItem
import com.lotterental.flexbus_aos.databinding.FragmentBusRouteDetailBinding
import com.lotterental.flexbus_aos.repositroy.MainRepository
import com.lotterental.flexbus_aos.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusRouteDetailFragment : Fragment() {
    lateinit var binding: FragmentBusRouteDetailBinding
    lateinit var mainViewModel: MainViewModel

    lateinit var busRouteDetailAdapter : BusRouteDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentBusRouteDetailBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        //Bus name
        binding.tvBusRouteDetailName.text = mainViewModel.busRouteDetailItem.value?.busRouteNm

        initBusRouteDetail()

        return binding.root
    }

    private fun initBusRouteDetail(){
        busRouteDetailAdapter = BusRouteDetailAdapter(this, mainViewModel)

        /** 뒤로가기 */
        binding.ivBack.setOnClickListener{
            App.activity.onBackPressed()
        }

        mainViewModel.busRouteDetailItem.value?.busRouteId.let {
            if(!it.isNullOrEmpty()){
                getBusRouteList(it)
                Log.e("YMC", "통신 it: ${it}")
            }
        }

    }

    private fun getBusRouteList(strSrch: String) {
        MainRepository().getArrInfoByRouteAllList(strSrch).enqueue(object : Callback<BusRouteDetatilItem> {
            override fun onResponse(call: Call<BusRouteDetatilItem>, response: Response<BusRouteDetatilItem>, ) {
                if(response.isSuccessful){
                    var res = response.body()


                    if(res?.msgBody?.itemList!=null && res?.msgBody?.itemList.size>0){
                        busRouteDetailAdapter.setItems(res.msgBody.itemList)
                    }
                }
            }

            override fun onFailure(call: Call<BusRouteDetatilItem>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}