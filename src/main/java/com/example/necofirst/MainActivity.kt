package com.example.necofirst

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.necofirst.ui.theme.NecoFirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {



            var name = "Andrii"
            var job = "Android Developer"


          myWholeApp(name = name, job = job )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithAppName(total: Int = 0, modifier: Modifier = Modifier) {

    CenterAlignedTopAppBar(
        title = {
           Row(modifier = modifier) {
               Text(text = total.toString())

           }
        })


}

@Composable
fun myWholeApp(name: String, job: String, modifier: Modifier = Modifier){
    var total by remember { mutableStateOf(0) }
    val counters = remember { mutableStateListOf(*Array(10) { 0 }) }
    val colors = remember { mutableStateListOf(*List(10) { Color.Cyan }.toTypedArray()) }

    Scaffold(topBar = { TopAppBarWithAppName(total) })
    { BarPadding ->
    LazyColumn(contentPadding = BarPadding) {
        items(10) { index ->
            myApp(
                name = name,
                job = job,
                counterValue = counters[index],
                colorValue = colors[index],
                onCounterClick = { total++
                counters[index]++
                when( counters[index]){
                  in 0 .. 10 -> colors[index] = Color.Cyan
                    else -> colors[index] = Color.Red
                }}
            )

        }
        }
    }
}

@Composable
fun myApp(name: String, job: String, counterValue: Int, colorValue: Color, onCounterClick: () -> Unit, modifier: Modifier = Modifier) {

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            onCounterClick()
        },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ){ Box(modifier = Modifier
        //   .clickable { Log.d("My log", "Clked") }
        .fillMaxWidth()
        .background(colorValue)){
        Row(modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(
                id = R.drawable.android_superhero1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape))

            Column(modifier = Modifier
                .padding(16.dp)){
                Text(text = name,)
                Text(text = job)
                Text(text = counterValue.toString())
            }

        }
    }
    }

}