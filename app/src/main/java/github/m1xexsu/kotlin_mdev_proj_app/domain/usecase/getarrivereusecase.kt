package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.data.model.arriveDTO
import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.arriverepository

class getarrivereusecase(private val repository: arriverepository) {
    suspend operator fun invoke(id: Int): List<arriveDTO>{
        return repository.getarrive(id)
    }
}