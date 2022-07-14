package com.lotterental.flexbus_aos.network

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    //    @Headers("Content-Type: application/json")
    @GET("getBusRouteList")
    fun getBusRouteList(
//        @Path("category") category:String
    ): Call<ArrayList<JSONObject>>

    companion object {
        var networkService: RetrofitService? = null
        var baseUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/"

        fun getInstance(): RetrofitService {
            if (networkService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                networkService = retrofit.create(RetrofitService::class.java)
            }
            return networkService!!
        }
    }
}