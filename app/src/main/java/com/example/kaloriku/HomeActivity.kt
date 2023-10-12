package com.example.kaloriku

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kaloriku.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Ambil data dari SharedPreferences
        val name = sharedPreferences.getString("name", "")
        val currentWeight = sharedPreferences.getString("currentWeight", "")
        val targetWeight = sharedPreferences.getString("targetWeight", "")
        val dietGoal = sharedPreferences.getString("dietGoal", "")
        val weightUnit = sharedPreferences.getString("weightUnit", "")
        val targetDate = sharedPreferences.getString("targetDate", "")

        // Menampilkan data
        binding.textViewNama.text = "Nama: $name"
        binding.textViewBeratSekarang.text = "Berat badan saat ini: $currentWeight $weightUnit"
        binding.textViewBeratTarget.text = "Berat badan yang diinginkan: $targetWeight $weightUnit"
        binding.textViewTujuanDiet.text = "Tujuan diet: $dietGoal"
        binding.textViewTargetTanggal.text = "Tanggal target capaian: $targetDate"

        // tanggal hari ini
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        binding.textViewTanggal.text = "Tanggal hari ini: $currentDate"


        val inputCaloriesStr = sharedPreferences.getString("inputCalories", "0")
        val inputCalories = inputCaloriesStr?.toInt() ?: 0
        val foodName = sharedPreferences.getString("foodName", "")
        val mealType = sharedPreferences.getString("mealType", "")

        val workoutType = sharedPreferences.getString("workoutType", "")
        val startTime = sharedPreferences.getString("startTime", "")
        val duration = sharedPreferences.getString("duration", "")
        val caloriesBurned = sharedPreferences.getString("caloriesBurned", "")
        val outputCaloriesStr = sharedPreferences.getString("outputCalories", "0")
        val outputCalories = outputCaloriesStr?.toInt() ?: 0

        // Menghitung kalori tersisa
        val targetCaloriesStr = sharedPreferences.getString("targetCalories", "0")
        val targetCalories = targetCaloriesStr?.toInt() ?: 0
        val remainingCalories = targetCalories - (inputCalories - outputCalories)

        // Menampilkan kalori tersisa dan rincian
        binding.textViewNamaMakanan.text = "Makanan: $foodName"
        binding.textViewKaloriIn.text = "Jenis kalori in: $mealType"
        binding.textViewKaloriMasuk.text = "Jumlah kalori masuk: $inputCalories"
        binding.textViewSisaKalori.text = "$remainingCalories"

        binding.textViewNamaWorkout.text = "Workout: $workoutType"
        binding.textViewMulaiWorkout.text = "Waktu mulai workout: $startTime"
        binding.textViewDurasi.text = "Durasi workout: $duration"
        binding.textViewCaloriesBurned.text = "Jumlah kalori keluar: $outputCalories"

        binding.btnInputData.setOnClickListener {
            val intentToInputActivity =
                Intent(this, InputActivity::class.java)
            startActivity(intentToInputActivity)
        }

        binding.buttonDeleteInputCalories.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("foodName")
            editor.remove("mealType")
            editor.remove("calories")
            editor.remove("inputCalories")
            editor.apply()
            updateCaloriesData()
        }

        binding.buttonDeleteOutputCalories.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("workoutType")
            editor.remove("startTime")
            editor.remove("duration")
            editor.remove("caloriesBurned")
            editor.remove("outputCalories")
            editor.apply()
            updateCaloriesData()
        }

    }

    private fun updateCaloriesData() {

        // mengambil data kalori masuk dan keluar dari SharedPreferences
        val inputCaloriesStr = sharedPreferences.getString("inputCalories", "0")
        val inputCalories = inputCaloriesStr?.toInt() ?: 0

        val outputCaloriesStr = sharedPreferences.getString("outputCalories", "0")
        val outputCalories = outputCaloriesStr?.toInt() ?: 0

        // menghitung sisa kalori
        val targetCaloriesStr = sharedPreferences.getString("targetCalories", "0")
        val targetCalories = targetCaloriesStr?.toInt() ?: 0
        val remainingCalories = targetCalories - (inputCalories - outputCalories)

        binding.textViewKaloriMasuk.text = "Jumlah Kalori Masuk: $inputCalories"
        binding.textViewCaloriesBurned.text = "Jumlah Kalori Keluar: $outputCalories"
        binding.textViewSisaKalori.text = "$remainingCalories"
    }

}