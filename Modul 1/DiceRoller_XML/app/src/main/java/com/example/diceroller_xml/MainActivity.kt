package com.example.diceroller_xml

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var imageViewDice1: ImageView
    private lateinit var imageViewDice2: ImageView
    private lateinit var buttonRoll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewDice1 = findViewById(R.id.imageViewDice1)
        imageViewDice2 = findViewById(R.id.imageViewDice2)
        buttonRoll     = findViewById(R.id.buttonRoll)

        imageViewDice1.setImageResource(R.drawable.dice_0)
        imageViewDice2.setImageResource(R.drawable.dice_0)

        buttonRoll.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val result1 = (1..6).random()
        val result2 = (1..6).random()

        imageViewDice1.setImageResource(getDiceDrawable(result1))
        imageViewDice2.setImageResource(getDiceDrawable(result2))

        val message = when {
            result1 == result2 -> "Selamat, anda dapat dadu double!"
            else               -> "Anda belum beruntung!"
        }

        showCustomToast(message)
    }

    private fun showCustomToast(message: String) {
        val textView = TextView(this).apply {
            text      = message
            textSize  = 14f
            typeface  = Typeface.DEFAULT
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#323232"))
            setPadding(48, 24, 48, 24)
        }

        Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            view     = textView
            setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 100)
        }.show()
    }

    private fun getDiceDrawable(value: Int): Int = when (value) {
        1    -> R.drawable.dice_1
        2    -> R.drawable.dice_2
        3    -> R.drawable.dice_3
        4    -> R.drawable.dice_4
        5    -> R.drawable.dice_5
        6    -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }
}