package com.noyal.spaceapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noyal.spaceapp.databinding.ActivityMainBinding
import dev.chrisbanes.insetter.Insetter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Insetter.CONSUME_AUTO
    }
}