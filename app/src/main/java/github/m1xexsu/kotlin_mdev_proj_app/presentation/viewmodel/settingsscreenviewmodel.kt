package github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.loginusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.logoutusecase

class settingsscreenviewmodel(
    private val loginusecase: loginusecase,
    private val logoutusecase: logoutusecase
): ViewModel() {

    var isLoggedIn by mutableStateOf(false)
        private set

    var statusText by mutableStateOf("Не выполнен вход")
        private set

    suspend fun login(username: String, password: String) {
        loginusecase(username, password)
        isLoggedIn = true
        statusText = "Вход выполнен"
    }

    suspend fun logout() {
        logoutusecase()
        isLoggedIn = false
        statusText = "Выход выполнен"
    }

}