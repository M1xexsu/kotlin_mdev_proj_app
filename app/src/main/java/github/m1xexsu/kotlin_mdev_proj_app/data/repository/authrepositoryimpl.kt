package github.m1xexsu.kotlin_mdev_proj_app.data.repository

import github.mixexsu.application.dtos.userDTO
import github.m1xexsu.kotlin_mdev_proj_app.data.remote.KtorClient
import github.m1xexsu.kotlin_mdev_proj_app.data.remote.TokenManager
import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.authrepository

class AuthRepositoryImpl(
    private val tokenManager: TokenManager
) : authrepository {

    override suspend fun login(username: String, password: String) {
        val response: Result<userDTO> = KtorClient.login(username, password)

        response.onSuccess { user ->
            tokenManager.saveAccessToken(user.token)
            tokenManager.saveRefreshToken("")
            KtorClient.accessToken = user.token
        }.onFailure { error ->
            throw Exception("Login failed: ${error.message}")
        }
    }

    override suspend fun logout() {
        KtorClient.logout()

        tokenManager.clearTokens()
        KtorClient.accessToken = null
    }
}
