package com.example.loginjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme
import com.example.loginjetpackcompose.core.navigation.NavigationWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginJetpackComposeTheme {
                NavigationWrapper()
            }
        }
    }
}
