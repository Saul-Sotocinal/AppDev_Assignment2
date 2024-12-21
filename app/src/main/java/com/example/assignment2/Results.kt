package com.example.assignment2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun ResultsScreen(correctAnswersCount: Int, quizSize: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "RESULTS")
        Text(text = "${correctAnswersCount}/${quizSize}")
        val message: String = if (correctAnswersCount / quizSize >= 1.0f)
            "Perfect Score!" else if (correctAnswersCount / quizSize >= 0.6f)
            "Congrats! That's a passing grade." else "Better luck next time..."
        Text(text = message)
    }
}