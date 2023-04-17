package com.gajeks.electronicjournal.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gajeks.electronicjournal.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntranceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
    }
}