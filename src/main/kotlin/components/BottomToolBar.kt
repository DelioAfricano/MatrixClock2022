package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Stop
import androidx.compose.material.icons.outlined.Update
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomToolBar(
    isAnimPlaying: Boolean,
    onShowTimeClicked: () -> Unit,
    onPlayClicked: () -> Unit,
    onStopClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(
                top = 18.dp,
                start = 50.dp,
                end = 20.dp,
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Play Button
        if (isAnimPlaying.not()) {
            IconTextButton(
                text = "START AUTOPLAY",
                imageVector = Icons.Outlined.PlayArrow,
                onClicked = onPlayClicked
            )
        }

        // Stop Button
        if (isAnimPlaying) {
            IconTextButton(
                text = "STOP AUTOPLAY 😎",
                imageVector = Icons.Outlined.Stop,
                onClicked = onStopClicked
            )
        }

        // Time Button
        if (isAnimPlaying) {
            IconTextButton(
                text = "SHOW TIME 😁",
                imageVector = Icons.Outlined.Update,
                onClicked = onShowTimeClicked
            )
        }
    }
}

