package com.example.Quiz_App

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.R

enum class QuizScreen(@StringRes val title: Int) {
    Login(title = R.string.login_screen),
    QuizStart(title = R.string.quiz_start_screen),
    Question(title = R.string.question_screen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: QuizScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = "Quiz App")
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton (onClick = {
                    navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        windowInsets = WindowInsets(0.dp)
    )
}

@Composable
fun QuizApp(
    navController: NavHostController = rememberNavController(),
    userRepository: UserRepository
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = QuizScreen.valueOf(
        backStackEntry?.destination?.route ?: QuizScreen.Login.name
    )

    Scaffold (
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QuizScreen.Login.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = QuizScreen.Login.name) {
                LoginScreen(userRepository, {username, password ->
                    if (userRepository.ValidateCredential(username, password) || true)
                        navController.navigate(QuizScreen.QuizStart.name)
                    else
                        return@LoginScreen "Incorrect username or password!"

                    return@LoginScreen ""
                })
            }
            composable(route = QuizScreen.QuizStart.name) {
                QuizStart({},{})
            }
            composable(route = QuizScreen.Question.name) {

            }
        }
    }
}