package com.example.surface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.surface.ui.theme.SurfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SurfaceTheme {
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface (
        border = BorderStroke(
            width = 2.dp,
            color = Color.Magenta
        ),
        modifier = Modifier.padding(5.dp),
        shape = CircleShape
    ) {
        Text(
            text = "Hello $name!"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SurfaceTheme {
        Greeting("Android")
    }
}