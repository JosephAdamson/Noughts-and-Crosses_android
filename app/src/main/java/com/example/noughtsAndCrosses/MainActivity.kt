package com.example.noughtsAndCrosses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import java.util.*

/**
 * @author Joseph Adamson
 *
 * Main game screen
 */
class MainActivity : AppCompatActivity() {

    val game: GameState = GameState()
    private val squares: ArrayList<ImageView> = arrayListOf()

    inner class DropCounter : View.OnClickListener {

        override fun onClick(view: View?) {
            val tappedSquare: ImageView = view as ImageView

            val position: Int = Integer.parseInt(tappedSquare.tag.toString())

            // check to see if counter has been played
            if (game.checkPosition(position) == 2) {

                // set square off screen
                tappedSquare.translationY = -1000f

                if (game.activePlayer == 0) {
                    tappedSquare.setImageResource(R.drawable.nought)
                    game.setCounter(position, 0)
                    game.activePlayer = 1
                } else {
                    tappedSquare.setImageResource(R.drawable.cross)
                    game.setCounter(position, 1)
                    game.activePlayer = 0
                }

                // drop in nought/cross
                tappedSquare.animate().translationYBy(1000f).duration = 500


                // check board for win

                var win: Boolean = game.checkForWinner()
                if (win) run {
                    val intent: Intent = Intent(this@MainActivity, EndGamePop::class.java)

                    if (game.activePlayer == 0) {
                        intent.putExtra("RESULT", 1)
                    } else {
                        intent.putExtra("RESULT", 0)
                    }
                    startActivity(intent)
                }

                // On a draw
                if (game.availableSquares == 0 && !win) {
                    val intent: Intent = Intent(this@MainActivity, EndGamePop::class.java)
                    intent.putExtra("RESULT", 2)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // wire up all 8 squares
        for (i in 0..8) {

            // search by id
            val squareId: String = "square".plus(i)
            val resourceId: Int = resources.getIdentifier(squareId, "id", packageName)
            val square: ImageView = findViewById(resourceId)

            // set clickable
            square.setOnClickListener(DropCounter())
            squares.add(square)
        }
    }
}