package com.lotterental.flexbus_aos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lotterental.flexbus_aos.data.BusRouteListItem
import com.lotterental.flexbus_aos.databinding.FragmentSearchBinding
import com.lotterental.flexbus_aos.repositroy.MainRepository
import com.lotterental.flexbus_aos.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        initSearchFragment()


        return binding.root
    }

    private fun initSearchFragment(){
        /** 뒤로가기 */
        binding.ivBack.setOnClickListener{
            App.activity.onBackPressed()
        }

        /** 노선번호 검색 */
        binding.tvSearchInput.setOnClickListener {
            var searchBusNum:String = binding.etSearch.text.toString()
            if(!searchBusNum.isNullOrEmpty()) {
                getBusRouteList(searchBusNum)
            }
        }

        /** 버스정류소 RecyClerVIew */
        initRecyclerViewBusSearch()
    }

    private fun initRecyclerViewBusSearch(){
        binding.rvBusRouteList.layoutManager = LinearLayoutManager(App.activity, LinearLayoutManager.VERTICAL, false)
        binding.rvBusRouteList.adapter = RvAdapter(dataset)
    }

    private fun getBusRouteList(strSrch: String) {
        MainRepository().getBusRouteList(strSrch).enqueue(object : Callback<BusRouteListItem> {
            override fun onResponse(call: Call<BusRouteListItem>, response: Response<BusRouteListItem>, ) {
                var res = response.body()
                Log.e("YMC", "retrofit res: ${res}")
                Log.e("YMC", "retrofit res item: ${res?.msgHeader?.headerMsg}")
                Log.e("YMC", "retrofit res item: ${res?.msgBody?.itemList}")

                res?.msgBody?.itemList?.forEach {
                    Log.e("YMC","=====forRach: ${it}")
                }

                binding.tvSearchFragment.text = res?.msgBody?.itemList.toString()
            }

            override fun onFailure(call: Call<BusRouteListItem>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}