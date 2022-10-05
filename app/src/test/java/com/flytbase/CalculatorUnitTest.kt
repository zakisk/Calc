package com.flytbase

import com.flytbase.ui.calculator_screen.components.Calculator
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorUnitTest {

    private val calc = Calculator()

    @Test
    fun calculator_test_1() {
        val result = calc.calculate("50+20/10")
        assertTrue(result.toInt() == 7)
    }

    @Test
    fun calculator_test_2() {
        val result = calc.calculate("50/20+5")
        assertTrue(result.toInt() == 2)
    }

    @Test
    fun calculator_test_3() {
        val result = calc.calculate("10/2-20")
        assertTrue(result.toInt() == -15)
    }

    @Test
    fun calculator_test_4() {
        val result = calc.calculate("25-2*10")
        assertTrue(result.toInt() == 5)
    }

    @Test
    fun calculator_test_5() {
        val result = calc.calculate("10-2-3")
        assertTrue(result.toInt() == 5)
    }

    @Test
    fun calculator_test_6() {
        val result = calc.calculate("10/2/5")
        assertTrue(result.toInt() == 1)
    }
}