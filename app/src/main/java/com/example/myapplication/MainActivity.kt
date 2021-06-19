package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                MyScreenContent(List(1000) { "Hello Android #$it" })
            }
        }
    }
}
@Composable
fun MyApp(content:@Composable () ->Unit ){
    content()
}

@Composable
fun PeopleList(names:List<String>,modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(items = names){
            name->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun MyScreenContent(names: List<String>){
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        PeopleList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun Counter(count : Int,updateCount:(Int) -> Unit){
    Button(
        onClick = {updateCount(count+1)},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've have clicked $count times")
    }
}

@Composable
fun Greeting(name:String){
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
    Column(modifier = Modifier.padding(16.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.header),
//            contentDescription = null,
//            modifier = Modifier
//                .height(180.dp)
//                .fillMaxWidth()
//                .clip(shape = RoundedCornerShape(8.dp)),
//            contentScale = ContentScale.Crop
//        )
        Text(
            text ="hello $name",
            modifier = Modifier
                .padding(24.dp)
                .background(color = backgroundColor)
                .clickable(onClick = {isSelected = !isSelected})
            )
    }
}

@Preview
@Composable
fun DefaultPreview(){
    MyApp{
        MyScreenContent(List(1000) { "Hello Android #$it" })
    }
}