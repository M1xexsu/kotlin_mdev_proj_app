package github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import github.m1xexsu.kotlin_mdev_proj_app.data.model.arriveDTO
import github.m1xexsu.kotlin_mdev_proj_app.data.model.stationDTO
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addarriveusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addbususecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addstationusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getarrivereusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getarrivesortusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getstationsusecase

class mainscreenviewmodel(
    private val getstationsusecase: getstationsusecase,
    private val getarrivereusecase: getarrivereusecase,
    private val getarrivesortusecase: getarrivesortusecase
): ViewModel() {

    var stations by mutableStateOf<List<stationDTO>>(emptyList())
        private set

    var arrivals by mutableStateOf<List<arriveDTO>>(emptyList())
        private set

    var selectedStationId by mutableStateOf<Int?>(null)
        private set

    var selectedDestinationId by mutableStateOf<Int?>(null)
        private set

    suspend fun loadStations() {
        try {
            stations = getstationsusecase()
        } catch (e: Exception) {
            stations = emptyList()
        }
    }

    suspend fun loadArrivals(stationId: Int) {
        selectedStationId = stationId
        try {
            arrivals = getarrivereusecase(stationId)
        } catch (e: Exception) {
            arrivals = emptyList()
        }
    }

    suspend fun loadSortedArrivals(stationId: Int, destinationId: Int) {
        selectedStationId = stationId
        selectedDestinationId = destinationId
        try {
            arrivals = getarrivesortusecase(stationId, destinationId)
        } catch (e: Exception) {
            arrivals = emptyList()
        }
    }
}