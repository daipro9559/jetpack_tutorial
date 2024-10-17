package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.example.myapplication.ui.theme.MyApplicationTheme

class BasicLayoutActivity : ComponentActivity() {
    val alignYourData = listOf(
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier,
                    bottomBar = {BottomBar()}
                    ){paddingValues ->
                    HomeContent(alignYourData)
                }
            }
        }
    }
}

@Composable
fun BottomBar(){
    NavigationBar {
        NavigationBarItem(  icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        },
            label = {
                Text("Home")
            },
            selected = true,
            onClick = {})
        NavigationBarItem(  icon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
            label = {
                Text("Seach")
            },
            selected = true,
            onClick = {})
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {
        },
        placeholder = {
            Text("Search")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
    )
}

@Composable
fun AlignYourBodyElement(@DrawableRes drawable: Int, name: String, modifier: Modifier = Modifier) {
    Column {
        Image(
            painter = painterResource(drawable),
            contentDescription = "",
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = name,
            modifier = modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AlignYourBodyRow(items: List<AlignYourBody>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(items) { index, data ->
            AlignYourBodyElement(data.drawable, data.content)
        }
    }
}

@Composable
fun FavoriteItem(@DrawableRes img: Int, content: String, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .width(255.dp)
                .wrapContentHeight()
                .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = "",

                contentScale = ContentScale.Crop,
                modifier = modifier
                    .width(80.dp)
                    .aspectRatio(1f)

            )

            Text(
                text = content,
                modifier = modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun FavoriteGrid(items: List<AlignYourBody>, modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(168.dp)
    ) {
        itemsIndexed(items) { i, d ->
            FavoriteItem(d.drawable, d.content)
        }
    }
}

@Composable
fun HomeSection(title: String, modifier: Modifier = Modifier,content: @Composable ()-> Unit){
    Column {
        Text(title)
        Spacer(modifier.height(10.dp))
        content()
    }
}

@Composable
fun HomeContent(data: List<AlignYourBody>, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ){
        SearchBar(modifier = modifier)
        HomeSection(title = "Align your body") {
            AlignYourBodyRow(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }
        HomeSection(title = "Favorite collection") {
            FavoriteGrid(data)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreView() {
    SearchBar()
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyPreview() {
    val alignYourData = listOf(
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content")
    )
    AlignYourBodyRow(alignYourData)
}

@Preview
@Composable
fun FavoriteItemPreview() {
    FavoriteItem(R.drawable.img_header, "Content")
}

@Preview(name = "FavoriteGridPreview")
@Composable
fun FavoriteGridPreview() {
    val alignYourData = listOf(
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content"),
        AlignYourBody(R.drawable.img_header, "Content")
    )
    FavoriteGrid(alignYourData)
}
