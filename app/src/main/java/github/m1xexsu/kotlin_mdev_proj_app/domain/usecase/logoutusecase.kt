package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.authrepository

class logoutusecase(private val repository: authrepository) {
    suspend operator fun invoke(){
        repository.logout()
    }
}