package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.adminrepository

class addstationusecase(private val repository: adminrepository) {
    suspend operator fun invoke(stationname: String){
        repository.addstation(stationname)
    }
}