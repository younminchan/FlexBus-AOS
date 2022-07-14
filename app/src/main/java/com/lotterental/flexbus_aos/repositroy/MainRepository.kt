package com.lotterental.flexbus_aos.repositroy

import com.lotterental.flexbus_aos.network.RetrofitService
import org.json.JSONObject
import retrofit2.Call
import java.util.ArrayList

class MainRepository {

    /** 노선번호에 해당하는 노선 목록 조회 */
    fun getBusRouteList(): Call<ArrayList<JSONObject>> {
        return RetrofitService.getInstance().getBusRouteList()
    }


}