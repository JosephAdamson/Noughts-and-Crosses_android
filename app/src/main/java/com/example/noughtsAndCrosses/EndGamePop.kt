package com.example.noughtsAndCrosses

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.IllegalStateException

/**
 * @author Joseph Adamson
 *
 * Pop-up screen used to conclude current game
 */
class EndGamePop : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // needs to be done before we set the content
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.end_game_pop)

        // get window dimensions
        val dm: DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width: Int = dm.widthPixels
        val height: Int = dm.heightPixels

        window.setLayout((width * 0.8).toInt(), (height * 0.4).toInt())

        // make whole activity clickable
        val screen: ConstraintLayout = findViewById<ConstraintLayout>(R.id.pop)
        screen.setOnClickListener{
            startActivity(Intent(this@EndGamePop, MainActivity::class.java))
        }

        val message: TextView = findViewById(R.id.winner)

        when (intent.getIntExtra("RESULT", -1)) {
            0 -> {
                message.setTextColor(Color.parseColor("#001eb5"))
                message.setText(R.string.noughts_wins)
            }
            1 -> {
                message.setTextColor(Color.parseColor("#b50000"))
                message.setText(R.string.crosses_wins)
            }
            2 -> {
                message.setTextColor(Color.BLACK)
                message.setText(R.string.draw)
            }
            else -> {
                throw IllegalStateException("Data wasn't transferred properly")
            }
        }
    }
}