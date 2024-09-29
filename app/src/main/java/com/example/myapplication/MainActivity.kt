package com.example.myapplication

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: ProductAdapter? = null
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
//        setContentView(binding.root)
//        doSomeThing()
//        viewModel.product.observe(this){
//            Log.d("Dainv", it.toString())
//            adapter = ProductAdapter(it)
//            binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//            binding.recyclerView.adapter = adapter
//        }
//        setContent {
//            Greeting(viewModel)
//        }
        
    }
}

fun doSomeThing(){
    Log.d("Dainv", "Before do something")
    inlineFunc {
        Log.d("Dainv", "Inside lamda")
    }
    Log.d("Dainv", "Done do something")
}

fun noInline(block: ()-> Unit){
    Log.d("Dainv", "start noInline")
    block()
}

inline fun inlineFunc(block: () -> Unit){
    Log.d("Dainv", "start inline")
    block()
}

@Composable
fun Greeting(viewModel: MyViewModel, modifier: Modifier = Modifier) {
    val isLoading = remember {
        mutableStateOf(false)
    }
    val listItem = remember {
        mutableStateOf<List<Product>?>(null)
    }
    val counter = remember {
        mutableStateOf(0)
    }
    SideEffect {
        Log.d("Dainv", "outer side effect ${counter.value}")
    }
    val key = remember { UUID.randomUUID().toString() }
    Log.d("Dainv", "key = $key" )
    LaunchedEffect(isLoading.value){
        if (isLoading.value) {
            listItem.value = viewModel.fetchProduct()
            isLoading.value = false
        }
    }
    Column {
        Button(onClick = {
            counter.value += 1
            isLoading.value = true
        }) {
            Text("Load Data")
        }
        Button(onClick = {
            counter.value += 1
        }) {
            Text("Button 2 ")
        }
        Text(
            text = "Current counter ${counter.value}",
            modifier = modifier
        )
        Box {
            if(isLoading.value){
                CircularProgressIndicator()
            } else {
                LazyColumn() {
                    listItem?.value?.also {
                        items(it){
                            Text(it.title)
                        }
                    }
                }
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
//        Greeting(viewModel = )
    }
}

@Composable
fun Splash(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Group 1000004817",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = (-59.34375).dp,
                    y = 0.4140625.dp)
                .requiredWidth(width = 513.dp)
                .requiredHeight(height = 522.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = 0.dp,
                    y = (-0.0000152587890625).dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_header),
                contentDescription = "App Icon 1 1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredWidth(width = 140.dp)
                    .requiredHeight(height = 116.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Smart translator",
                    color = Color(0xff151a35),
                    style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "Translate your                    into any language",
                    color = Color(0xff151a35),
                    style = TextStyle(
                        fontSize = 18.sp,
                        letterSpacing = 0.15.sp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 142.dp,
                    y = 482.dp)
                .requiredWidth(width = 62.dp)
                .clip(shape = RoundedCornerShape(3.dp))
                .background(color = Color(0xff0099cc))
                .padding(start = 10.dp,
                    end = 10.dp,
                    top = 2.dp,
                    bottom = 3.dp)
        ) {
            Text(
                text = "photo",
                color = Color.White,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.15.sp))
        }
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun SplashPreview() {
    Splash(Modifier)
}