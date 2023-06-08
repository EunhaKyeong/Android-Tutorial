package com.example.composebasic

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class TextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ColumnContainer()
                }
            }
        }
    }
}

@Composable
private fun ColumnContainer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val context: Context = LocalContext.current

        TextContainer(
            text = "Hello Android!"
        )

        ClickableText(
            text = AnnotatedString("Click !!"),
            onClick = {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
            }
        )

        TextContainer(
            modifier = Modifier.background(Color.Yellow),
            text = "Hello Android!".repeat(10)
        )

        TextContainer(
            modifier = Modifier.background(Color.Yellow),
            text = stringResource(R.string.content_dummy_short),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textDecoration = TextDecoration.combine(listOf(TextDecoration.LineThrough, TextDecoration.Underline))
        )

        TextContainer(
            modifier = Modifier.background(Color.Green),
            text = stringResource(R.string.content_dummy_short),
            fontWeight = FontWeight.Black,
        )

        Text(
            modifier = Modifier.background(Color.Yellow),
            text = buildAnnotatedString {
                append("열락의 만물은 불어 있다. 무엇을 싶이 인류의 ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue, fontSize = 32.sp)) {
                    append("창공")
                }
                append("에 가치를 아니다. 피부가 군영과 생명을 별과 인생의 그리하였는가? 보이는 그들은 오아이스도 속에서 부패뿐이다.")
            }
        )

        TextContainer(
            text = stringResource(R.string.content_dummy_long)
        )
    }
}

@Composable
private fun TextContainer(modifier: Modifier = Modifier, text: String, fontWeight: FontWeight = FontWeight.Normal, fontSize: TextUnit = TextUnit.Unspecified, textDecoration: TextDecoration? = null) {
    Text(
        modifier = modifier,
        text = text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        textDecoration = textDecoration
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview8() {
    ComposeBasicTheme {
        ColumnContainer()
    }
}