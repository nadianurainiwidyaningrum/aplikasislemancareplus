package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CariDataScreen(navController: NavHostController) {

    var keyword by remember { mutableStateOf("") }
    var sudahCari by remember { mutableStateOf(false) }

    val dataWarga = remember {
        listOf(
            Warga(
                nama = "Siti Aminah",
                alamat = "Mlati, Sleman",
                pekerjaan = "Ibu Rumah Tangga",
                penghasilan = 800000,
                jumlahAnak = 3,
                jumlahKendaraan = 1,
                menerimaBantuan = true,
                jenisBantuan = "PKH"
            ),
            Warga(
                nama = "Budi Santoso",
                alamat = "Depok, Sleman",
                pekerjaan = "Buruh Harian",
                penghasilan = 1200000,
                jumlahAnak = 2,
                jumlahKendaraan = 1,
                menerimaBantuan = true,
                jenisBantuan = "BLT"
            ),
            Warga(
                nama = "Rina Lestari",
                alamat = "Gamping, Sleman",
                pekerjaan = "Karyawan Swasta",
                penghasilan = 3000000,
                jumlahAnak = 1,
                jumlahKendaraan = 2,
                menerimaBantuan = false
            ),
            Warga(
                nama = "Andi Pratama",
                alamat = "Ngaglik, Sleman",
                pekerjaan = "Petani",
                penghasilan = 1000000,
                jumlahAnak = 4,
                jumlahKendaraan = 0,
                menerimaBantuan = true,
                jenisBantuan = "Beasiswa Sleman Pintar"
            )
        )
    }

    val hasil = dataWarga.filter {
        keyword.isNotEmpty() &&
                (it.nama.contains(keyword, true) ||
                        it.alamat.contains(keyword, true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Cari Data Bantuan",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = keyword,
            onValueChange = { keyword = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Masukkan nama atau alamat") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { sudahCari = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Cari", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (sudahCari) {
            if (hasil.isEmpty()) {
                Text(
                    text = "Data tidak ditemukan",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(hasil) { warga ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = if (warga.menerimaBantuan)
                                        "MENERIMA BANTUAN"
                                    else
                                        "TIDAK MENERIMA BANTUAN",
                                    fontWeight = FontWeight.Bold,
                                    color = if (warga.menerimaBantuan)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.error
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                DetailRow("Nama", warga.nama)
                                DetailRow("Alamat", warga.alamat)
                                DetailRow("Pekerjaan", warga.pekerjaan)
                                DetailRow(
                                    "Penghasilan",
                                    "Rp ${warga.penghasilan} / bulan"
                                )
                                DetailRow("Jumlah Anak", warga.jumlahAnak.toString())
                                DetailRow(
                                    "Jumlah Kendaraan",
                                    warga.jumlahKendaraan.toString()
                                )
                                DetailRow(
                                    "Menerima Bantuan",
                                    if (warga.menerimaBantuan) "YA" else "TIDAK"
                                )

                                if (warga.menerimaBantuan) {
                                    DetailRow("Jenis Bantuan", warga.jenisBantuan)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1.2f),
            fontWeight = FontWeight.SemiBold
        )
        Text(text = ": ")
        Text(
            text = value,
            modifier = Modifier.weight(2f)
        )
    }
}

data class Warga(
    val nama: String,
    val alamat: String,
    val pekerjaan: String,
    val penghasilan: Int,
    val jumlahAnak: Int,
    val jumlahKendaraan: Int,
    val menerimaBantuan: Boolean,
    val jenisBantuan: String = "-"
)
