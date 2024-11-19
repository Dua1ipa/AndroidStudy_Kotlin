package com.example.constraint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.constraint.ui.theme.ConstraintTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintTheme {

            }
        }
    }
}

@Composable
fun ConstraintThemeDetail(cardData : CardData) {
    val placeHolder = Color(0x33000000)
    Card (
        modifier = Modifier.padding(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
        ) {
            val (profileImage, author, description) = createRefs()
            AsyncImage(
                model = cardData.imageUri,
                contentDescription = cardData.imageDescription,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolderColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .constrainAs(profileImage) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start, margin = 8.dp)
//                        linkTo(parent.start, parent.bottom)
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = cardData.author,
                modifier = Modifier.constrainAs(author){
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                }
            )
            Text(
                text = cardData.description,
                modifier = Modifier.constrainAs(author){
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )
            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed)
            constrain(chain){
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
//            Row (
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(8.dp)
//            ){
//                Spacer(modifier = Modifier.size(8.dp))
//                Column {
//                    Text(text = cardData.author)
//                    Text(text = cardData.description)
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}