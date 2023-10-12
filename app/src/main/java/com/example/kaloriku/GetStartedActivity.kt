package com.example.kaloriku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.kaloriku.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weightUnits = arrayOf("Kg", "lbs")
        val dietGoals = arrayOf("Cutting", "Bulking", "Maintaining")

        val weightUnitAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weightUnits)
        val dietGoalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dietGoals)

        binding.spinnerSatuanBerat.adapter = weightUnitAdapter
        binding.spinnerTujuanDiet.adapter = dietGoalAdapter

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextNama.text.toString()
            val weightUnit = binding.spinnerSatuanBerat.selectedItem.toString()
            val currentWeight = binding.editTextBaratSekarang.text.toString()
            val targetWeight = binding.editTextBeratTarget.text.toString()
            val dietGoal = binding.spinnerTujuanDiet.selectedItem.toString()
            val targetDate = "${binding.datePickerTargetTanggal.dayOfMonth}-${binding.datePickerTargetTanggal.month + 1}-${binding.datePickerTargetTanggal.year}"
            val targetCalories = binding.editTextTargetKalori.text.toString()

            // Simpan data ke SharedPreferences
            val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("currentWeight", currentWeight)
            editor.putString("targetWeight", targetWeight)
            editor.putString("dietGoal", dietGoal)
            editor.putString("targetDate", targetDate)
            editor.putString("targetCalories", targetCalories)
            editor.putString("weightUnit", weightUnit)
            editor.apply()

            val intentToHomeActivity =
                Intent(this, HomeActivity::class.java)
            startActivity(intentToHomeActivity)
        }
    }
}