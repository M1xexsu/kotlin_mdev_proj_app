package github.m1xexsu.kotlin_mdev_proj_app.presentation.screen

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel.addscreenviewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addScreen(viewModel: addscreenviewmodel)
{
    Text(text = "Under construction", modifier = Modifier.systemBarsPadding())
}