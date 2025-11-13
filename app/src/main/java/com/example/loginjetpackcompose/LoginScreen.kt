package com.example.loginjetpackcompose

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen() {

    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val onLogin = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dbFirebase = Firebase.firestore

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Bienvenido",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                placeholder = { Text(text = "Nombre de Usuario") },
                value = userName.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    if (it.length <= 25) {
                        userName.value = it
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                placeholder = { Text(text = "Contraseña") },
                value = password.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        })
                    {
                        Icon(
                            imageVector = if (passwordVisibility.value) {
                                Icons.Default.Check
                            } else {
                                Icons.Default.Close
                            },
                            contentDescription = " "
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

            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 3.dp)
                    .height(70.dp),
                onClick = {
                    if (userName.value.isBlank() || password.value.isBlank()) {
                        Toast.makeText(
                            context, "Por favor ingrese sus credenciales",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        onLogin.value = true
                    }

                }

            ) {
                Text(text = "Iniciar Sesión")
            }

        }
        if (onLogin.value) {
            dbFirebase.collection("usuarios").
            document(userName.value).
            get().
            addOnSuccessListener { usuario ->
                if (usuario != null) {
                    val pwd = usuario.get("password")

                    if (pwd!!.equals(password.value)) {
                        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, "usuario no existe", Toast.LENGTH_SHORT).show()
                    }
                }
            }//addOnSuccessListener
            onLogin.value = false
        }//OnLogin
    }
}