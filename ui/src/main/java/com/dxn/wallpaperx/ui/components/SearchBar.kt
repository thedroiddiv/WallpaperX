package com.dxn.wallpaperx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.onPrimary.copy(0.1f)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onPrimary),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp, horizontal = 16.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(query)
                focusManager.clearFocus()
            }),
            singleLine = true,
            maxLines = 1,
            cursorBrush = SolidColor(MaterialTheme.colors.onPrimary)
        )
        IconButton(onClick = {
            query = ""
            focusManager.clearFocus()
        }) {
            Icon(
                imageVector = Icons.Rounded.Clear,
                contentDescription = "cancel",
                tint = MaterialTheme.colors.onPrimary.copy(0.4f)
            )
        }
    }
}
