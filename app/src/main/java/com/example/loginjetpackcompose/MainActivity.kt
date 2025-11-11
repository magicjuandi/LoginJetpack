package com.example.loginjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun showPreview(){
    LoginScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen() {
    Scaffold (modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            val username = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val passwordVisibility = remember { mutableStateOf(false) }
            val context = LocalContext.current

            Text(text = "Login",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(modifier = Modifier.fillMaxSize(0.8f),
            placeholder = {Text(text = "Username")},
            value = username.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    if (it.length <= 15) {
                        username.value = it
                    }
                })
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(modifier = Modifier.fillMaxSize(0.8f),
                placeholder = {Text(text = "Password")},
                value = password.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        })
                    {
                        Icon(
                            imageVector = (if (passwordVisibility.value) {
                                Icons.Default.Check
                            } else {
                                Icons.Default
                            }) as ImageVector,
                            contentDescription = ""
                        )

                    }
                },
                visualTransformation = if (passwordVisibility.value) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                onValueChange = {
                    if (it.length <= 15) {
                        password.value = it
                    }
                }
            )





        }
    }

}
