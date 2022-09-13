package com.dicoding.mycomposenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.mycomposenavigation.ui.theme.MyComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "first"
    ) {
        composable(route = "first") {
            FirstScreen { content ->
                navController.navigate("second/$content")
            }
        }
        composable(
            route = "second/{content}",
            arguments = listOf(
                navArgument("content") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            SecondScreen(
                content = backStackEntry.arguments?.getString("content"),
                navigateBack = { navController.navigateUp() },
            )
        }
    }
}

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navigateToSecondScreen: (String) -> Unit,
) {
    var content by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                navigateToSecondScreen(content)
            }
        ) {
            Text(text = "Second Screen")
        }
    }
}

@Composable
fun SecondScreen(
    content: String?,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    if (content != null) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = content, style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = navigateBack
            ) {
                Text(text = "Go back")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeNavigationTheme {
        MyApp()
    }
}