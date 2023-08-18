package com.example.admin_zakat.retrofit
import com.example.admin_zakat.data.DataTransfer
import com.example.admin_zakat.data.Response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("validate_transfer.php")
    fun getData(): Call<DataTransfer>

    @FormUrlEncoded
    @POST("process_transfer.php")
    fun processSetoran(@Field("id_pembayaran") id_pembayaran: Int,
                       @Field("id_pembayar") id_pembayar: Int,
                       @Field("tgl_penyerahan") tgl_penyerahan: String,
                       @Field("pembayaran_uang") pembayaran_uang: Int,
                       @Field("jumlah_tanggungan") jumlah_tanggugan: Int,
                       @Field("total_pembayaran") total_pembayaran: String,
                       @Field("foto") foto: String,
                       @Field("jenis_zakat") jenis_zakat:Int,
                       @Field("jenis_sumbangan") jenis_sumbangan:Int,
                       @Field("total_sumbangan") total_sumbangan:Int
    ):Call<Response>

    @FormUrlEncoded
    @POST("reject.php")
    fun reject(@Field("id") id_transaksi:Int):Call<Response>
}