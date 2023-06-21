package com.example.motogpcompose.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.motogpcompose.R

@Composable
fun ProfileScreen(
    name: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(400.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = email,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}