package github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addarriveusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addbususecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addstationusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getarrivereusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getarrivesortusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getstationsusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.loginusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.logoutusecase

class busappviewmodel(
    private val loginusecase: loginusecase,
    private val logoutusecase: logoutusecase,
    private val addbususecase: addbususecase,
    private val addstationusecase: addstationusecase,
    private val addarriveusecase: addarriveusecase,
    private val getstationsusecase: getstationsusecase,
    private val getarrivereusecase: getarrivereusecase,
    private val getarrivesortusecase: getarrivesortusecase
): ViewModel() {

}