package github.m1xexsu.kotlin_mdev_proj_app.data.repository

import github.mixexsu.application.dtos.userDTO
import github.m1xexsu.kotlin_mdev_proj_app.data.remote.KtorClient
import github.m1xexsu.kotlin_mdev_proj_app.data.remote.TokenManager
import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.authrepository

class AuthRepositoryImpl(
    private val tokenManager: TokenManager
) : authrepository {

    override suspend fun login(username: String, password: String) {
        val response: userDTO = KtorClient.login(username, password)
        tokenManager.saveAccessToken(response.token)
        tokenManager.saveRefreshToken("")
        KtorClient.accessToken = response.token
    }

    override suspend fun logout() {
        KtorClient.logout()
        tokenManager.clearTokens()
        KtorClient.accessToken = null
    }
}
