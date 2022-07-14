package com.lotterental.flexbus_aos.repositroy

import com.lotterental.flexbus_aos.ConfigValue
import com.lotterental.flexbus_aos.data.BusRouteListItem
import com.lotterental.flexbus_aos.network.RetrofitService
import org.json.JSONObject
import retrofit2.Call
import java.util.ArrayList

class MainRepository {

    /** 노선번호에 해당하는 노선 목록 조회 (getBusRouteList) */
    fun getBusRouteList(strSrch: String): Call<BusRouteListItem> {
        return RetrofitService.getInstance().getBusRouteList(ConfigValue.ServiceKey, strSrch, "json")
    }




}