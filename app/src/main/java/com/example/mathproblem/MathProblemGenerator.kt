package com.example.mathproblem

import kotlin.random.Random

/**
 * Class to generate random math problems.
 */
class MathProblemGenerator {
    fun generateProblem(type: String): Pair<Pair<Int, Int>, Int> {
        val num1 = Random.nextInt(1, 100)
        val num2 = Random.nextInt(1, 100)
        val correctAnswer = when (type) {
            "addition" -> num1 + num2
            "subtraction" -> num1 - num2
            "multiplication" -> num1 * num2
            "division" -> if (num2 != 0) num1 / num2 else 0
            else -> 0
        }
        return Pair(Pair(num1, num2), correctAnswer)
    }
}
