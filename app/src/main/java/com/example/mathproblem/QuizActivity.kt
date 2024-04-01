package com.example.mathproblem

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.mathproblem.ui.theme.MathProblemTheme

class QuizActivity : ComponentActivity() {

    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        // Get the quiz type from the intent
        val quizType = intent.getStringExtra("QUIZ_TYPE") ?: "addition"
        viewModel.generateQuestion(quizType)

        setContent {
            MathProblemTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    QuizScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun QuizScreen(viewModel: QuizViewModel) {
    // Use observeAsState to convert LiveData to State
    val question by viewModel.currentQuestion.observeAsState()
    val score by viewModel.score.observeAsState(0)

    // Use remember and mutableStateOf to hold user answer state
    var userAnswer by remember { mutableStateOf("") }
    val context = LocalContext.current as Context

    Column(modifier = Modifier.padding(16.dp)) {
        question?.let { mathQuestion ->
            Text(
                "Solve the following: ${mathQuestion.firstNumber} ${mathQuestion.operation} ${mathQuestion.secondNumber}",
                style = MaterialTheme.typography.headlineMedium
            )
            OutlinedTextField(
                value = userAnswer,
                onValueChange = { userAnswer = it },
                label = { Text("Your answer") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(onClick = {
                try {
                    viewModel.submitAnswer(userAnswer.toInt())
                    userAnswer = "" // Reset answer after submission
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Submit")
            }
            Text("Your score: $score", style = MaterialTheme.typography.bodyLarge)
        } ?: Text("Loading...", style = MaterialTheme.typography.headlineMedium)
    }
}
