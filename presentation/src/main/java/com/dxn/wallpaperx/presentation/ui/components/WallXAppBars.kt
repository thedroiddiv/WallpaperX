package com.dxn.wallpaperx.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dxn.wallpaperx.presentation.ui.theme.Padding
import com.dxn.wallpaperx.presentation.ui.theme.WallpaperXTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onSubmit: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                focusManager.clearFocus()
                onNavigateBack()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(Padding.medium))
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = searchQuery,
                onValueChange = onQueryChange,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                    onSubmit(searchQuery)
                }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
            )
        }
        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallXTitleBar(title: String) {
    MediumTopAppBar(title = {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
    })
}

@Preview
@Composable
fun SearchBarPreview() {
    WallpaperXTheme {
        Surface {
            var query by remember { mutableStateOf("") }
            val focusRequester = remember { FocusRequester() }

            SearchBar(
                searchQuery = query,
                onQueryChange = { query = it },
                onSubmit = {},
                focusRequester = focusRequester
            ) {
            }

            LaunchedEffect(key1 = Unit, block = {
                focusRequester.requestFocus()
            })
        }
    }
}
