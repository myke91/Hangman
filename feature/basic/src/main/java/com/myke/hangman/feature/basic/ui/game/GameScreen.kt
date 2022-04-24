package com.myke.hangman.feature.basic.ui.game

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myke.hangman.feature.basic.R
import com.myke.hangman.feature.basic.ui.composables.SimpleAlertDialog

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
    val pointsGained = viewModel.pointsGained.collectAsState()
    val showDialog = viewModel.showDialog.collectAsState()
    val showScoreHistory = viewModel.showScoreHistory.collectAsState()

    val lettersPlayed = remember { mutableStateOf("") }


    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxHeight()
    ) {

        Text(
            text = stringResource(id = R.string.remaining_words, remainingWords.value.toString()),
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
                contentDescription = stringResource(id = R.string.hangman_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(imageConstraint) {
                        top.linkTo(parent.top, margin = 0.dp)
                    }
                    .size(200.dp, 200.dp)
            )
            if (gameCompleted.value) {
                Text(
                    text = if (gameWon.value)
                        stringResource(id = R.string.you_won)
                    else
                        stringResource(id = R.string.you_lost),
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
            enabled = enableWordEntry.value,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onValueChange = {
                lettersPlayed.value = it
                viewModel.play(it)
            })

        Text(
            text = stringResource(id = R.string.letters_used, lettersUsed.value),
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Text(
            text = stringResource(
                id = R.string.remaining_attempts,
                remainingAttempts.value.toString()
            ),
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
            Text(text = stringResource(id = R.string.start_new_game))
        }

        Button(
            onClick = {
                viewModel.viewScoreHistory()
            },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.view_score_history))
        }
    }

    SimpleAlertDialog(
        show = showDialog.value,
        onDismiss = { viewModel.onDialogDismiss() },
        onConfirm = { viewModel.onDialogConfirm() },
        confirmText = stringResource(id = R.string.view_score_history),
        dismissText = stringResource(id = R.string.ok),
        title = stringResource(id = R.string.app_name),
        content = if (!showScoreHistory.value) {
            stringResource(id = R.string.score_message, pointsGained.value.toString())
        } else {
            viewModel.scoreHistory.value
        }
    )


}

