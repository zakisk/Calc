package com.flytbase.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flytbase.data.FlytBaseRepository
import com.flytbase.data.FlytBaseServiceImpl
import com.flytbase.domain.models.History
import com.flytbase.domain.models.User
import com.flytbase.ui.calculator_screen.components.Calculator
import com.flytbase.util.postValue
import com.flytbase.util.showToast
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {

    val buttons = mutableListOf(
        "", "CE", "Del", "",
        "7", "8", "9", "x",
        "4", "5", "6", "",
        "1", "2", "3", "",
        "", "0", "", "ENTER"
    )

    lateinit var multiply: String
    lateinit var divide: String
    lateinit var plus: String
    lateinit var minus: String

    val authenticationResult = mutableStateOf(false)
    val allHistory = mutableStateListOf<History>()
    var mobileNumber by mutableStateOf("")
    var password by mutableStateOf("")
    var expression by mutableStateOf("")
    var result by mutableStateOf("")

    private var repository: FlytBaseRepository

    init {
        val service = FlytBaseServiceImpl(mobileNumber)
        repository = FlytBaseRepository(service)
        getAllHistory()
    }

    fun validate(context: Context): Boolean {
        if (mobileNumber.length != 10) {
            context.showToast("Please enter correct mobile number")
            return false
        }
        if (password.length < 6) {
            context.showToast("Password should have alphanumerical characters")
            return false
        }
        return true
    }

    fun authenticate(context: Context) {
        val user = User(mobile = mobileNumber, password = password)
        repository.authenticate(user) {
            if (it.exists()) {
                val result = it.getValue(User::class.java)
                (result?.password == password && result.mobile == mobileNumber)
                    .let(authenticationResult::postValue)
            } else {
                context.showToast("Credentials aren't match")
            }
        }
    }

    private fun addHistory(history: History) {
        repository.addHistory(history)
    }

    private fun getAllHistory() {
        repository.getAllHistory { snapshot ->
            if (snapshot.exists()) {
                val list = snapshot.children.mapNotNull { it.getValue(History::class.java) }
                list.let(allHistory::postValue)
            }
        }
    }

    fun append(index: Int, button: String) = viewModelScope.launch {
        when (index) {
            1 -> {
                expression = ""
                result = ""
            }
            2 -> {
                if (expression.length == 1) result = ""
                if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)
                }
            }
            3 -> {
                if (expression.isNotEmpty() &&
                    expression[expression.lastIndex].isOperator().not()
                ) {
                    expression += divide
                }
            }
            7 -> {
                if (expression.isNotEmpty() &&
                    expression[expression.lastIndex].isOperator().not()
                ) {
                    expression += multiply
                    Log.v("ViewModel", "check : ${expression.last().toString() == multiply}")
                }
            }
            11 -> {
                if (expression.isNotEmpty() &&
                    expression[expression.lastIndex].isOperator().not()
                ) {
                    expression += plus
                }
            }
            15 -> {
                if (expression.isNotEmpty() &&
                    expression[expression.lastIndex].isOperator().not()
                ) {
                    expression += minus
                }
            }
            else -> {
                expression += button
            }
        }
    }

    private fun Char.isOperator(): Boolean {
        return this.toString() == divide || this.toString() == multiply ||
                this.toString() == plus || this.toString() == minus
    }

    fun calculate(context: Context, isEnterPressed: Boolean = false) {
        if (expression.isNotEmpty() && expression.last().isDigit() && containsOperators()) {
            val calc = Calculator(context)
            val res = calc.calculate(expression)
            result = if ((res % 1F) == 0F) res.toInt().toString() else res.toString()
            if (isEnterPressed) {
                addHistory(History(expression, result))
                context.showToast("added to history")
            }
        } else {
            if (isEnterPressed) {
                context.showToast("Please enter valid expression")
            }
        }
    }

    private fun containsOperators(): Boolean {
        return expression.contains(divide, ignoreCase = true) ||
                expression.contains(multiply, ignoreCase = true) ||
                expression.contains(plus, ignoreCase = true) ||
                expression.contains(minus, ignoreCase = true)
    }
}