package com.fgr.adik.component.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.component.z9_others.LoadImageUrl
import com.fgr.adik.component_core.item.BaseItem
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun ItemHomeScreen(
    officeImageUrl: String = "",
    officeName: String = "",
    entryTime: String = "",
    outTime: String = "",
    status: String = "",
    onContentClicked: () -> Unit = {},
) {
    BaseItem(
        onContentClicked = onContentClicked,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        shape = RoundedCornerShape(4.dp),
                        width = 1.dp,
                        color = colorScheme.outline
                    )
                    .padding(12.dp)
            ) {
                LoadImageUrl(
                    url = officeImageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = officeName,
                    style = typography.titleSmall,
                    color = colorScheme.onSurfaceVariant
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.entry_hours),
                        style = typography.bodySmall,
                        color = colorScheme.onSurface
                    )
                    Text(
                        text = entryTime,
                        style = typography.labelMedium,
                        color = colorScheme.onSurface
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.out_hours),
                        style = typography.bodySmall,
                        color = colorScheme.onSurface
                    )
                    Text(
                        text = outTime,
                        style = typography.labelMedium,
                        color = colorScheme.onSurface
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.statue),
                        style = typography.bodySmall,
                        color = colorScheme.onSurface
                    )
                    Text(
                        text = status,
                        style = typography.labelMedium,
                        color = colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemHomeScreenPreview() {
    ADIKTheme {
        val url =
            "https://online.visual-paradigm.com/repository/images/24c5981d-4a75-4080-b617-d77a65aa4a1f/logos-design/furniture-logo-designed-for-interior-design-company.png"
        ItemHomeScreen(
            officeImageUrl = url,
            officeName = "PT. Kursi Terbang Perawan",
            entryTime = "08:00 - 09:00",
            outTime = "16:00 - 17:00",
            status = "Absen masuk dibuka",
        )
    }
}