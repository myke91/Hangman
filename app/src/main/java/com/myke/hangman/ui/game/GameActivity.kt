package com.myke.hangman.ui.game

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myke.hangman.model.GameState
import com.myke.hangman.R
import com.myke.hangman.SoftKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen(gameViewModel)
        }
    }
}

@Composable
fun GameScreen(viewModel: GameViewModel) {

    val word = viewModel.word.collectAsState()
    val lettersUsed = viewModel.lettersUsed.collectAsState()
    val image = viewModel.image.collectAsState()
    val gameLost = viewModel.gameLost.collectAsState()
    val gameWon = viewModel.gameWon.collectAsState()
    val remainingWords = viewModel.remainingWords.collectAsState()
    val remainingAttempts = viewModel.remainingAttempts.collectAsState()
    val lettersLayout = viewModel.lettersLayout.collectAsState()


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
    ) {

        Text(
            text = "Remaining words $remainingWords",
            color = Color.Red,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.game0),
            contentDescription = "hangman image",
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp, 200.dp)
                .padding(16.dp)
        )
        if (gameWon.value) {
            Text(
                text = "You Won!",
                color = Color.Green,
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .align(Alignment.CenterHorizontally)
                    .rotate(-45f)
            )
        }

        if (gameLost.value) {
            Text(
                text = "You Lost!",
                color = Color.Red,
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .align(Alignment.CenterHorizontally)
                    .rotate(-45f)
            )
        }

        Text(
            text = word.value,
            letterSpacing = 0.3.sp,
            fontSize = 26.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Letters used: $lettersUsed",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Text(
            text = "Remaining attempts $remainingAttempts",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Button(
            onClick = { viewModel.startGame() },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text(text = "Start New Game")
        }

        Spacer(modifier = Modifier.weight(1f))
        SoftKeyboard() {

        }

    }
}

