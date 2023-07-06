package com.example.inflean_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inflean_compose.ui.theme.InfleanComposeTheme

class TutorialActivity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfleanComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        RoundedCard(isFavorite = false)
                        RoundedCard(isFavorite = true)
                    }
                }
            }
        }
    }
}

@Composable
fun RoundedCard(isFavorite: Boolean, modifier: Modifier = Modifier) {
    var isFavorite by rememberSaveable {
        mutableStateOf(isFavorite)
    }

    Card(
        modifier = modifier
            .padding(8.dp)
            .width(200.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.posters),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                alpha = 0.7f
            )

            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(56.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        RoundedCard(isFavorite = false)
        RoundedCard(isFavorite = true)
    }
}