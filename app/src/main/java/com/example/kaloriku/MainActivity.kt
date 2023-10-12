package com.example.kaloriku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.kaloriku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            BtnGetStarted.setOnClickListener(){
                val intentToGetStartedActivity =
                    Intent(this@MainActivity, GetStartedActivity::class.java)
                startActivity(intentToGetStartedActivity)
            }
        }

    }
}