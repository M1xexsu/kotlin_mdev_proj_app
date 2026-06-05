package github.m1xexsu.kotlin_mdev_proj_app.data.repository

import github.m1xexsu.kotlin_mdev_proj_app.data.remote.KtorClient
import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.adminrepository
import kotlinx.datetime.LocalDateTime

class AdminRepositoryImpl : adminrepository {
    override suspend fun addbus(busname: String, startstation: Int, endstation: Int) {
        KtorClient.addbus(busname, startstation, endstation)
    }

    override suspend fun addstation(stationname: String) {
        KtorClient.addstation(stationname)
    }

    override suspend fun addarrive(bus_id: Int, station_id: Int, load: Int, arrive_time: LocalDateTime) {
        KtorClient.addarrive(bus_id, station_id, load, arrive_time)
    }
}
