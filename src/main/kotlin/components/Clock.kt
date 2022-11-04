package components

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import utils.CLOCK_CIRCLE_COLOR
import utils.CLOCK_LINE_COLOR
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun Clock(
    needleOneDegree: Float = 270f,
    needleTwoDegree: Float = 0f,
    durationInMillis: Int = 500,
    delay: Int = 0,
    easing: Easing = LinearEasing,
    modifier: Modifier = Modifier,
) {

    val needleOneRadian = (needleOneDegree * Math.PI / 180).toFloat()
    val needleTwoRadian = (needleTwoDegree * Math.PI / 180).toFloat()
    val animationSpec = tween<Float>(durationMillis = durationInMillis, easing = easing, delayMillis = delay)

    val needleOneDegreeAnim by animateFloatAsState(
        needleOneRadian, animationSpec = animationSpec
    )

    val needleTwoDegreeAnim by animateFloatAsState(
        needleTwoRadian,
        animationSpec = animationSpec,
    )

    Canvas(
        modifier = modifier
    ) {

        val needleWidth = size.minDimension * 0.05f

        val radius = size.minDimension / 2f

        drawCircle(
            color = CLOCK_CIRCLE_COLOR, radius = radius
        )

        drawCircle(
            color = CLOCK_LINE_COLOR, radius = needleWidth * 0.487f
        )

        val radius2 = radius - 4

        drawLine(
            color = CLOCK_LINE_COLOR, start = center, end = Offset(
                x = center.x + radius2 * sin(needleOneDegreeAnim),
                y = center.y - radius2 * cos(needleOneDegreeAnim),
            ), strokeWidth = needleWidth
        )

        drawLine(
            color = CLOCK_LINE_COLOR, start = center, end = Offset(
                x = center.x + radius2 * sin(needleTwoDegreeAnim),
                y = center.y - radius2 * cos(needleTwoDegreeAnim),
            ), strokeWidth = needleWidth
        )
    }
}
