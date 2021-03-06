package com.caldeirasoft.outcast.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.caldeirasoft.outcast.domain.models.StorePodcast
import com.caldeirasoft.outcast.ui.ambient.ActionsAmbient
import com.caldeirasoft.outcast.ui.theme.colors
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun StorePodcastGridItem(podcast: StorePodcast)
{
    val actions = ActionsAmbient.current
    Column(modifier = Modifier
        .padding(horizontal = 8.dp)
        .preferredWidth(100.dp)
        .clickable(onClick = { actions.navigateToStoreEntry(podcast.url) })) {
        Card(
            backgroundColor = colors[1],
            shape = RoundedCornerShape(8.dp)
        )
        {
            CoilImage(
                imageModel = podcast.getArtworkUrl(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .preferredSize(100.dp))
        }
        androidx.compose.material.Text(
            podcast.name,
            modifier = Modifier.width(100.dp),
            overflow = TextOverflow.Ellipsis, maxLines = 2, style = MaterialTheme.typography.body2
        )
        androidx.compose.material.Text(
            podcast.artistName,
            modifier = Modifier.width(100.dp),
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption
        )
    }
}

