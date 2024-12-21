package com.example.Quiz_App

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun LoginScreen(
    userRepository: UserRepository,
    onLoginClicked: (username: String, password:String) -> String
) {
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var signedInState by remember { mutableStateOf("") }
    var Credentials by remember { mutableStateOf("") }

    Surface (modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = usernameInput,
                onValueChange = {usernameInput = it},
                placeholder = { Text("username") }
            )
            TextField(
                value = passwordInput,
                onValueChange = {passwordInput = it},
                placeholder = { Text("password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Text(text = signedInState)
            Button(onClick = {
                signedInState = onLoginClicked(usernameInput, passwordInput)
            }) { Text(text = "Login") }

            Text(text = Credentials)
            Button(onClick = {
                Credentials = userRepository.GetTestCredentials()
            }) { Text(text = "Get Test Credentials") }
        }
    }
}