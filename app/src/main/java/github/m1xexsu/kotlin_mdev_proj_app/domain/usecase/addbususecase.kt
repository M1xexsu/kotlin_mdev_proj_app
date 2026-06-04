package github.m1xexsu.kotlin_mdev_proj_app.domain.usecase

import github.m1xexsu.kotlin_mdev_proj_app.domain.repository.adminrepository

class addbususecase(private val repository: adminrepository) {
    suspend operator fun invoke(busname: String, startstation: Int, endstation: Int){
        repository.addbus(busname, startstation, endstation)
    }
}