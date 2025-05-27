package com.example.todone.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.todone.ui.theme.aliasTokens.AlternateThemeAliasTokens
import com.example.todone.ui.theme.aliasTokens.DefaultFluentAliasTokens
import com.microsoft.fluentui.theme.FluentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComposable(modifier: Modifier = Modifier, title: String) {
    var expanded by remember { mutableStateOf(false) }
    val themes = listOf("Theme1", "Theme2")
    var selectedTheme by remember { mutableStateOf(themes[0]) }

    TopAppBar(
        modifier = modifier,
        title = { Text(title) },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Theme Menu")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                themes.forEach { theme ->
                    DropdownMenuItem(
                        text = { Text(theme) },
                        onClick = {
                            selectedTheme = theme
                            expanded = false
                            onThemeSelected(theme)
                        }
                    )
                }
            }
        }
    )
}

fun onThemeSelected(theme: String) {
    // Handle theme selection logic here
    // This could involve updating a ViewModel or applying a theme change
    when (theme) {
        "Theme1" -> {
            FluentTheme.updateAliasTokens(DefaultFluentAliasTokens())
        }
        "Theme2" -> {
            FluentTheme.updateAliasTokens(AlternateThemeAliasTokens())
        }
        else -> {
            // Default or fallback theme
            FluentTheme.updateAliasTokens(AlternateThemeAliasTokens())
        }
    }
}

