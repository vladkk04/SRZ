package com.electro.fish.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.compose.ImagePainter
import coil3.compose.SubcomposeAsyncImage
import com.electro.essential.model.ImageSource

@Composable
fun AppImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

@Composable
fun AppAsyncImage(
    imageSource: ImageSource,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    when (imageSource) {
        ImageSource.Empty -> EmptyImageView(
            modifier = modifier
        )
        is ImageSource.Remote -> RemoteImageView(
            url = imageSource.url,
            contentScale = contentScale,
            modifier = modifier,
            contentDescription = contentDescription
        )
        is ImageSource.Local -> AsyncImage(
            model = imageSource.drawableResId,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

@Composable
fun RemoteImageView(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        error = { EmptyImageView() }
    )

}

@Composable
private fun EmptyImageView(
    modifier: Modifier = Modifier
) {
    Image(
        imageVector = Icons.Default.Image,
        contentDescription = null,
        modifier = modifier
    )
}