package com.example.kaloriku

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.kaloriku.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: com.example.kaloriku.databinding.ActivityInputBinding

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)


        val mealTypes = arrayOf("Sarapan", "Makan siang", "Makan malam", "Snack/lainnya")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mealTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMealType.adapter = adapter


        binding.buttonSubmit.setOnClickListener {
            val foodName = binding.editTextFoodName.text.toString()
            val mealType = binding.spinnerMealType.selectedItem.toString()
            val caloriesStr = binding.editTextCalories.text.toString()
            val calories = caloriesStr.toInt()

            // Ambil input kalori yg ada dari SharedPreferences
            val existingInputCaloriesStr = sharedPreferences.getString("inputCalories", "0")
            val existingInputCalories = existingInputCaloriesStr?.toInt() ?: 0

            val newInputCalories = existingInputCalories + calories

            val workoutType = binding.editTextNamaWorkout.text.toString()
            val startTime = binding.editTextMulaiWorkout.text.toString()
            val duration = binding.editTextDurasi.text.toString()
            val outputCalories = binding.editTextCaloriesBurned.text.toString().toInt()

            val existingOutputCaloriesStr = sharedPreferences.getString("outputCalories", "0")
            val existingOutputCalories = existingOutputCaloriesStr?.toInt() ?: 0

            val newOutputCalories = existingOutputCalories + outputCalories

            // Simpan di SharedPreferences
            val editor = sharedPreferences.edit()

            editor.putString("foodName", foodName)
            editor.putString("mealType", mealType)
            editor.putString("calories", caloriesStr)
            editor.putString("inputCalories", newInputCalories.toString())

            editor.putString("workoutType", workoutType)
            editor.putString("startTime", startTime)
            editor.putString("duration", duration)
            editor.putString("caloriesBurned", outputCalories.toString())
            editor.putString("outputCalories", newOutputCalories.toString())
            editor.apply()


            val intentToHomeActivity =
                Intent(this, HomeActivity::class.java)
            startActivity(intentToHomeActivity)
        }
    }
}