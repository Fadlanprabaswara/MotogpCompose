package com.example.motogpcompose.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.motogpcompose.ui.theme.MotogpComposeTheme

@Composable
fun HeroItem(
    name: String,
    desc: String,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1.0f)
        ){
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = desc,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HeroItemPreview() {
    MotogpComposeTheme() {
        HeroItem(
            name = "Joan Mir",
            desc = "Joan Mir Mayrata (lahir 1 September 1997) adalah seorang",
            photoUrl = ""
        )
    }
}