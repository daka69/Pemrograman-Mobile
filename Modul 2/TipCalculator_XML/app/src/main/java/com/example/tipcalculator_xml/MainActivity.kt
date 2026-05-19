package com.example.tipcalculator_xml

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import kotlin.math.ceil

class MainActivity : ComponentActivity() {

    private lateinit var editTextBillAmount: EditText
    private lateinit var autoCompleteTip: AutoCompleteTextView
    private lateinit var switchRoundUp: Switch
    private lateinit var textViewTipAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextBillAmount  = findViewById(R.id.editTextBillAmount)
        autoCompleteTip     = findViewById(R.id.autoCompleteTip)
        switchRoundUp       = findViewById(R.id.switchRoundUp)
        textViewTipAmount   = findViewById(R.id.textViewTipAmount)

        val tipOptions = listOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)
        autoCompleteTip.setAdapter(adapter)
        autoCompleteTip.setText(tipOptions[0], false)

        autoCompleteTip.setOnItemClickListener { _, _, _, _ -> calculateTip() }

        switchRoundUp.setOnCheckedChangeListener { _, _ -> calculateTip() }

        editTextBillAmount.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { calculateTip() }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }

    private fun calculateTip() {
        val billAmount = editTextBillAmount.text.toString().toDoubleOrNull() ?: 0.0
        val tipPercentage = autoCompleteTip.text.toString().replace("%", "").toDoubleOrNull() ?: 15.0
        var tipAmount = billAmount * (tipPercentage / 100)
        if (switchRoundUp.isChecked) tipAmount = ceil(tipAmount)
        textViewTipAmount.text = "Tip Amount: $" + "%.2f".format(tipAmount)
    }
}