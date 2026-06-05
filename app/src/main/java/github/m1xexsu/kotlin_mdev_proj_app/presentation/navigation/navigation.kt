package github.m1xexsu.kotlin_mdev_proj_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import github.m1xexsu.kotlin_mdev_proj_app.data.remote.KtorClient
import github.m1xexsu.kotlin_mdev_proj_app.data.remote.TokenManager
import github.m1xexsu.kotlin_mdev_proj_app.data.repository.AdminRepositoryImpl
import github.m1xexsu.kotlin_mdev_proj_app.data.repository.ArriveRepositoryImpl
import github.m1xexsu.kotlin_mdev_proj_app.data.repository.AuthRepositoryImpl
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addarriveusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addbususecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.addstationusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getarrivereusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getarrivesortusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.getstationsusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.loginusecase
import github.m1xexsu.kotlin_mdev_proj_app.domain.usecase.logoutusecase
import github.m1xexsu.kotlin_mdev_proj_app.presentation.screen.MainScreen
import github.m1xexsu.kotlin_mdev_proj_app.presentation.screen.SettingsScreen
import github.m1xexsu.kotlin_mdev_proj_app.presentation.screen.addScreen
import github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel.addscreenviewmodel
import github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel.mainscreenviewmodel
import github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel.settingsscreenviewmodel

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object AddScreen : Screen("add_screen/{id}") {
        fun createRoute(id: Int) = "add_screen/$id"
    }
    data object SettingsScreen : Screen("settings_screen")
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    val tokenManager = remember(context) { TokenManager(context) }

    LaunchedEffect(tokenManager) {
        tokenManager.accessTokenFlow.collect { KtorClient.accessToken = it }
    }

    val arriveRepository = remember { ArriveRepositoryImpl() }
    val adminRepository = remember { AdminRepositoryImpl() }
    val authRepository = remember { AuthRepositoryImpl(tokenManager) }

    val getStationsUseCase = remember { getstationsusecase(arriveRepository) }
    val getArriveUseCase = remember { getarrivereusecase(arriveRepository) }
    val getArriveSortUseCase = remember { getarrivesortusecase(arriveRepository) }
    val addBusUseCase = remember { addbususecase(adminRepository) }
    val addStationUseCase = remember { addstationusecase(adminRepository) }
    val addArriveUseCase = remember { addarriveusecase(adminRepository) }
    val loginUseCase = remember { loginusecase(authRepository) }
    val logoutUseCase = remember { logoutusecase(authRepository) }

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(Screen.MainScreen.route) {
            val mainscreenviewmodel = remember {
                mainscreenviewmodel(
                    getstationsusecase = getStationsUseCase,
                    getarrivereusecase = getArriveUseCase,
                    getarrivesortusecase = getArriveSortUseCase
                )
            }
            MainScreen(mainscreenviewmodel)
        }

        composable(Screen.AddScreen.route) {
            val addscreenviewmodel = remember {
                addscreenviewmodel(
                    addbususecase = addBusUseCase,
                    addstationusecase = addStationUseCase,
                    addarriveusecase = addArriveUseCase,
                    getstationsusecase = getStationsUseCase,
                    getarrivereusecase = getArriveUseCase,
                    getarrivesortusecase = getArriveSortUseCase
                )
            }
            addScreen(addscreenviewmodel)
        }

        composable(Screen.SettingsScreen.route) {
            val settingsscreenviewmodel = remember {
                settingsscreenviewmodel(
                    loginusecase = loginUseCase,
                    logoutusecase = logoutUseCase
                )
            }
            SettingsScreen(settingsscreenviewmodel)
        }
    }
}