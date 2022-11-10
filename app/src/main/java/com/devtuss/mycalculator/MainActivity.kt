package com.devtuss.mycalculator

/*
* A simple calculator app
* 2022-11-10
* Caroline Engqvist
* DevTuss
*/

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.devtuss.mycalculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var operators: List<Any>
    private var firstDouble: Double = 0.0
    private var secondDouble: Double = 0.0
    private var chosenOperator: Any? = null
    private lateinit var tvInput: TextView
    private val predicate: (Char) -> Boolean = { operators.contains(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        operators = listOf('/','*','-','+')

        tvInput = binding.tvInput
        binding.btnSeven.setOnClickListener { t -> onDigit(t) }
        binding.btnEight.setOnClickListener { t -> onDigit(t) }
        binding.btnNine.setOnClickListener { t -> onDigit(t) }
        binding.btnDiv.setOnClickListener { t -> onOperator(t) }
        binding.btnFour.setOnClickListener { t -> onDigit(t) }
        binding.btnFive.setOnClickListener { t -> onDigit(t) }
        binding.btnSix.setOnClickListener { t -> onDigit(t) }
        binding.btnMulti.setOnClickListener { t -> onOperator(t) }
        binding.btnOne.setOnClickListener { t -> onDigit(t) }
        binding.btnTwo.setOnClickListener { t -> onDigit(t) }
        binding.btnThree.setOnClickListener { t -> onDigit(t) }
        binding.btnMin.setOnClickListener { t -> onOperator(t) }
        binding.btnZero.setOnClickListener { t -> onDigit(t) }
        binding.btnCLR.setOnClickListener { tvInput.text = "" }
        binding.btnDot.setOnClickListener { t -> onDigit(t) }
        binding.btnPlus.setOnClickListener { t -> onOperator(t) }
        binding.btnEqual.setOnClickListener { onEqual() }
    }

    private fun onEqual() {
        val theText = tvInput.text.toString()
        if(theText.any(predicate) && theText[theText.lastIndex].isDigit()) {
            secondDouble = theText
                .subSequence(tvInput.text.indexOfFirst(predicate) + 1, theText.lastIndex + 1)
                .toString().toDouble()
            val format = DecimalFormat("0.#")
            tvInput.text = format.format(calculate())
            firstDouble = 0.0
            secondDouble = 0.0
        }
    }

    private fun onOperator(view: View) {
        val theText = tvInput.text.toString()
        if(theText.any(predicate) || theText.isNotEmpty()) {
            firstDouble = theText.toDouble()
            chosenOperator = (view as Button).text.toString()
            tvInput.append(view.text)
        }
    }

    private fun onDigit(view: View) {
        tvInput.append((view as Button).text)
    }

    private fun calculate():Double
    {
        return when(chosenOperator)
        {
            "+"->{firstDouble+secondDouble}
            "-"->{firstDouble-secondDouble}
            "/"->{firstDouble/secondDouble}
            "*"->{firstDouble*secondDouble}
            else -> throw Exception("That's not a supported operator")
        }
    }
}
