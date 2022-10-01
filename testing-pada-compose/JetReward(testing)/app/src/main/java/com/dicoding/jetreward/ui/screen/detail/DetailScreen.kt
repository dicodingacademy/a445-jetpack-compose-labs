package com.dicoding.jetreward.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.jetreward.R
import com.dicoding.jetreward.di.Injection
import com.dicoding.jetreward.ui.ViewModelFactory
import com.dicoding.jetreward.ui.common.UiState
import com.dicoding.jetreward.ui.components.OrderButton
import com.dicoding.jetreward.ui.components.ProductCounter
import com.dicoding.jetreward.ui.theme.JetRewardTheme

@Composable
fun DetailScreen(
    rewardId: Long,
    viewModel: DetailRewardViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getRewardById(rewardId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.reward.image,
                    data.reward.title,
                    data.reward.requiredPoint,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.reward, count)
                        navigateToCart()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    basePoint: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPoint by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.padding(16.dp).clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = stringResource(R.string.required_point, basePoint),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colors.secondary
                )
                Text(
                    text = stringResource(R.string.lorem_ipsum),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(4.dp).background(LightGray))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductCounter(
                1,
                orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
            )
            totalPoint = basePoint * orderCount
            OrderButton(
                text = stringResource(R.string.add_to_cart, totalPoint),
                enabled = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JetRewardTheme {
        DetailContent(
            R.drawable.reward_4,
            "Jaket Hoodie Dicoding",
            7500,
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}