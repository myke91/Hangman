package com.myke.hangman.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myke.hangman.R
import com.myke.hangman.SoftKeyboard

@Composable
fun GameScreen(viewModel: GameViewModel) {

    val word = viewModel.word.collectAsState()
    val lettersUsed = viewModel.lettersUsed.collectAsState()
    val image = viewModel.image.collectAsState()
    val gameWon = viewModel.gameWon.collectAsState()
    val gameCompleted = viewModel.gameCompleted.collectAsState()
    val remainingWords = viewModel.remainingWords.collectAsState()
    val remainingAttempts = viewModel.remainingAttempts.collectAsState()
    val enableWordEntry = viewModel.enableWordEntry.collectAsState()
    val lettersPlayed = remember { mutableStateOf<String>("") }


    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxHeight()
    ) {

        Text(
            text = "Remaining words ${remainingWords.value}",
            color = Color.Red,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        )

        ConstraintLayout {
            val (imageConstraint, textConstraint) = createRefs()


            Image(
                painter = painterResource(id = image.value),
                contentDescription = "hangman image",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(imageConstraint) {
                        top.linkTo(parent.top, margin = 0.dp)
                    }
                    .size(200.dp, 200.dp)
            )
            if (gameCompleted.value == true) {
                Text(
                    text = if (gameWon.value) "You Won!" else "You Lost!",
                    color = if (gameWon.value) Color.Green else Color.Red,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .constrainAs(textConstraint) {
                            top.linkTo(imageConstraint.top, margin = 0.dp)
                            bottom.linkTo(imageConstraint.bottom, margin = 0.dp)
                            start.linkTo(imageConstraint.start, margin = 0.dp)
                            end.linkTo(imageConstraint.end, margin = 0.dp)
                        }
                        .rotate(-45f)

                )
            }
        }

        Text(
            text = word.value,
            letterSpacing = 0.3.sp,
            fontSize = 26.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        TextField(value = lettersPlayed.value,
            enabled = enableWordEntry.value ?: false,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onValueChange = {
                lettersPlayed.value = it
                viewModel.play(it)
            })

        Text(
            text = "Letters used: ${lettersUsed.value}",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Text(
            text = "Remaining attempts ${remainingAttempts.value}",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Button(
            onClick = {
                lettersPlayed.value = ""
                viewModel.startGame()
            },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text(text = "Start New Game")
        }
    }
}

