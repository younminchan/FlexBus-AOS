package com.lotterental.flexbus_aos.network

import com.lotterental.flexbus_aos.data.BusRouteDetatilItem
import com.lotterental.flexbus_aos.data.BusRouteListItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    @Headers("Content-Type: application/json")
    @GET("busRouteInfo/getBusRouteList")
    fun getBusRouteList(
        @Query("ServiceKey") ServiceKey: String,
        @Query("strSrch") strSrch: String,
        @Query("resultType") resultType: String,
    ): Call<BusRouteListItem>

    @Headers("Content-Type: application/json")
    @GET("arrive/getArrInfoByRouteAll")
    fun getArrInfoByRouteAll(
        @Query("ServiceKey") ServiceKey: String,
        @Query("busRouteId") busRouteId: String,
        @Query("resultType") resultType: String,
    ): Call<BusRouteDetatilItem>

    companion object {
        var networkService: RetrofitService? = null
        var baseUrl = "http://ws.bus.go.kr/api/rest/"

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