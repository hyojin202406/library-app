package com.group.libraryapp.calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.AddTest()
}

class CalculatorTest {

    fun AddTest() {
        val calculator = Calculator(5)
        calculator.add(3)

        // equals 사용
        val expectedCalculator = Calculator(8)
        if (calculator != expectedCalculator) {
            throw IllegalArgumentException()
        }
    }

}