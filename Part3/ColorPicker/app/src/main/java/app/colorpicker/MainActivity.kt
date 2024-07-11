package app.colorpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxWithConstraints(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.width(min(400.dp, maxWidth)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val color = remember { mutableStateOf(Color.Magenta) }
                    ColorPicker(color)
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color.value),
                        text = "#${color.value.toArgb().toUInt().toString(16)}",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4.merge(
                            TextStyle(
                                color = color.value.complementary()
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ColorPicker(color: MutableState<Color>) {
    val red = color.value.red
    val green = color.value.green
    val blue = color.value.blue
    Column(modifier = Modifier.width(400.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Red",
                color = Color.Red,
                modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
            )
            Slider(
                value = red,
                onValueChange = { color.value = Color(it, green, blue) },
                modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Green",
                color = Color.Green,
                modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
            )
            Slider(
                value = green,
                onValueChange = { color.value = Color(red, it, blue) },
                modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Blue",
                color = Color.Blue,
                modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
            )
            Slider(
                value = blue,
                onValueChange = { color.value = Color(red, green, it) },
                modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
            )
        }
    }
}

fun Color.complementary() = Color(
    red = 1F - red,
    green = 1F - green,
    blue = 1F - blue
)