package com.dxn.wallpaperx.ui.activities.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    leadingIcon: @Composable () -> Unit,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth(1f),
            textStyle = MaterialTheme.typography.body1,
            leadingIcon = leadingIcon,
            trailingIcon = {
                IconButton(onClick = {
                    query = ""
                    focusManager.clearFocus()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.onPrimary.copy(0.5f)
                    )
                }
            },
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                cursorColor = MaterialTheme.colors.onPrimary,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colors.onPrimary.copy(0.7f),
                unfocusedIndicatorColor = MaterialTheme.colors.onPrimary.copy(0.6f),
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(query)
                focusManager.clearFocus()
            })
        )
    }
}