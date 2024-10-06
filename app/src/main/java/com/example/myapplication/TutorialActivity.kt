package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class TutorialActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val shouldShowOnboarding =  rememberSaveable { mutableStateOf(true) }
    Surface(modifier){
        if(shouldShowOnboarding.value){
            OnboardScreen(continueClick = {shouldShowOnboarding.value = false} )
        }else{
            GreetingScreen(names = List(1000) { "$it" } )
        }
    }
}

@Composable
fun GreetingScreen(
    modifier: Modifier = Modifier,
    names: List<String>
) {
    LazyColumn(modifier.padding(4.dp)) {
        itemsIndexed(names) { index, name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun OnboardScreen(continueClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(1f).background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the basic Codelab!")
        Button(onClick = continueClick) {
            Text("Continue")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = rememberSaveable { mutableStateOf(false) }
    val padding = animateDpAsState(if (expanded.value) 24.dp else 0.dp)
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = padding.value)
            ) {
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(onClick = {
                expanded.value = !expanded.value
            }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }

    }
}