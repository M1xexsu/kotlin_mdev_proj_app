package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.adminrepository
import kotlinx.datetime.LocalDateTime

class addarriveusecase(private val repository: adminrepository) {
    suspend operator fun invoke(bus_id: Int, station_id: Int, load: Int, arrive_time: LocalDateTime){
        repository.addarrive(bus_id, station_id, load, arrive_time)
    }
}