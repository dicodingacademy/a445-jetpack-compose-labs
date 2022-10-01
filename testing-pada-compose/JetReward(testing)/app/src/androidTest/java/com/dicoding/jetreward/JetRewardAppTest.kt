package com.dicoding.jetreward

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dicoding.jetreward.model.FakeRewardDataSource
import com.dicoding.jetreward.ui.navigation.Screen
import com.dicoding.jetreward.ui.theme.JetRewardTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetRewardAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetRewardTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetRewardApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("RewardList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("RewardList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[4].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

//    @Test
//    fun navHost_cartDecreased_removed() {
//        composeTestRule.onNodeWithText("Jaket Hoodie Dicoding").performClick()
//        assertEquals(navController.currentBackStackEntry?.destination?.route, Screen.DetailReward.route)
//        composeTestRule.onNodeWithText("＋").performClick()
//        composeTestRule.onNodeWithText("Tambah ke Keranjang : 1000 Pts").performClick()
//        assertEquals(navController.currentBackStackEntry?.destination?.route, Screen.Cart.route)
//        composeTestRule.onNodeWithText("Jaket Hoodie Dicoding").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Home").performClick()
//        composeTestRule.onNodeWithText("Token Sertifikasi TensorFlow").performClick()
//        assertEquals(navController.currentBackStackEntry?.destination?.route, Screen.DetailReward.route)
//        composeTestRule.onNodeWithText("＋").performClick()
//        composeTestRule.onNodeWithText("Tambah ke Keranjang : 4500 Pts").performClick()
//        assertEquals(navController.currentBackStackEntry?.destination?.route, Screen.Cart.route)
//        composeTestRule.onAllNodesWithText("＋")[0].performClick()
//        composeTestRule.onNodeWithText("Total pesanan : 10000 Pts").assertExists()
//        composeTestRule.onAllNodesWithText("—")[1].performClick()
//        composeTestRule.onNodeWithText("Jaket Hoodie Dicoding").assertIsNotDisplayed()
//        composeTestRule.onNodeWithText("Total pesanan : 9000 Pts").assertExists()
//    }
}