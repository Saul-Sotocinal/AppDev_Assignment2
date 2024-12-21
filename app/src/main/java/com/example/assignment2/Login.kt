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

/**
 * Composable screen for logging into app.
 * @param userRepository database of user information.
 * @param onLoginClicked function called when login button is clicked.
 */
@Composable
fun LoginScreen(
    userRepository: UserRepository,
    onLoginClicked: (username: String, password:String) -> String
) {
    // Credential variables
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    // Variable displays error message for incorrect credentials
    var signedInError by remember { mutableStateOf("") }
    // Gives login credentials
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

            Text(text = signedInError)

            Button(onClick = {
                signedInError = onLoginClicked(usernameInput, passwordInput)
            }) { Text(text = "Login") }

            Text(text = Credentials)
            Button(onClick = {
                Credentials = userRepository.GetTestCredentials()
            }) { Text(text = "Get Test Credentials") }
        }
    }
}