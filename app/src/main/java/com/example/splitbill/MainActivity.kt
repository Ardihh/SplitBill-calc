package com.example.splitbill

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputTotal = findViewById<EditText>(R.id.inputTotal)
        val inputOrang = findViewById<EditText>(R.id.inputOrang)
        val inputPajak = findViewById<EditText>(R.id.inputPajak)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val txtHasil = findViewById<TextView>(R.id.txtHasil)

        // Formatter Rupiah
        val formatRupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

        btnHitung.setOnClickListener {

            val total = inputTotal.text.toString().toDoubleOrNull()
            val orang = inputOrang.text.toString().toIntOrNull()
            val pajak = inputPajak.text.toString().toDoubleOrNull() ?: 0.0

            // Validasi aman
            if (total == null || orang == null) {
                txtHasil.text = "⚠️ Input harus angka!"
                return@setOnClickListener
            }

            if (orang == 0) {
                txtHasil.text = "⚠️ Jumlah orang tidak boleh 0!"
                return@setOnClickListener
            }

            val totalAkhir = total + (total * pajak / 100)
            val perOrang = totalAkhir / orang

            val totalFormat = formatRupiah.format(totalAkhir)
            val perOrangFormat = formatRupiah.format(perOrang)

            // Pesan dinamis 😏
            val pesan = when {
                perOrang < 20000 -> "Murah banget 😁"
                perOrang < 50000 -> "Masih aman 😌"
                else -> "Dompet menjerit 😭"
            }

            txtHasil.text = """
                💰 Total Akhir: $totalFormat
                👥 Per Orang: $perOrangFormat
                
                $pesan
            """.trimIndent()
        }
    }
}