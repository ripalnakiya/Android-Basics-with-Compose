package com.ripalnakiya.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ripalnakiya.lunchtray.datasource.DataSource
import com.ripalnakiya.lunchtray.ui.AccompanimentMenuScreen
import com.ripalnakiya.lunchtray.ui.CheckoutScreen
import com.ripalnakiya.lunchtray.ui.EntreeMenuScreen
import com.ripalnakiya.lunchtray.ui.OrderViewModel
import com.ripalnakiya.lunchtray.ui.SideDishMenuScreen
import com.ripalnakiya.lunchtray.ui.StartOrderScreen

@Composable
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = LunchTrayScreen.valueOf(backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name),
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = { navController.navigate(LunchTrayScreen.Entree.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(LunchTrayScreen.Entree.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.SideDish.name) },
                    onSelectionChanged = { viewModel.updateEntree(it) },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
            composable(LunchTrayScreen.SideDish.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Accompaniment.name) },
                    onSelectionChanged = { viewModel.updateSideDish(it) },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
            composable(LunchTrayScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { navController.navigate(LunchTrayScreen.Checkout.name) },
                    onSelectionChanged =  { viewModel.updateAccompaniment(it) },
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
            composable(LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium),
                        )
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController,
) {
    viewModel.resetOrder()
    navController.popBackStack(route = LunchTrayScreen.Start.name, inclusive = false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    currentScreen: LunchTrayScreen,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                    )
                }
            }
        }
    )
}

enum class LunchTrayScreen(@field:StringRes val title: Int) {
    Start(R.string.app_name),
    Entree(R.string.choose_entree),
    SideDish(R.string.choose_side_dish),
    Accompaniment(R.string.choose_accompaniment),
    Checkout(R.string.order_checkout),
}