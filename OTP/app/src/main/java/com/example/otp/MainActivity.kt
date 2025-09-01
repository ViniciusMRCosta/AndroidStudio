package com.example.otp

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.otp.ui.theme.OTPTheme
import com.example.otp.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OTPTheme {

            }
        }
    }
}

@Composable
fun OTPField() {
    Column {
        val digits = 4
        var result by remember { mutableStateOf("") }
        var colorBg by remember { mutableStateOf(Color.Transparent) }

        BasicTextField(
            result, onValueChange = {
                if (it.length <= digits) {
                    result = it
                }
            },
            modifier = Modifier.onFocusChanged {
                colorBg = if (it.isFocused) Purple80 else Color.Black

            }) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(digits) { index ->
                    val number = if (index < result.length) result[index].toString() else ""
                    val bg = if (index < result.length) Color.Black else colorBg

                    Text(
                        modifier = Modifier
                            .width(35.dp)
                            .background(Color.White, RoundedCornerShape(4.dp))
                            .border(1.dp, bg, RoundedCornerShape(4.dp))
                            .padding(10.dp),
                        textAlign = TextAlign.Center,
                        text = number
                    )
                }
            }
        }
        if (result.length == digits) {
            Text("processando...")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OTPTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            OTPField()
        }
    }
}