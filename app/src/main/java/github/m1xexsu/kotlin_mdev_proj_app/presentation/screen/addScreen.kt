package github.m1xexsu.kotlin_mdev_proj_app.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel.addscreenviewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addScreen(viewModel: addscreenviewmodel) {
    LaunchedEffect(Unit) {
        viewModel.loadStations()
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Админ-панель", style = MaterialTheme.typography.titleLarge)
            Text(text = "Станций загружено: ${viewModel.stations.size}")
            Text(text = "Рейсов загружено: ${viewModel.arrivals.size}")
            Text(text = "Добавление автобусов, станций и рейсов можно подключить к формам позже.")
        }
    }
}