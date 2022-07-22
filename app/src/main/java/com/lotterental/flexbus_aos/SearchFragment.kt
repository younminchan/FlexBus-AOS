package com.lotterental.flexbus_aos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import java.util.*

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var busRouteListAdapter : BusRouteListAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
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

        /** 노선번호 검색버튼 */
        binding.tvSearchInput.setOnClickListener {
            var searchBusNum:String = binding.etSearch.text.toString()
            if(!searchBusNum.isNullOrEmpty()) {
                getBusRouteList(searchBusNum)

                Toast.makeText(App.activity, "${searchBusNum} 노선번호 검색", Toast.LENGTH_SHORT).show()
            }
        }
        /** 노선번호 검색입력 */
        binding.etSearch.apply {
            setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
//                        binding.tvSearchInput.performClick() //강제클릭
                        Utils.hideKeyboard()
                        return true
                    } else {

                        return false
                    }
                }
            })

            filters = arrayOf(Utils.filterAlphaNumSpace)

            addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                private var timer: Timer = Timer()
                private val DELAY: Long = 1000 // Milliseconds

                override fun afterTextChanged(s: Editable?) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(object : TimerTask() {
                            override fun run() {
                                // TODO: Do what you need here (refresh list).
                                // You will probably need to use
                                // runOnUiThread(Runnable action) for some
                                // specific actions (e.g., manipulating views).

                                var searchText = binding.etSearch.text
                                if(!searchText.isNullOrEmpty()){
                                    getBusRouteList("${searchText}")
                                }
                            }
                        }, DELAY)
                }
            })
        }

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
            override fun onResponse(
                call: Call<BusRouteListItem>,
                response: Response<BusRouteListItem>,
            ) {
                if(response.isSuccessful){
                    var res = response.body()
//                Log.e("YMC", "retrofit res: ${res}")
//                Log.e("YMC", "retrofit res item: ${res?.msgHeader?.headerMsg}")
//                Log.e("YMC", "retrofit res item: ${res?.msgBody?.itemList}")

//                res?.msgBody?.itemList?.forEach {
//                    Log.e("YMC","=====forEach: ${it}")
////                    vusRouteItemList.add(it)
//                }

                    if(res?.msgBody?.itemList!=null && res?.msgBody?.itemList.size>0){
                        busRouteListAdapter.setItems(res.msgBody.itemList)
                    }
                }
            }

            override fun onFailure(call: Call<BusRouteListItem>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}