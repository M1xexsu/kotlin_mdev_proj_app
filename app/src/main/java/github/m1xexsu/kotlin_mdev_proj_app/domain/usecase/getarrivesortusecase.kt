package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.data.model.arriveDTO
import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.arriverepository

class getarrivesortusecase(private val repository: arriverepository) {
    suspend operator fun invoke(id: Int, dest: Int): List<arriveDTO>{
        return repository.getarrivesort(id, dest)
    }
}