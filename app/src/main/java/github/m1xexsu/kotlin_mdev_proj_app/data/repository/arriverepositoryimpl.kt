package github.m1xexsu.kotlin_mdev_proj_app.data.repository

import github.m1xexsu.kotlin_mdev_proj_app.data.model.arriveDTO
import github.m1xexsu.kotlin_mdev_proj_app.data.model.stationDTO
import github.m1xexsu.kotlin_mdev_proj_app.data.remote.KtorClient
import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.arriverepository

class ArriveRepositoryImpl : arriverepository {
    override suspend fun getstations(): List<stationDTO> {
        return KtorClient.getstations().getOrThrow()
    }

    override suspend fun getarrive(id: Int): List<arriveDTO> {
        return KtorClient.getarrive(id).getOrThrow()
    }

    override suspend fun getarrivesort(id: Int, dest: Int): List<arriveDTO> {
        return KtorClient.getarrivesort(id, dest).getOrThrow()
    }
}