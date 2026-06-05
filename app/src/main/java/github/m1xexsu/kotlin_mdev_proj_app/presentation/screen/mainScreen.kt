package github.m1xexsu.kotlin_mdev_proj_app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.m1xexsu.kotlin_mdev_proj_app.presentation.component.ArriveComponent
import github.m1xexsu.kotlin_mdev_proj_app.presentation.component.FilterComponent
import github.m1xexsu.kotlin_mdev_proj_app.presentation.component.SearchComponent
import github.m1xexsu.kotlin_mdev_proj_app.presentation.component.TopbarComponent
import github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel.mainscreenviewmodel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: mainscreenviewmodel) {
	val coroutineScope = rememberCoroutineScope()
	var searchQuery by remember { mutableStateOf("") }
	var filterQuery by remember { mutableStateOf("") }
	var showFilter by remember { mutableStateOf(false) }
	var showSearchResults by remember { mutableStateOf(false) }
	val titleState = remember { mutableStateOf("Маршруты") }

	LaunchedEffect(Unit) {
		viewModel.loadStations()
	}

	val points = remember(viewModel.stations) {
		viewModel.stations.map { it.station_id to it.station_name }
	}

	Scaffold(
		topBar = {
			TopbarComponent(
				text = titleState,
				triggersearch = { showSearchResults = !showSearchResults },
				triggerfilter = { showFilter = !showFilter }
			)
		}
	) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			SearchComponent(
				query = searchQuery,
				onQueryChange = {
					searchQuery = it
					showSearchResults = true
				},
				points = points,
				onPointSelected = { stationId ->
					coroutineScope.launch { viewModel.loadArrivals(stationId) }
				},
				onFilterClick = { showFilter = !showFilter },
				isExpanded = showSearchResults,
				onExpandedChange = { showSearchResults = it }
			)

			FilterComponent(
				isVisible = showFilter,
				searchQuery = filterQuery,
				onSearchQueryChange = { filterQuery = it },
				points = points,
				onPointSelected = { stationId ->
					coroutineScope.launch { viewModel.loadArrivals(stationId) }
				}
			)

			if (viewModel.arrivals.isEmpty()) {
				Text(
					text = "Выберите станцию для отображения рейсов",
					style = MaterialTheme.typography.bodyLarge,
					modifier = Modifier.padding(16.dp)
				)
			} else {
				LazyColumn {
					items(viewModel.arrivals) { arrive ->
						ArriveComponent(
							busname = arrive.bus_name,
							startstationname = arrive.start_station_name,
							endstationname = arrive.end_station_name,
							load = arrive.load,
							arrivetime = remember { androidx.compose.runtime.mutableIntStateOf(0) },
							starttime = arrive.arrive_start,
							endtime = arrive.arrive_end
						)
					}
				}
			}
		}
	}

}