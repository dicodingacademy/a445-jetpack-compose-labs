package com.dicoding.jetheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dicoding.jetheroes.ui.theme.JetHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetHeroesTheme {
                JetHeroesApp()
            }
        }
    }
}