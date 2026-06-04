package github.m1xexsu.kotlin_mdev_proj_app.domain.repository

import kotlinx.datetime.LocalDateTime

interface adminrepository {
    suspend fun addbus(busname: String, startstation: Int, endstation: Int)
    suspend fun addstation(stationname: String)
    suspend fun addarrive(bus_id: Int, station_id: Int, load: Int, arrive_time: LocalDateTime)
}