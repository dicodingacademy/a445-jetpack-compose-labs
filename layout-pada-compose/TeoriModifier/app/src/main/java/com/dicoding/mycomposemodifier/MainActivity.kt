package com.dicoding.mycomposemodifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mycomposemodifier.ui.theme.MyComposeModifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeModifierTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactCard()
                }
            }
        }
    }
}

@Composable
fun ContactCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = {}),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.avatar1),
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(4.dp)
                .border(2.dp, Color.Green, CircleShape)
                .clip(CircleShape)
                .size(60.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (modifier = Modifier.weight(1f)){
            Text(
                text = "Dico Wisesa",
                fontWeight = FontWeight.Bold,
            )
            Text(text = "Online")
        }
//        Icon(
//            imageVector = Icons.Filled.Check,
//            contentDescription = null,
//            modifier = Modifier.offset(x=8.dp, y = 30.dp)
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeModifierTheme {
        ContactCard()
    }
}


