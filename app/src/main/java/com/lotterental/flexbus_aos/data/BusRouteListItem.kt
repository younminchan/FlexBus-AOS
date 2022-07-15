package com.lotterental.flexbus_aos.data


import android.content.ClipData
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class BusRouteListItem(
    @SerializedName("comMsgHeader") val comMsgHeader: ComMsgHeader,
    @SerializedName("msgHeader") val msgHeader: MsgHeader,
    @SerializedName("msgBody") val msgBody: MsgBody
)

data class ComMsgHeader(
    @SerializedName("errMsg") val errMsg: Any,
    @SerializedName("requestMsgID") val requestMsgID: Any,
    @SerializedName("responseMsgID") val responseMsgID: Any,
    @SerializedName("responseTime") val responseTime: Any,
    @SerializedName("returnCode") val returnCode: Any,
    @SerializedName("successYN") val successYN: Any
)

data class MsgHeader(
    @SerializedName("headerCd") val headerCd: String,
    @SerializedName("headerMsg") val headerMsg: String,
    @SerializedName("itemCount") val itemCount: Int
)

data class MsgBody(
    @SerializedName("itemList") val itemList: ArrayList<BusRouteItem> = ArrayList<BusRouteItem>() //각 항목 리스트
)


data class BusRouteItem(
    @SerializedName("busRouteAbrv")  val busRouteAbrv: String,
    @SerializedName("busRouteId")    val busRouteId: String,    //노선 ID
    @SerializedName("busRouteNm")    val busRouteNm: String,    //노선명(버스번호)
    @SerializedName("corpNm")        val corpNm: String,        //운수사명
    @SerializedName("firstBusTm")    val firstBusTm: String,    //금일 첫차시간
    @SerializedName("lastBusTm")     val lastBusTm: String,     //금일 막차시간
    @SerializedName("routeType")     val routeType: String,     //노선 유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    @SerializedName("stStationNm")   val stStationNm: String,   //기점
    @SerializedName("edStationNm")   val edStationNm: String,   //종점
    @SerializedName("term")          val term: String,          //배차간격(분)
    @SerializedName("firstLowTm")    val firstLowTm: String,
    @SerializedName("lastBusYn")     val lastBusYn: String,
    @SerializedName("lastLowTm")     val lastLowTm: String,
    @SerializedName("length")        val length: String
)
