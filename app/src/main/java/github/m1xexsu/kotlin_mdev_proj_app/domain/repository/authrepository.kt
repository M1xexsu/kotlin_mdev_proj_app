package github.m1xexsu.kotlin_mdev_proj_app.domain.repository

interface authrepository {
    suspend fun login(username: String, password: String)
    suspend fun logout()
}