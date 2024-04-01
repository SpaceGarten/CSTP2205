//package com.example.mathproblem
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun QuizScreen(viewModel: QuizViewModel) {
//    val question by viewModel.currentQuestion.observeAsState()
//    val score by viewModel.score.observeAsState(0)
//
//    var userAnswer by remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        question?.let { mathQuestion ->
//            Text(
//                "Solve the following: ${mathQuestion.firstNumber} ${mathQuestion.operation} ${mathQuestion.secondNumber}",
//                style = MaterialTheme.typography.headlineMedium
//            )
//            OutlinedTextField(
//                value = userAnswer,
//                onValueChange = { userAnswer = it },
//                label = { Text("Your answer") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//            )
//            Button(onClick = {
//                try {
//                    viewModel.submitAnswer(userAnswer.toInt())
//                    userAnswer = "" // Reset answer after submission
//                } catch (e: NumberFormatException) {
//                    Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
//                }
//            }) {
//                Text("Submit")
//            }
//            Text("Your score: $score", style = MaterialTheme.typography.bodyLarge)
//        } ?: Text("Loading...", style = MaterialTheme.typography.headlineMedium)
//    }
//}
