package com.example.mathproblem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class QuizViewModel : ViewModel() {
    private val _currentQuestion = MutableLiveData<MathQuestion>()
    val currentQuestion: LiveData<MathQuestion> = _currentQuestion

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

    // Generate a random math question based on the type
    fun generateQuestion(type: String) {
        val num1 = Random.nextInt(1, 100)
        val num2 = Random.nextInt(1, 100)
        val correctAnswer = when (type) {
            "addition" -> num1 + num2
            "subtraction" -> num1 - num2
            "multiplication" -> num1 * num2
            "division" -> if (num2 != 0) num1 / num2 else 0
            else -> 0
        }
        _currentQuestion.value = MathQuestion(num1, num2, type, correctAnswer)
    }

    // Call this method when a user answers a question
    fun submitAnswer(userAnswer: Int) {
        if (userAnswer == _currentQuestion.value?.correctAnswer) {
            _score.value = (_score.value ?: 0) + 1
        }
        // Generate the next question
        _currentQuestion.value?.let {
            generateQuestion(it.operation)
        }
    }
}

