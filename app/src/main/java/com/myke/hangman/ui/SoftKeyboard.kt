package com.myke.hangman

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SoftKeyboard(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        val keysMatrix = arrayOf(
            arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            arrayOf("Z", "X", "C", "V", "B", "N", "M")
        )
        val refsMap = mutableMapOf<String, ConstrainedLayoutReference>()
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(clip = false)
        ) {
            keysMatrix.forEachIndexed { _, keys ->
                keys.forEachIndexed { _, key ->
                    val ref = createRef()
                    refsMap[key] = ref
                }
            }
            keysMatrix.forEachIndexed { row, keys ->
                keys.forEachIndexed { column, key ->
                    val ref = refsMap[key]!!
                    val modifier = Modifier.constrainAs(ref) {
                        // Start
                        if (column == 0) {
                            start.linkTo(parent.start)
                        } else {
                            refsMap[keys[column - 1]]?.let {
                                start.linkTo(it.end)
                            }
                        }
                        // End
                        if (column == keys.lastIndex) {
                            end.linkTo(parent.end)
                        } else {
                            refsMap[keys[column + 1]]?.let {
                                end.linkTo(it.start)
                            }
                        }
                        // Top
                        if (row == 0) {
                            top.linkTo(parent.top)
                        } else {
                            refsMap[keysMatrix[row - 1][0]]?.let {
                                top.linkTo(it.bottom)
                            }
                        }
                    }

                    val modifierPressed = Modifier.constrainAs(createRef()) {
                        start.linkTo(ref.start)
                        end.linkTo(ref.end)
                        bottom.linkTo(ref.bottom)
                    }
                    KeyboardKey(
                        keyboardKey = key,
                        modifier = modifier,
                        modifierPressed = modifierPressed,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
fun KeyboardKey(
    keyboardKey: String,
    modifier: Modifier,
    modifierPressed: Modifier,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    Text(keyboardKey, Modifier
        .then(modifier.clickable {
            onClick()
        })
//        .pressIndicatorGestureFilter(
//            onStart = { pressed = true },
//            onStop = { pressed = false },
//            onCancel = { pressed = false }
//        )
        .background(MaterialTheme.colors.primary)
        .padding(
            start = 12.dp,
            end = 12.dp,
            top = 16.dp,
            bottom = 16.dp
        ),
        color = Color.White
    )
    if (pressed) {
        Text(
            keyboardKey, Modifier
                .then(modifierPressed)
                .background(Color.White)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 48.dp
                )
        )
    }
}