package com.example.todone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todone.model.TodoListEntity
import com.example.todone.ui.theme.Dimens
import com.example.todone.viewModel.TodoListViewModel
import com.microsoft.fluentui.theme.FluentTheme.aliasTokens
import com.microsoft.fluentui.theme.token.FluentAliasTokens
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun TodoNode(
    list: TodoListEntity,
    viewModel: TodoListViewModel,
    onLongPress: (() -> Unit)? = null,
    isSelected: Boolean = false
) {
    var newSubListTitle by remember { mutableStateOf("") }
    val subLists by viewModel.getSubLists(list.id).collectAsState(initial = emptyList())
    val background1 = aliasTokens.brandBackgroundColor[FluentAliasTokens.BrandBackgroundColorTokens.BrandBackground1].value()
    val background2 = aliasTokens.brandBackgroundColor[FluentAliasTokens.BrandBackgroundColorTokens.BrandBackground2].value()
    val foreground1 = aliasTokens.brandForegroundColor[FluentAliasTokens.BrandForegroundColorTokens.BrandForeground1].value()
    val neutralForeground =
        aliasTokens.neutralForegroundColor[FluentAliasTokens.NeutralForegroundColorTokens.Foreground1].value()


    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { onLongPress?.invoke() }
                )
                .then(if (isSelected) Modifier.background(background2) else Modifier)
        ) {
            Text(
                text = list.title,
                modifier = Modifier.weight(1f),
                color = foreground1
            )
        }
        // Add sub-list
        Row {
            OutlinedTextField(
                value = newSubListTitle,
                onValueChange = { newSubListTitle = it },
                label = { Text("New sub-list", color = foreground1) },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = foreground1,
                    unfocusedIndicatorColor = neutralForeground,
                    focusedLabelColor = foreground1,
                    unfocusedLabelColor = neutralForeground,
                    focusedTextColor = foreground1,
                    unfocusedTextColor = neutralForeground,
                    cursorColor = foreground1),
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newSubListTitle.isNotBlank()) {
                        viewModel.addList(newSubListTitle, parentId = list.id)
                        newSubListTitle = ""
                    }
                },
                modifier = Modifier.padding(start = Dimens.ListTopPadding),
                colors = ButtonDefaults.buttonColors(containerColor = background2, contentColor = foreground1)
            ) {
                Text("Add List", color = foreground1)
            }
        }
        // Render sub-lists recursively
        if (subLists.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(
                    start = Dimens.SublistStartPadding,
                    top = Dimens.SublistTopPadding
                )
            ) {
                subLists.forEach { subList ->
                    TodoNode(
                        list = subList,
                        viewModel = viewModel,
                        onLongPress = { viewModel.onSelectedNodeChanged(subList) },
                        isSelected = isSelected && subList.id == list.id
                    )
                }
            }
        }
    }
}

