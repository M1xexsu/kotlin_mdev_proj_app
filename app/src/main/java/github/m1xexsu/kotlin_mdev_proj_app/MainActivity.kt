package github.m1xexsu.kotlin_mdev_proj_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import github.m1xexsu.kotlin_mdev_proj_app.presentation.navigation.Navigation
import github.m1xexsu.kotlin_mdev_proj_app.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Navigation()
            }
        }
    }
}
