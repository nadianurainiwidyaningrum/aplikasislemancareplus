package com.example.slemancareplus.api

import com.example.slemancareplus.data.model.*
import com.example.slemancareplus.model.AgenModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    /* ================= AUTH ================= */

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("no_hp") noHp: String,
        @Field("password") password: String
    ): Response<ApiResponse<ProfileModel>>

    @POST("register.php")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<ApiResponse<String>>

    @FormUrlEncoded
    @POST("forgot_password.php")
    suspend fun forgotPassword(
        @Field("username") username: String
    ): Response<ApiResponse<Nothing>>


    @FormUrlEncoded
    @POST("verify_otp.php")
    suspend fun verifyOtp(
        @Field("username") username: String,
        @Field("otp") otp: String
    ): retrofit2.Response<ApiResponse<Any>>


    @FormUrlEncoded
    @POST("reset_password.php")
    suspend fun resetPassword(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<ApiResponse<String>>


    /* ================= PROFILE ================= */

    @GET("profile.php")
    suspend fun getProfile(
        @Query("user_id") userId: String
    ): Response<ApiResponse<ProfileModel>>

    @FormUrlEncoded
    @POST("update_profile.php")
    suspend fun updateProfile(
        @Field("user_id") userId: String,
        @Field("nama") nama: String,
        @Field("no_hp") noHp: String,
        alamat: String
    ): Response<ApiResponse<String>>

    @FormUrlEncoded
    @POST("change_password.php")
    suspend fun changePassword(
        @Field("user_id") userId: String,
        @Field("old_password") oldPassword: String,
        @Field("new_password") newPassword: String
    ): Response<ApiResponse<String>>


    /* ================= CARI DATA ================= */

    @GET("cari_data.php")
    suspend fun cariData(
        @Query("keyword") keyword: String
    ): Response<ApiResponse<List<DataWarga>>>


    /* ================= AGEN ================= */

    @GET("agen.php")
    suspend fun getAgen(): Response<List<AgenModel>>


    /* ================= PENGAJUAN ================= */

    @POST("pengajuan.php")
    suspend fun kirimPengajuan(
        @Body request: PengajuanRequest
    ): Response<ApiResponse<String>>


    /* ================= PENGADUAN ================= */


    @POST("pengaduan.php")
    suspend fun kirimPengaduan(
        @Body request: PengaduanRequest
    ): Response<ApiResponse<String>>

    @GET("pengaduan.php")
    suspend fun getPengaduan(
        @Query("nik") nik: String
    ): Response<ApiResponse<List<RiwayatModel>>>

    @FormUrlEncoded
    @POST("hapus_pengaduan.php")
    suspend fun deletePengaduan(
        @Field("id") id: Int
    ): Response<ApiResponse<String>>


    /* ================= RIWAYAT (OPSIONAL JIKA DIGABUNG) ================= */

    @GET("riwayat.php")
    suspend fun getRiwayat(
        @Query("nik") nik: String
    ): Response<ApiResponse<List<RiwayatModel>>>

    @GET("panduan.php")
    suspend fun getPanduan(): retrofit2.Response<ApiResponse<List<PanduanModel>>>

    @GET("pemberdayaan.php")
    suspend fun getPemberdayaan():
            retrofit2.Response<ApiResponse<List<PemberdayaanModel>>>

    @GET("pengajuan.php")
    suspend fun getPengajuan(
        @Query("nik") nik: String
    ): Response<ApiResponse<List<RiwayatModel>>>

    @FormUrlEncoded
    @POST("hapus_pengajuan.php")
    suspend fun deletePengajuan(
        @Field("id") id: Int
    ): Response<ApiResponse<String>>

}
