package com.example.compose_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_navigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationView()
                }
            }
        }
    }
}

@Composable
fun NavigationView() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home.route,
    ) {
        composable(Home.route) {
            HomeScreen() {
                navController.navigate(Settings.route)
            }
        }

        composable(Settings.route) {
            SettingsScreen(
                goBackToHome = {
                    navController.popBackStack()
                },
                goToProfile = {
                    navController.navigate(Profile.route)
                }
            )
        }

        composable(Profile.route) {
            ProfileScreen {
                //inclusive: 지정한 route 까지도 pop 할 건지 -> true 면 HomeScreen 이전 화면 / false 면 HomeScreen 화면
                navController.popBackStack(Home.route, true)
            }
        }
    }
}

@Composable
fun HomeScreen(onclick:() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Button(onClick = onclick) {
            Text(text = "Go to Settings")
        }
    }
}

@Composable
fun SettingsScreen(goBackToHome:() -> Unit, goToProfile:() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Settings Screen")
        Button(onClick = goBackToHome) {
            Text(text = "Go back to Home")
        }
        Button(onClick = goToProfile) {
            Text(text = "Go to Profile")
        }
    }
}

@Composable
fun ProfileScreen(onclick:() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen")
        Button(onClick = onclick) {
            Text(text = "Go back to Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeNavigationTheme {
        NavigationView()
    }
}