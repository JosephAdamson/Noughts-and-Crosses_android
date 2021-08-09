package com.example.tiktactoe

import android.util.Log
import java.io.Serializable

/**
 * @author Joseph Adamson
 *
 * Simple game logic
 */
class GameState : Serializable {

    // 0 for noughts (player 1), 1 for crosses (Player 2). Naughts goes first.
    var activePlayer: Int = 0

    var availableSquares: Int = 9

    // flattened game board 2 is blank
    private var board: Array<Int> = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    private val winningPositions: Array<Array<Int>> = arrayOf(
        arrayOf(0, 1, 2),
        arrayOf(3, 4, 5),
        arrayOf(6, 7, 8),
        arrayOf(0, 4, 8),
        arrayOf(2, 4, 6),
        arrayOf(0, 3, 6),
        arrayOf(1, 4, 7),
        arrayOf(2, 5, 8)
    )

    /**
     * Set our counter (either a naught or a cross) on the game board.
     *
     * @param position: position on the board
     * @param counter: either 0 or 1 depending on the counter
     */
    fun setCounter(position: Int, counter: Int) {
        board[position] = counter
        availableSquares--
    }

    /**
     * Check a certain position on the game board.
     *
     * @param position: position on the board
     */
    fun checkPosition(position: Int): Int {
        return board[position]
    }

    /**
     * Check board for winning counter combinations.
     */
    fun checkForWinner(): Boolean {
        for (win in winningPositions) {

            if (board[win[0]] == board[win[1]] && board[win[1]] == board[win[2]]
                && board[win[0]] != 2) {

                if (board[win[0]] == 0) {
                    // naughts win
                    Log.i("Info", "naughts wins!")
                } else {
                    // crosses win
                    Log.i("Info", "crosses wins!")
                }
                return true
            }
        }
        return false
    }
}