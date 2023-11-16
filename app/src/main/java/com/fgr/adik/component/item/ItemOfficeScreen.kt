package com.fgr.adik.component.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.component.z9_others.LoadImageUrl
import com.fgr.adik.component_core.item.BaseItem

@Composable
fun ItemOfficeScreen(
    officeImageUrl: String = "",
    officeName: String = "",
    access: String = "",
    division: String = "",
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
                    .size(64.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        shape = RoundedCornerShape(4.dp),
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline
                    )
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
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = stringResource(id = R.string.access, access),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(id = R.string.division, division),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}