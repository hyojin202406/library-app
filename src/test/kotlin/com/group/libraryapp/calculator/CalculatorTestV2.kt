package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

fun main() {
    val calculatorTestV2 = CalculatorTestV2()
    calculatorTestV2.addTest()
    calculatorTestV2.divideExceptionTest()
    calculatorTestV2.divideExceptionTestV2()
}

class CalculatorTestV2 {
    @Test
    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun divideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when & then
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message

        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
    }

    @Test
    fun divideExceptionTestV2() {
        // given
        val calculator = Calculator(5)

        // when & then
        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
        }
    }

}