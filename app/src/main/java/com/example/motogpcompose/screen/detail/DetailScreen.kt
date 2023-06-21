package com.example.motogpcompose.screen.detail


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.motogpcompose.R
import coil.compose.AsyncImage
import com.example.motogpcompose.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.motogpcompose.model.Injection
import com.example.motogpcompose.screen.UiState
import com.example.motogpcompose.ui.theme.MotogpComposeTheme

@Composable
fun DetailScreen(
    heroId: Long,
    viewModel: DetailRewardViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getRewardById(heroId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.hero.name,
                    data.hero.desc,
                    data.hero.photoUrl,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailContent(
    name: String,
    desc: String,
    photoUrl: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    MotogpComposeTheme() {
        DetailContent(
            "Marquez",
            "Marc Márquez Alentà (lahir 17 Februari 1993) adalah seorang pembalap Grand Prix ",
            "",
            onBackClick = {}
        )
    }
}