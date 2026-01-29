package com.example.slemancareplus.data.model

data class PengajuanRequest(
    val nik: String,
    val nama: String,
    val alamat: String,
    val jenis_bantuan: String,
    val alasan: String,
    val dokumen: String // sementara kirim URI string
)
