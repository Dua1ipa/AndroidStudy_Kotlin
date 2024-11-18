package com.example.button

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.button.ButtonExample
import com.example.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
                ButtonExample(onButtonClicked = {
                    Toast.makeText(this, "버튼 클릭", Toast.LENGTH_SHORT).show()
                }
                )
            }
        }
    }
}

@Composable
fun ButtonExample(onButtonClicked: () -> Unit) {
    Button(onClick = onButtonClicked,
        enabled = true,  //버튼이 활성화 상태
        border = BorderStroke(10.dp, Color.Magenta),  //버튼의 테두리를 설정 (10.dp 두께와 자주색(Magenta) 색상으로 지정)
        shape = CircleShape,  //버튼의 모양을 원형으로
        contentPadding = PaddingValues(20.dp))  //버튼 내부의 내용에 대해 패딩 값을 설정(20.dp의 여백을 추가)
    {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null  //접근성을 위한 텍스트 설명
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "버튼")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ButtonExample(onButtonClicked = {})
}