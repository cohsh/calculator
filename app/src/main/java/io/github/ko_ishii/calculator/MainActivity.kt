package io.github.ko_ishii.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn0 : Button = findViewById(R.id.num0)
        val btn1 : Button = findViewById(R.id.num1)
        val btn2 : Button = findViewById(R.id.num2)
        val btn3 : Button = findViewById(R.id.num3)
        val btn4 : Button = findViewById(R.id.num4)
        val btn5 : Button = findViewById(R.id.num5)
        val btn6 : Button = findViewById(R.id.num6)
        val btn7 : Button = findViewById(R.id.num7)
        val btn8 : Button = findViewById(R.id.num8)
        val btn9 : Button = findViewById(R.id.num9)
        val btn00 : Button = findViewById(R.id.num00)
        val btnPlus : Button = findViewById(R.id.plus)
        val btnMinus : Button = findViewById(R.id.minus)
        val btnTimes : Button = findViewById(R.id.times)
        val btnDivide : Button = findViewById(R.id.divide)
        val btnEqual : Button = findViewById(R.id.equal)
        val btnClear : Button = findViewById(R.id.clear)
        val mainDisplay : TextView = findViewById(R.id.mainDisplay)
        val subDisplay  : TextView = findViewById(R.id.subDisplay)

        var value = 0
        var registers = arrayOf("0", "0")
        var operator = ""
        var canOperate = true
        var isInitial = true

        fun operate(op : String, i : Int) :Int {
            val j = registers[0].toInt()
            return when(op) {
                "+" -> j + i
                "-" -> j - i
                "*" -> j * i
                "/" -> j / i
                else -> i
            }
        }

        fun inputNumber(num : String) {
            var s : String
            if (isInitial) {
                s = num
                isInitial = false
            } else {
                s = mainDisplay.text.toString() + num
            }
            mainDisplay.setText(s)
            registers[1] += num
            val i : Int = registers[1].toInt()
            value = operate(operator, i)
            subDisplay.setText(value.toString())
            canOperate = true
        }

        fun inputOperator(op : String) {
            if (canOperate) {
                mainDisplay.setText(mainDisplay.text.toString() + op)
                registers[0] = value.toString()
                registers[1] = "0"
                canOperate = false
                operator = op
            }
            if (isInitial) {
                isInitial = false
            }
        }

        btnEqual.setOnClickListener {
            mainDisplay.setText(value.toString())
            registers[1] = ""
            operator = ""
            canOperate = true
            isInitial = true
        }

        btnClear.setOnClickListener {
            mainDisplay.setText("0")
            subDisplay.setText("0")
            value = 0
            registers = arrayOf("0", "0")
            operator = ""
            canOperate = true
            isInitial = true
        }

        btn0.setOnClickListener{
            if (mainDisplay.text.toString() != "0") {
                if (operator != "/" || registers[1].toInt() != 0) {
                    inputNumber("0")
                }
            }
        }
        btn1.setOnClickListener{
            inputNumber("1")
        }
        btn2.setOnClickListener{
            inputNumber("2")
        }
        btn3.setOnClickListener{
            inputNumber("3")
        }
        btn4.setOnClickListener{
            inputNumber("4")
        }
        btn5.setOnClickListener{
            inputNumber("5")
        }
        btn6.setOnClickListener{
            inputNumber("6")
        }
        btn7.setOnClickListener{
            inputNumber("7")
        }
        btn8.setOnClickListener{
            inputNumber("8")
        }
        btn9.setOnClickListener{
            inputNumber("9")
        }
        btn00.setOnClickListener{
            if (mainDisplay.text.toString() != "0") {
                if (operator != "/" || registers[1].toInt() != 0) {
                    repeat(2){inputNumber("0")}
                }
            }
        }

        btnPlus.setOnClickListener {
            inputOperator("+")
        }

        btnMinus.setOnClickListener {
            inputOperator("-")
        }

        btnTimes.setOnClickListener {
            inputOperator("*")
        }

        btnDivide.setOnClickListener {
            inputOperator("/")
        }
    }
}