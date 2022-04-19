package com.myke.hangman

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

//    private val gameManager = GameManager()
//
//    @Inject
//    lateinit var scoreManager: ScoreManager

//    private lateinit var wordTextView: TextView
//    private lateinit var lettersUsedTextView: TextView
//    private lateinit var imageView: ImageView
//    private lateinit var gameLostTextView: TextView
//    private lateinit var gameWonTextView: TextView
//    private lateinit var newGameButton: Button
//    private lateinit var lettersLayout: ConstraintLayout
//    private lateinit var remainingWords: TextView
//    private lateinit var remainingAttempts: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_game)

        setContent {
            GameScreen()
        }


//        remainingAttempts = findViewById(R.id.remainingAttempts)
//        remainingWords = findViewById(R.id.remainingWords)
//        imageView = findViewById(R.id.imageView)
//        wordTextView = findViewById(R.id.wordTextView)
//        lettersUsedTextView = findViewById(R.id.lettersUsedTextView)
//        gameLostTextView = findViewById(R.id.gameLostTextView)
//        gameWonTextView = findViewById(R.id.gameWonTextView)
//        newGameButton = findViewById(R.id.newGameButton)
//        lettersLayout = findViewById(R.id.lettersLayout)
//        newGameButton.setOnClickListener {
//            startNewGame()
//        }
//        val gameState = gameManager.startNewGame()
//        updateUI(gameState)
//
//        lettersLayout.children.forEach { letterView ->
//            if (letterView is TextView) {
//                letterView.setOnClickListener {
//                    val gameState = gameManager.play((letterView).text[0])
//                    updateUI(gameState)
//                    letterView.visibility = View.GONE
//                }
//            }
//        }
    }
//
//    private fun updateUI(gameState: GameState) {
//        when (gameState) {
//            is GameState.Lost -> {
//                showGameLost(gameState.wordToGuess)
//                remainingAttempts.text = "Remaining Attempts: ${gameState.remainingAttempts}"
//            }
//            is GameState.Running -> {
//                remainingWords.text = "Remaining Words: ${gameState.remainingWords}"
//                remainingAttempts.text = "Remaining Attempts: ${gameState.remainingAttempts}"
//                wordTextView.text = gameState.underscoreWord
//                lettersUsedTextView.text = "Letters used: ${gameState.lettersUsed}"
//                imageView.setImageDrawable(ContextCompat.getDrawable(this, gameState.drawable))
//            }
//            is GameState.Won -> {
//                showGameWon(gameState.wordToGuess)
//                showCurrentScore(gameState.wordToGuess)
//            }
//        }
//    }
//
//    private fun showCurrentScore(wordToGuess: String) {
//        val points = scoreManager.pointsGained(wordToGuess)
//        val builder = AlertDialog.Builder(this)
//        builder.setMessage("Hurray!!!! You gained $points points")
//            .setPositiveButton("OK", null)
//        builder.show()
//
//        lifecycleScope.launch {
//            scoreManager.updateScore(wordToGuess, points)
//        }
//    }
//
//    private fun showGameLost(wordToGuess: String) {
//        wordTextView.text = wordToGuess
//        gameLostTextView.visibility = View.VISIBLE
//        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.game7))
//        lettersLayout.visibility = View.GONE
//    }
//
//    private fun showGameWon(wordToGuess: String) {
//        wordTextView.text = wordToGuess
//        gameWonTextView.visibility = View.VISIBLE
//        lettersLayout.visibility = View.GONE
//    }
//
//    private fun startNewGame() {
//        gameLostTextView.visibility = View.GONE
//        gameWonTextView.visibility = View.GONE
//        val gameState = gameManager.startNewGame()
//        lettersLayout.visibility = View.VISIBLE
//        lettersLayout.children.forEach { letterView ->
//            letterView.visibility = View.VISIBLE
//        }
//        updateUI(gameState)
//    }


}


@Composable
fun GameScreen() {
    var visible by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Text(
            text = "Remaining words", modifier = Modifier
                .align(Alignment.End)
        )

        Image(
            painter = painterResource(id = R.drawable.game0),
            contentDescription = "hangman image",
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )
        if (visible) {
            Text(
                text = "You Won!",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .rotate(-45f),

                )
        }

        Text(
            text = " _ _ ABCD_ _ _ _ _ _ _", modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = " Used Letters: A, B, C, D, E, F, G", modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Remaining attempts", modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Start New Game")
        }
        Row(
            modifier = Modifier
                .weight(1f, false)
        ) {
            SoftKeyboard()
        }
    }
}

