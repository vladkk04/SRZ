package com.electro.fish.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.electro.fish.ui.theme.Dimens

@Composable
fun <T> RadioButtonSingleSelection(
    items: List<T>,
    selectedItem: T?,
    onSelectionChanged: (T) -> Unit,
    itemLabel: (T) -> String,
    modifier: Modifier = Modifier
) {
    Column(modifier.selectableGroup()) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (item == selectedItem),
                        onClick = { onSelectionChanged(item) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = Dimens.LargePadding),
            ) {
                RadioButton(selected = (item == selectedItem), onClick = null)
                Text(
                    text = itemLabel(item),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = Dimens.LargePadding)
                )
            }
        }
    }
}
