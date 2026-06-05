package github.m1xexsu.kotlin_mdev_proj_app.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.m1xexsu.kotlin_mdev_proj_app.presentation.component.LoginComponent
import github.m1xexsu.kotlin_mdev_proj_app.presentation.viewmodel.settingsscreenviewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: settingsscreenviewmodel) {
	val coroutineScope = rememberCoroutineScope()
	var username by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	LaunchedEffect(viewModel.isLoggedIn) {
		// пока просто оставляем состояние как есть
	}

	Scaffold { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			Text(
				text = if (viewModel.isLoggedIn) "Пользователь вошёл" else "Вход в систему",
				style = MaterialTheme.typography.titleLarge
			)

			LoginComponent(
				username = username,
				password = password,
				onUsernameChange = { username = it },
				onPasswordChange = { password = it },
				onLoginClick = {
					coroutineScope.launch { viewModel.login(username, password) }
				},
				buttonText = "Войти",
				modifier = Modifier.fillMaxWidth()
			)

			Button(
				onClick = { coroutineScope.launch { viewModel.logout() } },
				modifier = Modifier.fillMaxWidth()
			) {
				Text("Выйти")
			}

			Text(text = viewModel.statusText, style = MaterialTheme.typography.bodyMedium)
		}
	}

}