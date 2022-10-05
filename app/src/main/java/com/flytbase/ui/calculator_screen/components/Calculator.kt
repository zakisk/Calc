package com.flytbase.ui.calculator_screen.components

import android.content.Context
import com.flytbase.R
import java.util.*

class Calculator(context: Context? = null) {

    //this initialization is for test cases only
    private var divide = ""
    private var multiply = ""
    private var plus = ""
    private var minus = ""

    init {
        context?.let {
            divide = context.getString(R.string.divide)
            multiply = context.getString(R.string.multiply)
            plus = context.getString(R.string.plus)
            minus = context.getString(R.string.minus)
        }
    }

    fun calculate(expression: String): Float {
        var i = 0
        val values = Stack<Float>()
        val operators = Stack<Char>()

        fun evaluate() {
            val val2 = values.peek()
            values.pop()
            val val1 = values.peek()
            values.pop()
            val operator = operators.peek()
            operators.pop()
            values.push(applyOperator(operator, val1, val2))
        }

        while (i < expression.length) {

            if (expression[i] == ' ') {
                continue
            }

            if (expression[i].isDigit()) {
                var value = 0F
                while (i < expression.length && expression[i].isDigit()) {
                    value = (value * 10F) + expression[i].digitToInt().toFloat()
                    i++
                }

                values.push(value)
                i--
            } else {
                while (operators.isNotEmpty() &&
                    precedence(operators.peek()) >= precedence(expression[i])
                ) {
                    evaluate()
                }

                operators.push(expression[i])
            }
            i++
        }

        while (operators.isNotEmpty()) {
            evaluate()
        }

        return if (values.empty()) 0F else values.peek()
    }


    //used these hard coded operator for test cases
    private fun applyOperator(operator: Char, a: Float, b: Float): Float {
        return when (operator.toString()) {
            multiply, "*" -> a * b
            plus, "+" -> a + b
            divide, "/" -> a / b
            minus, "-" -> a - b
            else -> 0F
        }
    }

    private fun precedence(operator: Char): Int {
        return when (operator.toString()) {
            multiply, "*" -> 4
            plus, "+" -> 3
            divide, "/" -> 2
            minus, "-" -> 1
            else -> 0
        }
    }
}