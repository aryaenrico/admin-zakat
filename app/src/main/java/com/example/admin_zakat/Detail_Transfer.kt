package com.example.admin_zakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.admin_zakat.data.DaftarTransfer
import com.example.admin_zakat.databinding.ActivityDetailTransferBinding

class Detail_Transfer : AppCompatActivity() {
    private lateinit var binding:ActivityDetailTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val param =intent.getParcelableExtra<DaftarTransfer>("transfer") as DaftarTransfer
        binding.tvNama.text = param.nama_pembayar
        binding.tvJumlah.text =param.total_pembayaran

        Glide.with(this@Detail_Transfer)
            .load("https://indrasela.net/mobile_zakat/foto/${param.foto}")
            .into(binding.gambar)
    }
}