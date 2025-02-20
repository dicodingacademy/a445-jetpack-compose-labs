package com.dicoding.jetreward.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.jetreward.ui.theme.JetRewardTheme
import org.w3c.dom.Text

@Composable
fun ProductCounter(
    orderId: Long,
    orderCount: Int,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 110.dp, height = 40.dp)
            .padding(4.dp)
    ) {
        CounterButton(
            text = "—",
            modifier = Modifier
                .weight(1f)
                .size(30.dp)
                .padding(1.dp),
            onClick = {
                onProductDecreased(orderId)
            },
        )
        Text(
            text = orderCount.toString(),
            modifier = Modifier
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        CounterButton(
            text = "+",
            modifier = Modifier
                .weight(1f)
                .size(30.dp)
                .padding(1.dp),
            onClick = {
                onProductIncreased(orderId)
            },
        )
    }
}

@Composable
fun CounterButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedIconButton(
        onClick = {
            onClick()
        },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(size = 5.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
fun ProductCounterPreview() {
    JetRewardTheme {
        ProductCounter(
            orderId = 1,
            orderCount = 0,
            onProductIncreased = { },
            onProductDecreased = { }
        )
    }
}
