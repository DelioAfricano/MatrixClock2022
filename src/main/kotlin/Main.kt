// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import components.BottomToolBar
import components.Clock
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import movement.alphabet.TextMatrixGenerator
import movement.core.Movement
import utils.*
import javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR


@ExperimentalFoundationApi
fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "HI DEV",
        state = rememberWindowState(
            width = (CLOCKS_CONTAINER_WIDTH + PADDING).dp, height = (CLOCKS_CONTAINER_HEIGHT + PADDING + 40).dp
        ),
    ) {

        val infiniteLoopScope = rememberCoroutineScope()

        // To hold and control movement transition
        var activeMovement by remember { mutableStateOf<Movement>(Movement.StandBy) }

        // To control the auto playing animation
        var shouldPlayAutoAnim by remember { mutableStateOf(true) }

        var textInput by remember { mutableStateOf("") }

        // Generating degree matrix using the active movement
        val degreeMatrix = activeMovement.getMatrixGenerator().getVerifiedMatrix()

        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxSize().background(BACKGROUND_BLACK),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Building clock matrix
                repeat(ROWS) { i ->
                    Row {
                        repeat(COLUMNS) { j ->
                            val clockData = degreeMatrix[i][j]
                            Clock(
                                needleOneDegree = clockData.degreeOne,
                                needleTwoDegree = clockData.degreeTwo,
                                durationInMillis = activeMovement.durationInMillis,
                                modifier = androidx.compose.ui.Modifier.requiredSize(CLOCK_SIZE.dp)
                            )
                        }
                    }
                }

                // The animation loop
                LaunchedEffect(shouldPlayAutoAnim) {

                    val defaultWaitTime = activeMovement.durationInMillis.toLong() + ENJOY_TIME_IN_MILLIS
                    val mediumDelay = defaultWaitTime - ENJOY_TIME_IN_MILLIS

                    while (shouldPlayAutoAnim) {
                        delay(ENJOY_TIME_IN_MILLIS)

                        activeMovement = Movement.Trance(Movement.Trance.To.SQUARE) // Show square
                        delay(defaultWaitTime)

                        activeMovement = Movement.Trance(to = Movement.Trance.To.FLOWER) // Then flower
                        delay(mediumDelay) // flower doesn't have enjoy time

                        activeMovement = Movement.Trance(to = Movement.Trance.To.STAR) // To star, through circle (auto)
                        delay(mediumDelay)

                        activeMovement = Movement.Trance(to = Movement.Trance.To.FLY) // then fly
                        delay(defaultWaitTime)

                        activeMovement = Movement.Wave(Movement.Wave.State.START)
                        delay(mediumDelay)

                        activeMovement = Movement.Wave(Movement.Wave.State.END)
                        delay(mediumDelay)

                        // Ripple
                        activeMovement = Movement.Ripple(to = Movement.Ripple.To.START) // then ripple start
                        delay(defaultWaitTime)

                        activeMovement = Movement.Ripple(to = Movement.Ripple.To.END) // then ripple end
                        delay(defaultWaitTime)

                        // Time table
                        activeMovement = Movement.Ripple(to = Movement.Ripple.To.TIME_TABLE) // then ripple start
                        delay(defaultWaitTime)

                        // Ripple again
                        activeMovement = Movement.Ripple(to = Movement.Ripple.To.START) // then ripple start
                        delay(defaultWaitTime)

                        activeMovement = Movement.Ripple(to = Movement.Ripple.To.END) // then ripple end
                        delay(defaultWaitTime)

                        activeMovement = Movement.Time() // then show time
                        delay(defaultWaitTime)
                    }

                }

                BottomToolBar(isAnimPlaying = shouldPlayAutoAnim,

                    onShowTimeClicked = {
                        shouldPlayAutoAnim = false // stop auto play
                        infiniteLoopScope.launch {
                            while (true) {
                                activeMovement = Movement.Time() // then show time
                                delay(activeMovement.durationInMillis.toLong())
                            }
                        }
                    }, onPlayClicked = {
                        shouldPlayAutoAnim = true
                        infiniteLoopScope.cancel()
                    }, onStopClicked = {
                        shouldPlayAutoAnim = false
                        activeMovement = Movement.StandBy
                    })
            }
        }
    }
}