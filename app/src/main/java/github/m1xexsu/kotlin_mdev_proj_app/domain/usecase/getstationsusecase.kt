package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.data.model.stationDTO
import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.arriverepository

class getstationsusecase(private val repository: arriverepository){
    suspend operator fun invoke(): List<stationDTO>{
        return repository.getstations()
    }
}