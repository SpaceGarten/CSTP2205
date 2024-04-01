package com.example.mathproblem

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Class to represent the user's answer to a math problem.
 */
class UserAnswer(initialValue: Int = 0) {
    var value: MutableState<Int> = mutableStateOf(initialValue)

    fun updateAnswer(newValue: Int) {
        value.value = newValue
    }

    fun resetAnswer() {
        value.value = 0
    }
}
