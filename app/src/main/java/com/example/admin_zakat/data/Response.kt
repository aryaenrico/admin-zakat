package com.example.admin_zakat.data

import com.google.gson.annotations.SerializedName

data class Response(
    @field:SerializedName("status")
    var status:String,
    @field:SerializedName("message")
    var message:String
)
