package com.dicoding.mynavdrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dicoding.mynavdrawer.ui.theme.MyNavDrawerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNavDrawerTheme {
                MyNavDrawerApp()
            }
        }
    }
}