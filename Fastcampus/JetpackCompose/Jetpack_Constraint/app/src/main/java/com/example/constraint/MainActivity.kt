package com.example.constraint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.constraint.ui.theme.ConstraintTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintThemeDetail()
                }
            }
        }
    }
}

@Composable
fun ConstraintThemeDetail() {

    val constrainSet = ConstraintSet {
        val redBox = createRefFor("redBox")
        val magentaBox = createRefFor("magentaBox")
        val greenBox = createRefFor("greenBox")
        val blueBox = createRefFor("blueBox")

        constrain(redBox){
            bottom.linkTo(parent.bottom, margin = 8.dp)
            end.linkTo(parent.end, margin = 4.dp)
        }

        constrain(magentaBox){
            start.linkTo(parent.start, margin = 4.dp)
            end.linkTo(parent.end, margin = 4.dp)
        }

        constrain(greenBox){
            centerTo(parent)
        }

        constrain(blueBox){
            start.linkTo(magentaBox.end)
            top.linkTo(magentaBox.bottom)
        }

    }

    ConstraintLayout(constrainSet, modifier = Modifier.fillMaxSize()) {
//        val (redBox, magentaBox, greenBox, blueBox) = createRefs()
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .layoutId("redBox")
//                .constrainAs(redBox){
//                    bottom.linkTo(parent.bottom, margin = 8.dp)
//                    end.linkTo(parent.end, margin = 4.dp)
//                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .layoutId("magentaBox")
//                .constrainAs(magentaBox){
//                    start.linkTo(parent.start, margin = 4.dp)
//                    end.linkTo(parent.end, margin = 4.dp)
//                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .layoutId("greenBox")
//                .constrainAs(greenBox){
//                    centerTo(parent)
//                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Blue)
                .layoutId("blueBox")
//                .constrainAs(blueBox){
//                    start.linkTo(magentaBox.end)
//                    top.linkTo(magentaBox.bottom)
//                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintTheme {
        ConstraintThemeDetail()
    }
}