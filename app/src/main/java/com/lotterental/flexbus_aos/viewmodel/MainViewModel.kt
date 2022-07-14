package com.lotterental.flexbus_aos.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lotterental.flexbus_aos.data.BusRouteListItem
import com.lotterental.flexbus_aos.data.Item
import com.lotterental.flexbus_aos.repositroy.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var mainRepository: MainRepository = MainRepository()

    //getBusRouteList (노선번호에 해당하는 노선 목록 조회)
    private val _retrofitBusRouteItemList = MutableLiveData<ArrayList<Item>>()
    val retrofitBusRouteItemList: LiveData<ArrayList<Item>>
        get() = retrofitBusRouteItemList

    fun getBusRouteList(strSrch: String) {
        mainRepository.getBusRouteList(strSrch).enqueue(object : Callback<BusRouteListItem> {
            override fun onResponse(call: Call<BusRouteListItem>, response: Response<BusRouteListItem>, ) {
                var res = response.body()
                Log.e("YMC", "retrofit res: ${res}")
                Log.e("YMC", "retrofit res item: ${res?.msgHeader?.headerMsg}")
                Log.e("YMC", "retrofit res item: ${res?.msgBody?.itemList}")


                _retrofitBusRouteItemList.postValue(res?.msgBody?.itemList)
            }

            override fun onFailure(call: Call<BusRouteListItem>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    /** ViewModel Singleton */
    companion object{
        private lateinit var instance: MainViewModel

        @MainThread
        fun getInstance(): MainViewModel{
            instance = if(::instance.isInitialized) instance else MainViewModel()
            return instance
        }
    }
}