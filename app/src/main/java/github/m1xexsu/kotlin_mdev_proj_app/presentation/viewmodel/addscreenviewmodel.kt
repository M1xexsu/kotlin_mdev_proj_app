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

class addscreenviewmodel(
    private val addbususecase: addbususecase,
    private val addstationusecase: addstationusecase,
    private val addarriveusecase: addarriveusecase,
    private val getstationsusecase: getstationsusecase,
    private val getarrivereusecase: getarrivereusecase,
    private val getarrivesortusecase: getarrivesortusecase
): ViewModel() {

    var stations by mutableStateOf<List<stationDTO>>(emptyList())
        private set

    var arrivals by mutableStateOf<List<arriveDTO>>(emptyList())
        private set

    suspend fun loadStations() {
        stations = getstationsusecase()
    }

    suspend fun loadArrivals(stationId: Int) {
        arrivals = getarrivereusecase(stationId)
    }

    suspend fun loadSortedArrivals(stationId: Int, destinationId: Int) {
        arrivals = getarrivesortusecase(stationId, destinationId)
    }

    suspend fun addBus(busname: String, startstation: Int, endstation: Int) {
        addbususecase(busname, startstation, endstation)
    }

    suspend fun addStation(stationname: String) {
        addstationusecase(stationname)
        loadStations()
    }

    suspend fun addArrive(busId: Int, stationId: Int, load: Int, arriveTime: kotlinx.datetime.LocalDateTime) {
        addarriveusecase(busId, stationId, load, arriveTime)
        loadArrivals(stationId)
    }

}