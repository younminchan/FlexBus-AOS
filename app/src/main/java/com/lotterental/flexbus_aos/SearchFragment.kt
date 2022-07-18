package com.lotterental.flexbus_aos

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lotterental.flexbus_aos.adapter.BusRouteListAdapter
import com.lotterental.flexbus_aos.data.BusRouteListItem
import com.lotterental.flexbus_aos.databinding.FragmentSearchBinding
import com.lotterental.flexbus_aos.repositroy.MainRepository
import com.lotterental.flexbus_aos.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var busRouteListAdapter : BusRouteListAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

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

                Toast.makeText(App.activity, "${searchBusNum} 노선번호 검색", Toast.LENGTH_SHORT).show()
            }
        }
        binding.etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.tvSearchInput.performClick()
                    Utils.hideKeyboard()
                    return true
                } else {

                    return false
                }
            }
        })
        binding.etSearch.filters = arrayOf(Utils.filterAlphaNumSpace)

        /** 버스정류소 RecyClerVIew */
        initRecyclerViewBusSearch()

    }

    private fun initRecyclerViewBusSearch(){
        busRouteListAdapter = BusRouteListAdapter(this, mainViewModel)

        binding.rvBusRouteList.layoutManager = LinearLayoutManager(App.activity, LinearLayoutManager.VERTICAL, false)
        binding.rvBusRouteList.adapter = busRouteListAdapter
    }

    private fun getBusRouteList(strSrch: String) {
        MainRepository().getBusRouteList(strSrch).enqueue(object : Callback<BusRouteListItem> {
            override fun onResponse(call: Call<BusRouteListItem>, response: Response<BusRouteListItem>, ) {
                var res = response.body()
//                Log.e("YMC", "retrofit res: ${res}")
//                Log.e("YMC", "retrofit res item: ${res?.msgHeader?.headerMsg}")
//                Log.e("YMC", "retrofit res item: ${res?.msgBody?.itemList}")

//                res?.msgBody?.itemList?.forEach {
//                    Log.e("YMC","=====forRach: ${it}")
////                    vusRouteItemList.add(it)
//                }

                if(res?.msgBody?.itemList!=null && res?.msgBody?.itemList.size>0){
                    busRouteListAdapter.setItems(res.msgBody.itemList)
                }
            }

            override fun onFailure(call: Call<BusRouteListItem>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun moveBusRouteDetail(){
        /** 버스도착정보 디테일 */
        MainActivity.getInstance().moveFragment(requireActivity(), BusRouteDetailFragment(), true)
    }
}