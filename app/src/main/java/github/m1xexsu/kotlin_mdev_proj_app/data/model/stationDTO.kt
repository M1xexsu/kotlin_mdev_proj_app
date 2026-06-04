package github.m1xexsu.kotlin_mdev_proj_app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class stationDTO(
    val station_id: Int,
    val station_name: String
) {
}