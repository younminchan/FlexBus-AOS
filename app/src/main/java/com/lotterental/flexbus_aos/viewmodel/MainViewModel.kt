package com.lotterental.flexbus_aos.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.lotterental.flexbus_aos.repositroy.MainRepository

class MainViewModel : ViewModel() {
    private var mainRepository: MainRepository = MainRepository()

//    //getBusRouteList (노선번호에 해당하는 노선 목록 조회)
//    private val _retrofitBusRouteItemList = MutableLiveData<ArrayList<BusRouteItem>>()
//    val retrofitBusRouteItemList: LiveData<ArrayList<BusRouteItem>>
//        get() = retrofitBusRouteItemList
//
//    fun getBusRouteList(strSrch: String) {
//        mainRepository.getBusRouteList(strSrch).enqueue(object : Callback<BusRouteListItem> {
//            override fun onResponse(call: Call<BusRouteListItem>, response: Response<BusRouteListItem>, ) {
//                var res = response.body()
//                Log.e("YMC", "retrofit res: ${res}")
//                Log.e("YMC", "retrofit res item: ${res?.msgHeader?.headerMsg}")
//                Log.e("YMC", "retrofit res item: ${res?.msgBody?.itemList}")
//
//
//                _retrofitBusRouteItemList.postValue(res?.msgBody?.itemList)
//            }
//
//            override fun onFailure(call: Call<BusRouteListItem>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }

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