package com.example.pokemonapp.presentation.screens

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonapp.R
import com.example.pokemonapp.data.Dependencies

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Dependencies.init(this)
    }
}