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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.GetHexColorsQuiz
import com.example.assignment2.GetMathQuiz
import com.example.assignment2.Question
import com.example.assignment2.R
import com.example.assignment2.ResultsScreen
import com.example.assignment2.questionScreen

enum class QuizScreen(@StringRes val title: Int) {
    LoginScreen(title = R.string.login_screen),
    QuizStartScreen(title = R.string.quiz_start_screen),
    QuestionScreen(title = R.string.question_screen),
    ResultsScreen(title = R.string.results_screen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: QuizScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navigateHome: () -> Unit,
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
                    if (currentScreen == QuizScreen.QuestionScreen ||
                        currentScreen == QuizScreen.ResultsScreen )
                        navigateHome()
                    else
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
        backStackEntry?.destination?.route ?: QuizScreen.LoginScreen.name
    )
    var currentQuestion by remember { mutableIntStateOf(0) }
    var correctAnswersCount by remember { mutableIntStateOf(0) }
    var questions: List<Question> by remember { mutableStateOf(listOf()) }

    Scaffold (
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                navigateHome = {
                    currentQuestion = 0
                    correctAnswersCount = 0
                    navController.popBackStack(QuizScreen.QuizStartScreen.name, false)
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QuizScreen.LoginScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = QuizScreen.LoginScreen.name) {
                LoginScreen(userRepository, {username, password ->
                    if (userRepository.ValidateCredential(username, password) || true)
                        navController.navigate(QuizScreen.QuizStartScreen.name)
                    else
                        return@LoginScreen "Incorrect username or password!"

                    return@LoginScreen ""
                })
            }
            composable(route = QuizScreen.QuizStartScreen.name) {
                QuizStartScreen({
                    questions = GetMathQuiz()
                    navController.navigate(QuizScreen.QuestionScreen.name)
                },{
                    questions = GetHexColorsQuiz()
                    navController.navigate(QuizScreen.QuestionScreen.name)
                })
            }
            composable(route = QuizScreen.QuestionScreen.name) {
                if (currentQuestion < questions.size) {
                    questionScreen(questions.get(currentQuestion), { isCorrectAnswer ->
                        if (isCorrectAnswer)
                            correctAnswersCount += 1
                        currentQuestion += 1
                        navController.navigate(QuizScreen.QuestionScreen.name)
                    })
                }
                else
                    navController.navigate(QuizScreen.ResultsScreen.name)
            }
            composable(route = QuizScreen.ResultsScreen.name) {
                ResultsScreen(5, 5)
            }
        }
    }
}