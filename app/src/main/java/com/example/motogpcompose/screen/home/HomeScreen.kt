package com.example.motogpcompose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.motogpcompose.ViewModelFactory
import com.example.motogpcompose.components.HeroItem
import com.example.motogpcompose.model.Injection
import com.example.motogpcompose.model.OrderHero
import com.example.motogpcompose.screen.home.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRewards()
            }
            is UiState.Success -> {
                HomeContent(
                    orderHero = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun HomeContent(
    orderHero: List<OrderHero>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderHero){data->
            HeroItem(
                name = data.hero.name,
                desc = data.hero.desc,
                photoUrl = data.hero.photoUrl,
                modifier = Modifier.clickable {
                    navigateToDetail(data.hero.id)
                }
            )
        }
    }
}