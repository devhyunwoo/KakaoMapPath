package com.example.kakaomappath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kakaomappath.ui.navigation.MapNavHost
import com.example.kakaomappath.ui.theme.KakaoMapPathTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KakaoMapPathTheme {
                MapNavHost()
            }
        }
    }
}
