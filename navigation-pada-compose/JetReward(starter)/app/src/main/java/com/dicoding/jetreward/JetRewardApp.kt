package com.dicoding.jetreward

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.jetreward.ui.theme.JetRewardTheme

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
) {

}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetRewardTheme {
        JetRewardApp()
    }
}
