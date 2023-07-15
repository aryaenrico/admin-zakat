package com.example.admin_zakat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin_zakat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        binding.ivTransaksi.setOnClickListener{
            val moveIntent = Intent(this@MainActivity, daftar_transfer::class.java)
            startActivity(moveIntent)
        }

    }
}