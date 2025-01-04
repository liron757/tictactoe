package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Array<Button>>
    private lateinit var tvCurrentTurn: TextView
    private lateinit var btnReset: Button

    private var player1Turn = true
    private var roundCount = 0

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

        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].setOnClickListener {
                    onButtonClicked(it)
                }
            }
        }
        btnReset = findViewById(R.id.btnReset)
        btnReset.setOnClickListener({ resetBoard() })

        tvCurrentTurn = findViewById(R.id.tvCurrentTurn)
        updateTurnTitle()
    }

    private fun onButtonClicked(view: View) {
        if ((view as Button).text != "") {
            return
        }

        view.apply {
            isEnabled = false
            text = if(player1Turn) "X" else "O"
            setBackgroundResource(if(player1Turn) R.drawable.player1 else R.drawable.player2)
        }

        roundCount++

        if (checkForWin()) {
            endGame("Player ${if(player1Turn) "X" else "O"} Wins!")
        } else if (roundCount == 9) {
            endGame("Draw!")
        } else {
            player1Turn = !player1Turn
            updateTurnTitle()
        }
    }

    private fun checkForWin(): Boolean {
        val fields = Array(3) { row ->
            Array(3) { col ->
                buttons[row][col].text.toString()
            }
        }

        for (i in 0..2) {
            if (fields[i][0] == fields[i][1] && fields[i][0] == fields[i][2] && fields[i][0] != "") {
                return true
            }
            if (fields[0][i] == fields[1][i] && fields[0][i] == fields[2][i] && fields[0][i] != "") {
                return true
            }
        }

        if (fields[0][0] == fields[1][1] && fields[0][0] == fields[2][2] && fields[0][0] != "") {
            return true
        }
        if (fields[0][2] == fields[1][1] && fields[0][2] == fields[2][0] && fields[0][2] != "") {
            return true
        }

        return false
    }

    private fun showResetButton() {
        btnReset.visibility = View.VISIBLE
        btnReset.isEnabled = true
    }

    private fun hideResetButton() {
        btnReset.visibility = View.INVISIBLE
        btnReset.isEnabled = false
    }

    private fun endGame(toastMessage: String) {
        tvCurrentTurn.text = toastMessage
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].isEnabled = false
            }
        }
        showResetButton()
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].apply {
                    setBackgroundResource(android.R.drawable.btn_default)
                    isEnabled = true
                    text = ""
                }
            }
        }
        roundCount = 0
        player1Turn = true
        updateTurnTitle()
        hideResetButton()
    }

    private fun updateTurnTitle() {
        tvCurrentTurn.text = "${if(player1Turn) "X" else "O"} Turn"
    }
}