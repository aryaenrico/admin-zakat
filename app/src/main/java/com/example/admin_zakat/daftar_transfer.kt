package com.example.admin_zakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_zakat.adapter.ValidateTrsanferAdapater
import com.example.admin_zakat.data.DaftarTransfer
import com.example.admin_zakat.data.DataTransfer
import com.example.admin_zakat.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class daftar_transfer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_transfer)

        val client = ApiConfig.getApiService().getData()
        client.enqueue(object : Callback<DataTransfer> {
            override fun onResponse(
                call: Call<DataTransfer>,
                response: Response<DataTransfer>
            ) {

                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val rvHeroes = findViewById<RecyclerView>(R.id.rv_daftar_transfer)
                    rvHeroes.layoutManager = LinearLayoutManager(this@daftar_transfer)
                    rvHeroes.adapter = ValidateTrsanferAdapater(responseBody.data)
                    Toast.makeText(this@daftar_transfer,responseBody.data[0].foto,Toast.LENGTH_SHORT).show()

                } else {

                }
            }
            override fun onFailure(call: Call<DataTransfer>, t: Throwable) {
                Toast.makeText(this@daftar_transfer,t.message,Toast.LENGTH_SHORT).show()


            }
        })

    }
}