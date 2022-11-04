package components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconTextButton(
    text: String,
    imageVector: ImageVector,
    onClicked: () -> Unit,
) {

    OutlinedButton(
        onClick = onClicked
    ) {

        Icon(
            imageVector = imageVector,
            tint = Color.White,
            contentDescription = "ToolBar Icon",
            modifier = Modifier.padding(end = 10.dp)
        )

        Text(
            text = text
        )
    }
}