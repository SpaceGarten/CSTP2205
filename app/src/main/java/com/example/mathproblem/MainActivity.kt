package com.example.mathproblem

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mathproblem.ui.theme.MathProblemTheme
import androidx.compose.foundation.layout.fillMaxSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathProblemTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    QuizOperationButtons(this)
                }
            }
        }
    }

    fun startQuizActivity(quizType: String) {
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra("QUIZ_TYPE", quizType)
        }
        startActivity(intent)
    }
}

@Composable
fun QuizOperationButtons(activity: MainActivity) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Addition Button
        MathOperationButton("Addition") {
            activity.startQuizActivity("addition")
        }
        // Subtraction Button
        MathOperationButton("Subtraction") {
            activity.startQuizActivity("subtraction")
        }
        // Multiplication Button
        MathOperationButton("Multiplication") {
            activity.startQuizActivity("multiplication")
        }
        // Division Button
        MathOperationButton("Division") {
            activity.startQuizActivity("division")
        }
    }
}

@Composable
fun MathOperationButton(operation: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(PaddingValues(vertical = 4.dp))
    ) {
        Text(text = operation)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MathProblemTheme {
        // Using LocalContext to retrieve the context within Preview
        val context = LocalContext.current
        if (context is MainActivity) {
            QuizOperationButtons(context)
        } else {
            Text("Preview not available in the current context.")
        }
    }
}
