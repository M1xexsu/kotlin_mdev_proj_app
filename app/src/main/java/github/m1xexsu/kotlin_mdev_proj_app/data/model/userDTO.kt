package github.mixexsu.application.dtos

import kotlinx.serialization.Serializable

@Serializable
data class userDTO(
    val token: String,
    val message: String,
    val userId: Int
    )