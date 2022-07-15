package com.lotterental.flexbus_aos.data


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
    @SerializedName("itemList") val itemList: ArrayList<Item> = ArrayList<Item>()
)


data class Item(
    @SerializedName("busRouteAbrv") val busRouteAbrv: String,
    @SerializedName("busRouteId") val busRouteId: String,
    @SerializedName("busRouteNm") val busRouteNm: String,
    @SerializedName("corpNm") val corpNm: String,
    @SerializedName("edStationNm") val edStationNm: String,
    @SerializedName("firstBusTm") val firstBusTm: String,
    @SerializedName("firstLowTm") val firstLowTm: String,
    @SerializedName("lastBusTm") val lastBusTm: String,
    @SerializedName("lastBusYn") val lastBusYn: String,
    @SerializedName("lastLowTm") val lastLowTm: String,
    @SerializedName("length") val length: String,
    @SerializedName("routeType") val routeType: String,
    @SerializedName("stStationNm") val stStationNm: String,
    @SerializedName("term") val term: String
)
