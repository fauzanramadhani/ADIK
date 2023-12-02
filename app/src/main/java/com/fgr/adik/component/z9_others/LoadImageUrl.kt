package com.fgr.adik.component.z9_others

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.fgr.adik.R
import com.fgr.adik.component.loading.LoadingCircleAnimation
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun LoadImageUrl(
    modifier: Modifier = Modifier,
    url: String = "",
) {
    SubcomposeAsyncImage(
        model = url,
        loading = {
            LoadingCircleAnimation(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        },
        error = {
            painterResource(id = R.drawable.image_not_found_illustration)
        },
        contentScale = ContentScale.Crop,
        modifier = modifier,
        contentDescription = null,
    )
}

@Preview
@Composable
fun LoadImageUrlPreview() {
    ADIKTheme {
        LoadImageUrl()
    }
}