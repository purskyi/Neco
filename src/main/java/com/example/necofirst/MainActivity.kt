package com.example.necofirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.necofirst.data.ItemModel
import com.example.necofirst.data.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


          myWholeApp(items)

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
fun myWholeApp(items: List<ItemModel>, modifier: Modifier = Modifier){
    val total = remember{ mutableStateOf(0) }
    val counters = remember { mutableStateListOf(*Array(10) { 0 }) }
    val colors = remember { mutableStateListOf(*Array(10) { Color.Cyan }) }

    var expanded: Boolean by remember { mutableStateOf(false) }
    val listExpanded = remember { mutableStateListOf(*Array(10) { expanded }) }



    Scaffold(topBar = { TopAppBarWithAppName(total.value) })
    { BarPadding ->
    LazyColumn(contentPadding = BarPadding) {
        itemsIndexed(items) { index, item ->
            myApp(
              item,
                counterValue = counters[index],
                colorValue = colors[index],
                onCounterClick = {
                    total.value++
                counters[index]++
                when( counters[index]){
                  in 0 .. 10 -> colors[index] = Color.Cyan
                    else -> colors[index] = Color.Red
                }},
                expandedValue = listExpanded[index],
                onClick = {listExpanded[index] = !listExpanded[index]}

            )

        }
        }
    }
}

@Composable
fun myApp(item: ItemModel, counterValue: Int, colorValue: Color, onCounterClick: () -> Unit, expandedValue: Boolean, onClick: () -> Unit,  modifier: Modifier = Modifier) {

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
        Column {


            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = item.imageId
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(text = item.title,
                        modifier = Modifier
                        .clickable {   onClick() }

                    )
                    Text(text = counterValue.toString())
                }

            }
        if (expandedValue){
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = item.content)
                Text(text = counterValue.toString())
            }
        }

        }
    }
    }

}