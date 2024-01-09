package com.example.navigationexercise

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = "main_screen"){
            MainScreen(navController = navController)
        }
        composable(
            route = "detail_screen/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "GTN"
                    nullable = true
                }
            )
        ){backStackEntry ->
            DetailScreen(name = backStackEntry.arguments?.getString("name"),navController= navController)
        }
        composable(
            route = "detail_screen2/{name}/{age}",
            arguments = listOf(navArgument("age") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen2(name = backStackEntry.arguments?.getString("name"),age = backStackEntry.arguments?.getString("age"), navController = navController)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
    var text by remember {
        mutableStateOf("")
    }
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)){
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "What is your name?")
        }
        Spacer(modifier =Modifier.height(10.dp))
        TextField(
            value = text,
            onValueChange = {
                text = it
        },
        modifier = Modifier.fillMaxWidth()
            )
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = { navController.navigate(Screen.DetailScreen.withArgs(text)) },
            modifier = Modifier.align(Alignment.End)) {
                Text(text = "Enter name")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(name: String?,navController: NavController){
    var text2 by remember {
        mutableStateOf("")
    }
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp))
    {
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Hello, $name! what is your age?")
        }
        Spacer(modifier =Modifier.height(10.dp))
        TextField(value = text2, onValueChange = {
            text2 = it
        }
        )
        Spacer(modifier =Modifier.height(10.dp))
        Button(
            onClick = { navController.navigate(Screen.DetailScreen2.withArgs(name,text2)) },
            modifier = Modifier) {
            Text(text = "Enter age")
        }
        Spacer(modifier =Modifier.height(10.dp))
        Button(
            onClick = { navController.navigate(Screen.MainScreen.route) },
            modifier = Modifier) {
            Text(text = "Go Back")
        }
    }
}
@Composable
fun DetailScreen2(name: String?,age: String?, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    )
    {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$name your Age is $age!")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(name))
            },
            modifier = Modifier
        ) {
            Text(text = "Go Back")
        }
    }
}