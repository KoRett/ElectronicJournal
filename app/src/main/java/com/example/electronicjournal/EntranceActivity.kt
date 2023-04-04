package com.example.electronicjournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EntranceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_entrance)
    }
}