package github.m1xexsu.kotlin_mdev_proj_app.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import github.m1xexsu.kotlin_mdev_proj_app.R
import github.m1xexsu.kotlin_mdev_proj_app.ui.theme.MyApplicationTheme


//Топбар для главного экрана приложения.
//При нажатии на текст станет полем поиска.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarComponent(
    modifier: Modifier = Modifier,
    triggerfilter: () -> Unit = {},
    query: String,
    onQueryChange: (String) -> Unit,
    points: List<Pair<Int, String>>,
    onPointSelected: (Int) -> Unit,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
)
{
    //Лень думать
    val _state = remember { mutableStateOf(false) }
    var anchorExpanded by remember { mutableStateOf(isExpanded) }
    val filteredPoints = remember(query, points) {
        points.filter { it.second.contains(query, ignoreCase = true) }
    }

    CenterAlignedTopAppBar(
        title = {
            BasicTextField(
                value = query,
                onValueChange = {
                    onQueryChange(it)
                    onExpandedChange(true)
                    anchorExpanded = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true,
                textStyle = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center),
            )

            DropdownMenu(
                expanded = isExpanded && anchorExpanded && filteredPoints.isNotEmpty(),
                onDismissRequest = {
                    onExpandedChange(false)
                    anchorExpanded = false
                }
            ) {
                filteredPoints.forEach { point ->
                    DropdownMenuItem(
                        text = { Text(point.second) },
                        onClick = {
                            onQueryChange(point.second)
                            onPointSelected(point.first)
                            onExpandedChange(false)
                            anchorExpanded = false
                        }
                    )
                }
            }
        },
        actions = {
        IconButton(
            onClick = {
                triggerfilter()
                _state.value = !_state.value
            }
        ) {
            Icon(
                painter = painterResource(id = (if (_state.value) R.drawable.outline_filter_list_off_24 else R.drawable.outline_filter_list_24)),
                contentDescription = "menu",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        }
        ,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
            .graphicsLayer {
                shape = RoundedCornerShape(
                    36.dp
                )
                clip = true
            }
    )
}

@Composable
@Preview(showSystemUi = true)
fun PreviewTopbarComponent() {
    MyApplicationTheme {
        TopbarComponent(
            modifier = Modifier.systemBarsPadding().padding(24.dp),
            query = "",
            onQueryChange = {},
            points = listOf(1 to "Москва", 2 to "Питер"),
            onPointSelected = {},
            isExpanded = false,
            onExpandedChange = {},
        )
    }
}