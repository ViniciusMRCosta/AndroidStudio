package com.example.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dicegame.ui.theme.DiceGameTheme
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

fun DrawScope.circle(offset: (Float) -> Offset) {
    val radius = Dp(20f).value
    drawCircle(
        Color.Black,
        radius = radius,
        center = offset(radius)
    )
}

fun DrawScope.center() {
    circle {
        Offset((size.width / 2) + (it / 2), (size.height / 2) + (it / 2))
    }
}

fun DrawScope.topRight() {
    circle {
        Offset(size.width - it, it * 2)
    }

}

fun DrawScope.topLeft() {
    circle {
        Offset(it * 2, it * 2)
    }
}

fun DrawScope.bottomRight() {
    circle {
        Offset(size.width - it, size.height - it)
    }
}

fun DrawScope.bottomLeft() {
    circle {
        Offset(it * 2, size.height - it)
    }
}

fun DrawScope.rightCenter() {
    circle {
        Offset(size.width - it, size.height / 2)
    }

}

fun DrawScope.leftCenter() {
    circle {
        Offset(it * 2, size.height / 2)
    }
}


fun DrawScope.bullet(number:Int) {
    when(number) {
        1 -> {
            center()
        }
        2 -> {
            topRight()
            bottomLeft()
            }
        3 -> {
            topLeft()
            center()
            bottomRight()
        }
        4 -> {
            topLeft()
            topRight()
            bottomLeft()
            bottomRight()
        }
        5 -> {
            topLeft()
            topRight()
            center()
            bottomRight()
            bottomLeft()
        }
        6 -> {
            topLeft()
            topRight()
            leftCenter()
            rightCenter()
            bottomLeft()
            bottomRight()
        }

    }
}

@Composable
fun Dice(number: Int, modifier: Modifier) {
    Canvas(modifier = modifier.size(96.dp,96.dp)) {
        drawRoundRect(Color.Green,
            cornerRadius =  CornerRadius(20f,20f),
            topLeft = Offset(10f,10f),
            size = this.size
        )
        bullet(number)
    }
}

@Composable
fun App() {
    var r by remember { mutableStateOf(1) }
    var timer by remember {mutableStateOf(0)}

    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay((500 * 1.0f / timer).toLong())
            r =  Random.nextInt(1..6)
            timer -= 1
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {

        Dice(r, Modifier.align(Alignment.Center))

        Button(onClick = {
            timer = 60
        },  modifier = Modifier.align(Alignment.Center).offset(y=(100).dp)) {
            if (timer > 0) {
                Text(text = "Timer: $timer")
            } else {
                Text("Jogar", )
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceGameTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            App()
        }
    }
}