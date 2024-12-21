package com.example.assignment2

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
import com.example.Quiz_App.QuizApp
import com.example.Quiz_App.UserRepository
import com.example.assignment2.ui.theme.Assignment2Theme

val userRepository: UserRepository = UserRepository()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme (
                darkTheme = true
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuizApp(userRepository = userRepository)
                }
            }
        }
    }
}