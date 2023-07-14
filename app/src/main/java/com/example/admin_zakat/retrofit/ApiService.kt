package com.example.admin_zakat.retrofit
import com.example.admin_zakat.data.DataTransfer
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("validate_transfer.php")
    fun getData(): Call<DataTransfer>
}