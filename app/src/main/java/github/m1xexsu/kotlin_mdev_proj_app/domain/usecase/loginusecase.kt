package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.authrepository

class loginusecase(private val repository: authrepository) {
    suspend operator fun invoke(username: String, password: String){
        repository.login(username, password)
    }
}