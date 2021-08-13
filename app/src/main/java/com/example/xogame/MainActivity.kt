package com.example.xogame


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.xogame.databinding.ActivityMainBinding
import com.example.xogame.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val buttons = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun setValue(button: Button) {
        Data.ROUND++
        button.isEnabled = false
        button.text = if (Player.CURRENT == Player.TWO) Constant.X else Constant.O

        if (Player.CURRENT == Player.TWO) {
            Player.CURRENT = Player.ONE
            button.setBackgroundColor(Color.GRAY)
        } else {
            Player.CURRENT = Player.TWO
            button.setBackgroundColor(Color.RED)
        }

        if (isWinner(current = Constant.X)) {
            Points.X++
            binding.playerX.text = Points.X.toString()
            reset(winner = Constant.X)
        } else if (isWinner(current = Constant.O)) {
            Points.O++
            binding.playerO.text = Points.O.toString()
            reset(winner = Constant.O)
        }

        if (Data.ROUND >= 9) {
            Points.reset()
            reset(null)
        }
    }

    private fun isWinner(current: String): Boolean {
        val state1 = when (buttons[Index.ZERO].text.equals(current)) {
            true -> (current == buttons[Index.ONE].text && buttons[Index.TWO].text == current)
                    || (current == buttons[Index.THREE].text && buttons[Index.SIX].text == current)
                    || (current == buttons[Index.FOUR].text && buttons[Index.EIGHT].text == current)
            else -> false
        }

        val state2 = when (buttons[Index.FOUR].text.equals(current)) {
            true -> (current == buttons[Index.SIX].text && buttons[Index.TWO].text == current)
                    || (current == buttons[Index.THREE].text && buttons[Index.FIVE].text == current)
                    || (current == buttons[Index.ONE].text && buttons[Index.SEVEN].text == current)
            else -> false
        }

        val state3 = when (buttons[Index.EIGHT].text.equals(current)) {
            true -> (current == buttons[Index.TWO].text && buttons[Index.FIVE].text == current)
                    || (current == buttons[Index.SIX].text && buttons[Index.SEVEN].text == current)
            else -> false
        }
        return (state1 || state2 || state3)
    }

    private fun reset(winner: String?) {
        winner?.let { Toast.makeText(this, "$it Winner!", Toast.LENGTH_LONG).show() }
        Data.reset()
        buttons.forEach {
            it.text = ""
            it.isEnabled = true
            it.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun init() {
        binding.gridLayout.children.forEach {
            val button = it as Button
            buttons.add(button)
            button.setOnClickListener { setValue(button = button) }
        }
        binding.btnReset.setOnClickListener {
            reset(null)
            Points.reset()
            binding.playerX.text = Points.X.toString()
            binding.playerO.text = Points.O.toString()
        }
    }
}