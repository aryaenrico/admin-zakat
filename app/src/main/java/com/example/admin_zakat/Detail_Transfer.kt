package com.example.admin_zakat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.admin_zakat.data.DaftarTransfer
import com.example.admin_zakat.data.Response
import com.example.admin_zakat.databinding.ActivityDetailTransferBinding
import com.example.admin_zakat.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import java.text.NumberFormat
import java.util.Locale
import java.util.StringTokenizer


class Detail_Transfer : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val param = intent.getParcelableExtra<DaftarTransfer>("transfer") as DaftarTransfer
        binding.tvNama.text = param.nama_pembayar
        binding.tvJumlah.text = token(currency(param.total_pembayaran.toInt()))

        Glide.with(this@Detail_Transfer)
            .load("https://indrasela.net/mobile_zakat/foto/${param.foto}")
            .into(binding.gambar)

        binding.btProses.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            var data:Int=0
           when(param.tipe_zakat){
               "mal"-> data =1
               "fidyah"->data=2
               "fitrah"->data=3
           }
            var dataSumbangan:Int=0;
            var temp = param?.jenis_sumbangan ?: "kosong";
            var totalSumbangan = param?.total_sumbangan ?: 0;
            when(temp){
                "infak"->dataSumbangan =4
                "sedekah"->dataSumbangan=5
                "kosong"->dataSumbangan =0;
            }

            val client = ApiConfig.getApiService().processSetoran(
                param.id_pembayaran,
                param.id_pembayar,
                param.tgl_penyerahan,
                param.pembayaran_uang,
                param.jumlah_tanggungan,
                param.total_pembayaran,
                param.foto,
                data,
                dataSumbangan ,
               totalSumbangan

            )

            client.enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {

                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                       if (responseBody.status =="sukses"){
                           Toast.makeText(this@Detail_Transfer,"data berhasil diproses",Toast.LENGTH_SHORT).show()
                           val intent = Intent(this@Detail_Transfer, daftar_transfer::class.java)
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                           startActivity(intent)
                       }
                    } else {

                    }
                    binding.progressBar.visibility = View.GONE
                }
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Toast.makeText(this@Detail_Transfer,t.message,Toast.LENGTH_SHORT).show()

                }
            })
        }

        binding.btnReject.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val client =ApiConfig.getApiService().reject(param.id_pembayaran)
            client.enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {

                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        if (responseBody.status =="sukses"){
                            Toast.makeText(this@Detail_Transfer,"data berhasil diproses",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Detail_Transfer, daftar_transfer::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Toast.makeText(this@Detail_Transfer,responseBody?.message,Toast.LENGTH_SHORT).show()
                    }
                    binding.progressBar.visibility = View.GONE
                }
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Toast.makeText(this@Detail_Transfer,t.message,Toast.LENGTH_SHORT).show()

                }
            })
        }
    }
    private fun currency(data:Int):String{
        val locale = Locale("IND", "ID")
        val formatter = NumberFormat.getCurrencyInstance(locale)
        return formatter.format(data)
    }

    private fun token(data: String): String {
        val tokenizer = StringTokenizer(data, ",")
        return tokenizer.nextToken()
    }
}