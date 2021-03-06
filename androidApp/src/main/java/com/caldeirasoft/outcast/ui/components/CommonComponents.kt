package com.caldeirasoft.outcast.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.caldeirasoft.outcast.domain.models.StoreCollectionPodcasts
import com.caldeirasoft.outcast.ui.ambient.ActionsAmbient
import com.caldeirasoft.outcast.ui.util.toast

@Composable
fun LoadingScreen()
{
    Box(
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(t: Throwable) {
    ContextAmbient.current.toast("${t.message}")

    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Failed to load team",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .gravity(Alignment.CenterVertically)
        )
    }
}

@Composable
fun StoreHeadingSection(title:String) {
    val actions = ActionsAmbient.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            title,
            modifier = Modifier,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun StoreHeadingSectionWithLink(title:String, url: String) {
    val actions = ActionsAmbient.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable(onClick = { actions.navigateToStoreEntry(url) })
    ) {
        val (text, icon) = createRefs()
        Text(
            title,
            modifier = Modifier
                .constrainAs(text) {
                    linkTo(top = parent.top, bottom = parent.bottom)
                    linkTo(start = parent.start, end = icon.start)
                    width = Dimension.fillToConstraints
                },
            style = MaterialTheme.typography.h6
        )
        Icon(asset = Icons.Filled.ArrowForward,
            modifier = Modifier
                .constrainAs(icon) {
                    linkTo(top = parent.top, bottom = parent.bottom)
                    linkTo(start = text.end, end = parent.end)
                })
    }
}

@Preview
@Composable
fun previewStoreHeadingSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        StoreHeadingSection("Nouveautés et tendances")
    }
}

@Preview
@Composable
fun previewStoreHeadingSectionWithLink() {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        StoreHeadingSectionWithLink("Nouveautés et tendances", "")
    }
}