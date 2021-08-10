package com.example.xogame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import com.example.xogame.databinding.ActivityMainBinding
import com.example.xogame.util.Constant
import com.example.xogame.util.Player

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object var player = Player.ONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun setValue(button: View) {
        if ((button as Button).text.isNotEmpty()) {
            Toast.makeText(
                this,
                "Not Allowed",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        button.text = if (player == Player.TWO) Constant.X else Constant.O

        if (player == Player.TWO) {
            player = Player.ONE
            button.isEnabled = false
            button.setBackgroundColor(Color.GRAY)
        } else {
            player = Player.TWO
            button.isEnabled = false
            button.setBackgroundColor(Color.RED)
        }

        if (isWinner(Constant.X)) {
            Toast.makeText(this, "X Winner!", Toast.LENGTH_LONG).show()
            reset()
        } else if (isWinner(Constant.O)) {
            Toast.makeText(this, "O Winner!", Toast.LENGTH_LONG).show()
            reset()
        }
    }

    private fun isWinner(current: String): Boolean {
        val state1 = if (current != binding.btn1.text) false
                     else (binding.btn2.text == current && binding.btn3.text == current)
                             || (binding.btn4.text == current && binding.btn7.text == current)
                             || (binding.btn5.text == current && binding.btn9.text == current)

        val state2 = if (current != binding.btn2.text) false
                     else (binding.btn5.text == current && binding.btn8.text == current)

        val state3 = if (current != binding.btn2.text) false
                     else (binding.btn5.text == current && binding.btn7.text == current)
                             || (binding.btn6.text== current && binding.btn9.text == current)

        val state4 = when (current) {
            binding.btn4.text -> (binding.btn5.text == current && binding.btn6.text == current)
            binding.btn7.text -> (binding.btn8.text == current && binding.btn9.text == current)
            else -> false
        }

        return (state1 || state2 || state3 || state4)

    }

    private fun reset() {
        binding.btn1.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn2.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn3.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn4.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn5.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn6.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn7.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn8.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }

        binding.btn9.apply {
            this.text = ""
            this.isEnabled = true
            this.setBackgroundColor(Color.GREEN) }
    }

    private fun init() {
        binding.btn1.setOnClickListener { setValue(it) }
        binding.btn2.setOnClickListener { setValue(it) }
        binding.btn3.setOnClickListener { setValue(it) }
        binding.btn4.setOnClickListener { setValue(it) }
        binding.btn5.setOnClickListener { setValue(it) }
        binding.btn6.setOnClickListener { setValue(it) }
        binding.btn7.setOnClickListener { setValue(it) }
        binding.btn8.setOnClickListener { setValue(it) }
        binding.btn9.setOnClickListener { setValue(it) }
    }
}