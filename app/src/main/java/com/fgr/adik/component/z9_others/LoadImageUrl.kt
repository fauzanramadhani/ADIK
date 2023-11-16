package com.fgr.adik.component.z9_others

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.fgr.adik.R
import com.fgr.adik.component.loading.LoadingCircleAnimation

@Composable
fun LoadImageUrl(
    url: String = "",
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(url)
    var stateImageLoad by rememberSaveable {
        mutableStateOf(false)
    }
    var stateImageError by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(painter.state) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> {
                stateImageLoad = false
                stateImageError = true
            }

            AsyncImagePainter.State.Empty -> {
                stateImageLoad = true
                stateImageError = false
            }

            is AsyncImagePainter.State.Loading -> {
                stateImageLoad = true
                stateImageError = false
            }

            is AsyncImagePainter.State.Success -> {
                stateImageLoad = false
                stateImageError = false
            }
        }
    }
    Box(modifier = modifier) {
        if (stateImageLoad) {
            LoadingCircleAnimation(
                modifier = modifier
                    .align(Alignment.Center)
            )
        } else {
            Image(
                painter = if (stateImageError) {
                    painterResource(id = R.drawable.image_not_found_illustration)
                } else {
                    painter
                },
                contentDescription = null,
                modifier = modifier
                    .align(Alignment.Center)
            )
        }
    }
}