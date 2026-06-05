package github.m1xexsu.kotlin_mdev_proj_app.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import github.m1xexsu.kotlin_mdev_proj_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    points: List<Pair<Int, String>>,
    onPointSelected: (Int) -> Unit,
    onFilterClick: () -> Unit,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var anchorExpanded by remember { mutableStateOf(isExpanded) }
    val filteredPoints = remember(query, points) {
        points.filter { it.second.contains(query, ignoreCase = true) }
    }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                onQueryChange(it)
                onExpandedChange(true)
                anchorExpanded = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Поиск маршрута...") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_filter_alt_24),
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(onClick = onFilterClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_filter_list_24),
                        contentDescription = "Фильтр"
                    )
                }
            },
            shape = RoundedCornerShape(16.dp),
            singleLine = true
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
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewSearchScreenStructure() {
    val points = listOf(1 to "Библиотека", 2 to "Столовая")

    Column {
        SearchComponent(
            query = "",
            onQueryChange = {},
            points = points,
            onPointSelected = {},
            onFilterClick = {},
            isExpanded = false,
            onExpandedChange = {}
        )
    }
}
