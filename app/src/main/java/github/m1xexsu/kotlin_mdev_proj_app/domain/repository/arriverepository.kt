package github.m1xexsu.kotlin_mdev_proj_app.domain.repository

import github.m1xexsu.kotlin_mdev_proj_app.data.model.arriveDTO
import github.m1xexsu.kotlin_mdev_proj_app.data.model.stationDTO

interface arriverepository {
    suspend fun getstations(): List<stationDTO>
    suspend fun getarrive(id: Int): List<arriveDTO>
    suspend fun getarrivesort(id: Int, dest: Int): List<arriveDTO>
}