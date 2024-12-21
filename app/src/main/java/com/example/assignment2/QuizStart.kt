package com.example.Quiz_App

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun QuizStart(
    onStartMathQuizClicked: () -> Unit,
    onStartColorQuizClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "ABOUT")
        Text(text = "This is a quiz app. once you start one of the quizzes, " +
                "a question will appear and you will have to select the correct " +
                "answer to get a point.")
        Button(onClick = onStartMathQuizClicked) {
            Text(text = "Start Math Quiz")
        }
        Button(onClick = onStartColorQuizClicked) {
            Text(text = "Start Hex Color Code Quiz")
        }
    }
}