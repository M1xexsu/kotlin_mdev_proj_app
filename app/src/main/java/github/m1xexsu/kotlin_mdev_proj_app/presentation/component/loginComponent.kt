package github.m1xexsu.kotlin_mdev_proj_app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginComponent(
	username: String,
	password: String,
	onUsernameChange: (String) -> Unit,
	onPasswordChange: (String) -> Unit,
	onLoginClick: () -> Unit,
	buttonText: String = "Войти",
	modifier: Modifier = Modifier
) {
	Card(
		modifier = modifier.fillMaxWidth(),
		colors = CardDefaults.cardColors()
	) {
		Column(
			modifier = Modifier.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			OutlinedTextField(
				value = username,
				onValueChange = onUsernameChange,
				modifier = Modifier.fillMaxWidth(),
				label = { Text("Логин") },
				singleLine = true
			)
			OutlinedTextField(
				value = password,
				onValueChange = onPasswordChange,
				modifier = Modifier.fillMaxWidth(),
				label = { Text("Пароль") },
				singleLine = true,
				visualTransformation = PasswordVisualTransformation()
			)
			Button(
				onClick = onLoginClick,
				modifier = Modifier.fillMaxWidth()
			) {
				Text(buttonText)
			}
		}
	}
}
