package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Array<Button>>
    private lateinit var tvCurrentTurn: TextView
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = Array(3) { row ->
            Array(3) { col ->
                val buttonId = resources.getIdentifier(
                    "button_$row$col",
                    "id",
                    packageName
                )
                findViewById(buttonId)
            }
        }

        btnReset = findViewById(R.id.btnReset)

        tvCurrentTurn = findViewById(R.id.tvCurrentTurn)
    }
}