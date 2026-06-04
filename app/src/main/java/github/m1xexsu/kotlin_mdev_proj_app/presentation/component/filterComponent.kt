package github.m1xexsu.kotlin_mdev_proj_app.presentation.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterComponent(

) {
    var _expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(2) }
    val options = listOf("Низкая", "Средняя", "Все")

    var searchQuery by remember { mutableStateOf("") }
    val allPoints = listOf("Университет", "Воробьёвы Горы") //TODO: ИЗМЕНИТЬ НА ВНЕШНИЕ ЛИСТЫ

    val filteredPoints = remember(searchQuery) {
        allPoints.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(36.dp),
        modifier = Modifier
            .systemBarsPadding()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
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
                expanded = _expanded,
                onExpandedChange = { _expanded = it },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { 
                        searchQuery = it
                        _expanded = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    label = { Text("Поиск точки") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = _expanded) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    singleLine = true
                )

                if (filteredPoints.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = _expanded,
                        onDismissRequest = { _expanded = false }
                    ) {
                        filteredPoints.forEach { point ->
                            DropdownMenuItem(
                                text = { Text(point) },
                                onClick = {
                                    searchQuery = point
                                    _expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewFilterComponent() {
    FilterComponent()
}
