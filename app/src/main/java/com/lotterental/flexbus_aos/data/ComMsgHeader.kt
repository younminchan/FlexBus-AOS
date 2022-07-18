package com.lotterental.flexbus_aos.data


import com.google.gson.annotations.SerializedName

data class ComMsgHeader(
    @SerializedName("headerCd") val headerCd: String,
    @SerializedName("headerMsg") val headerMsg: String,
    @SerializedName("itemCount") val itemCount: Int
)