package com.dicoding.mycomposelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mycomposelayout.ui.theme.MyComposeLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ContactCard()
                }
            }
        }
    }
}

@Composable
fun ContactCard() {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.avatar1),
                contentDescription = "Avatar",
                modifier = Modifier.size(60.dp).clip(CircleShape)
            )
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Online Status",
                tint = Color.Green,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "Dico Wisesa",
                fontWeight = FontWeight.Bold,
            )
            Text("Online")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeLayoutTheme {
        ContactCard()
    }
}

@Composable
fun ButtonA() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_200)),
        modifier = Modifier.size(80.dp)
    ) {
        Text("A")
    }
}

@Composable
fun ButtonB() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_500)),
        modifier = Modifier.size(60.dp)
    ) {
        Text("B")
    }
}

@Composable
fun ButtonC(text: String = "C") {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700)),
        modifier = Modifier.size(40.dp)
    ) {
        Text(text)
    }
}

@Composable
fun RowButton() {
    Row(modifier = Modifier.padding(16.dp)) {
        ButtonC("")
        Spacing()
        ButtonC("")
        Spacing()
        ButtonC("")
    }
}

@Composable
fun ColumnButton() {
    Column(modifier = Modifier.padding(16.dp)) {
        ButtonC("")
        Spacing()
        ButtonC("")
        Spacing()
        ButtonC("")
    }
}

@Composable
fun BoxButton() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(16.dp)) {
        ButtonA()
        ButtonB()
        ButtonC("")
    }
}

@Composable
fun BasicLayout() {
    Row(modifier = Modifier.padding(16.dp)) {
        RowButton()
        ColumnButton()
        BoxButton()
    }
}

@Preview(showBackground = true)
@Composable
fun BoxButtonPreview() {
    BasicLayout()
}

@Composable
fun ColumnAlignment() {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(horizontalAlignment = Alignment.Start) {
            Text("Start")
            Spacing()
            ButtonA()
            Spacing()
            ButtonB()
            Spacing()
            ButtonC()
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("CenterHorizontally")
            Spacing()
            ButtonA()
            Spacing()
            ButtonB()
            Spacing()
            ButtonC()
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("End")
            Spacing()
            ButtonA()
            Spacing()
            ButtonB()
            Spacing()
            ButtonC()
        }
    }
}

@Composable
fun Spacing() {
    Spacer(modifier = Modifier.padding(8.dp))
}

@Preview(showBackground = true)
@Composable
fun ColumnAlignmentPreview() {
    ColumnAlignment()
}

@Composable
fun RowAlignment() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Top                      ",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            ButtonA()
            ButtonB()
            ButtonC()
        }
        Spacing()
        Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("CenterVertically")
            ButtonA()
            ButtonB()
            ButtonC()
        }
        Spacing()
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("Bottom                ", modifier = Modifier.align(Alignment.CenterVertically))
            ButtonA()
            ButtonB()
            ButtonC()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowAlignmentPreview() {
    RowAlignment()
}

@Composable
fun BoxAlignment() {
    Box(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        ButtonWithText("TopStart", Modifier.align(Alignment.TopStart))
        ButtonWithText("TopCenter", Modifier.align(Alignment.TopCenter))
        ButtonWithText("TopEnd", Modifier.align(Alignment.TopEnd))
        ButtonWithText("CenterStart", Modifier.align(Alignment.CenterStart))
        ButtonWithText("Center", Modifier.align(Alignment.Center))
        ButtonWithText("CenterEnd", Modifier.align(Alignment.CenterEnd))
        ButtonWithText("BottomStart", Modifier.align(Alignment.BottomStart))
        ButtonWithText("BottomCenter", Modifier.align(Alignment.BottomCenter))
        ButtonWithText("BottomEnd", Modifier.align(Alignment.BottomEnd))
    }
}

@Preview(showBackground = true, widthDp = 500, heightDp = 200)
@Composable
fun BoxAlignmentPreview() {
    BoxAlignment()
}

@Composable
fun ButtonWithText(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_500)),
        modifier = modifier.padding(4.dp)
    ) {
        Text(text, textAlign = TextAlign.Center)
    }
}

@Composable
fun RowButtonMaxWidth(arrangement: Arrangement.Horizontal = Arrangement.Start) {
    Row(horizontalArrangement = arrangement, modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        ButtonC("")
        Spacing()
        ButtonC("")
        Spacing()
        ButtonC("")
    }
}

@Composable
fun Arrangement() {
    Column(modifier = Modifier.fillMaxWidth()) {
        RowButtonMaxWidth(Arrangement.Start)
        RowButtonMaxWidth(Arrangement.End)
        RowButtonMaxWidth(Arrangement.Center)
        RowButtonMaxWidth(Arrangement.SpaceEvenly)
        RowButtonMaxWidth(Arrangement.SpaceAround)
        RowButtonMaxWidth(Arrangement.SpaceBetween)
    }
}

@Preview(showBackground = true)
@Composable
fun ArrangementPreview() {
    Arrangement()
}

@Composable
fun Weight() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Row {
            ButtonWithText("1", Modifier.weight(1f))
            ButtonWithText("1", Modifier.weight(1f))
            ButtonWithText("1", Modifier.weight(1f))
        }
        Row {
            ButtonWithText("1", Modifier.weight(1f))
            ButtonWithText("2", Modifier.weight(2f))
            ButtonWithText("1", Modifier.weight(1f))
        }
        Row {
            ButtonWithText("1", Modifier.weight(1f))
            ButtonWithText("2", Modifier.weight(2f))
            ButtonWithText("3", Modifier.weight(3f))
        }

        Row {
            ButtonWithText("1", Modifier.weight(1f))
            ButtonWithText("2", Modifier.weight(2f))
            ButtonWithText("3 fill true", Modifier.weight(weight = 3f, fill = true))
        }
        Row {
            ButtonWithText("1", Modifier.weight(1f, true))
            ButtonWithText("2", Modifier.weight(2f, true))
            ButtonWithText("3 fill false", Modifier.weight(weight = 3f, fill = false))
        }
        Row {
            ButtonWithText("1", Modifier.weight(1f))
            ButtonWithText("0")
            ButtonWithText("0")
        }
    }
}

@Preview(showBackground = true, widthDp = 600)
@Composable
fun WeightPreview() {
    Weight()
}