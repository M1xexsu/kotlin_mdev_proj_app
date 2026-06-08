package github.m1xexsu.kotlin_mdev_proj_app.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterComponent(
    isVisible: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    points: List<Pair<Int, String>>,
    onPointSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    settingsjump: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(2) }
    val options = listOf("Низкая", "Средняя", "Все")

    var isMenuExpanded by remember { mutableStateOf(false) }

    val filteredPoints = remember(searchQuery, points) {
        points.filter { it.second.contains(searchQuery, ignoreCase = true) }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(36.dp),
            modifier = modifier
                .systemBarsPadding()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
            ) {
                Text(
                    text = "Загруженность",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                )

                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = options.size
                            ),
                            onClick = { selectedIndex = index },
                            selected = index == selectedIndex,
                            label = {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = "Конечная точка",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = isMenuExpanded,
                    onExpandedChange = { isMenuExpanded = it },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = {
                            onSearchQueryChange(it)
                            isMenuExpanded = true
                        },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        label = { Text("Поиск точки") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded) },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                        singleLine = true
                    )

                    if (filteredPoints.isNotEmpty()) {
                        ExposedDropdownMenu(
                            expanded = isMenuExpanded,
                            onDismissRequest = { isMenuExpanded = false }
                        ) {
                            filteredPoints.forEach { point ->
                                DropdownMenuItem(
                                    text = { Text(point.second) },
                                    onClick = {
                                        onSearchQueryChange(point.second)
                                        onPointSelected(point.first)
                                        isMenuExpanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier.fillMaxWidth()
                ){
                    IconButton(
                        onClick = { settingsjump() },
                        modifier = Modifier.padding(top = 8.dp).align(androidx.compose.ui.Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = github.m1xexsu.kotlin_mdev_proj_app.R.drawable.baseline_settings_24),
                            contentDescription = "Настройки",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewFilterComponent() {
    FilterComponent(
        isVisible = true, // В превью ставим true для отображения
        searchQuery = "",
        onSearchQueryChange = {},
        points = listOf(
            Pair(1, "Библиотека"),
            Pair(2, "Столовая"),
            Pair(3, "Корпус А")
        ),
        onPointSelected = {},
        settingsjump = {}
    )
}
