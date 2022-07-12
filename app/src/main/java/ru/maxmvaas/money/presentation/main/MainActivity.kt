package ru.maxmvaas.money.presentation.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import ru.maxmvaas.money.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = Color.BLACK
        }
    }

}