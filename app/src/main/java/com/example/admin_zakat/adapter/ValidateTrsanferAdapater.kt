package com.example.admin_zakat.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin_zakat.Detail_Transfer
import com.example.admin_zakat.R
import com.example.admin_zakat.data.DaftarTransfer
import java.text.NumberFormat
import java.util.Locale
import java.util.StringTokenizer

class ValidateTrsanferAdapater (private val listData: ArrayList<DaftarTransfer>): RecyclerView.Adapter<ValidateTrsanferAdapater.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ValidateTrsanferAdapater.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.data_validate_transfer, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ValidateTrsanferAdapater.ListViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load("https://indrasela.net/mobile_zakat/foto/${listData[position].foto}")
            .into(holder.imgPhoto)
        holder.tvName.text = listData[position].nama_pembayar
        holder.tvTotal.text = token(currency(listData[position].total_pembayaran.toInt()))
        holder.tvTipeZakat.text ="Jenis Zakat: "+listData[position].tipe_zakat
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,Detail_Transfer::class.java)
            intent.putExtra("transfer",listData[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
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
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvTotal: TextView = itemView.findViewById(R.id.tv_total_transfer)
        var tvTipeZakat: TextView = itemView.findViewById(R.id.tipe_zakat)
    }
}