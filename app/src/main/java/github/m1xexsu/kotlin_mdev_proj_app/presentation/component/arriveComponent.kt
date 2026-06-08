package github.m1xexsu.kotlin_mdev_proj_app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime

@Composable
fun ArriveComponent(
    busname: String = "",
    startstationname: String = "",
    endstationname: String = "",
    load: Int = 0,
    arrivetime: MutableState<Int>,
    starttime: LocalDateTime,
    endtime: LocalDateTime,
    filterscore: MutableState<Int>
) {
    val expanded = remember { mutableStateOf(false) }
    if (load <= filterscore.value) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable(onClick = {expanded.value = !expanded.value})
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = busname,
                            style = MaterialTheme.typography.titleLarge,
                            color = (if (load < 70) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
                        )
                        Text(
                            text = "$startstationname - $endstationname",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = (if (load < 70) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error).copy(
                                alpha = 0.7f
                            )
                        )
                    }
                    Text(
                        text = "${arrivetime.value} МИН",
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                if (expanded.value) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = String.format("%02d:%02d", starttime.hour, starttime.minute),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = String.format("%02d:%02d", endtime.hour, endtime.minute),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // В прогрессбаре пока загруженность
                LinearProgressIndicator(
                    progress = { load / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    strokeCap = StrokeCap.Round
                )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewArriveComponent() {
    ArriveComponent(
        busname = "110",
        startstationname = "Комсомольская",
        endstationname = "Пролетарская",
        load = 70,
        arrivetime = remember { mutableIntStateOf(12) },
        starttime = LocalDateTime(2023, 12, 12, 12, 5),
        endtime = LocalDateTime(2023, 12, 12, 13, 30),
        filterscore = remember { mutableStateOf(100) }
    )
}
