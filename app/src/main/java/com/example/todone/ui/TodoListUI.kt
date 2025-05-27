package com.example.todone.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todone.R
import com.example.todone.ui.theme.Dimens
import com.example.todone.viewModel.TodoListViewModel
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.example.todone.ui.theme.ToDoneTheme
import com.microsoft.fluentui.theme.FluentTheme.aliasTokens
import com.microsoft.fluentui.theme.token.FluentAliasTokens
import androidx.compose.foundation.background
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldColors

@Composable
fun TodoListsScreen(modifier: Modifier = Modifier, viewModel: TodoListViewModel = hiltViewModel()) {
    val lists by viewModel.rootLists.collectAsState()
    var newListTitle by remember { mutableStateOf("") }
    val shouldShowBottomBar by viewModel.shouldShowBottomBar.observeAsState(initial = false)
    var showEditDialog by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf("") }
    val background1 =
        aliasTokens.brandBackgroundColor[FluentAliasTokens.BrandBackgroundColorTokens.BrandBackground1].value()
    val foreground1 =
        aliasTokens.brandForegroundColor[FluentAliasTokens.BrandForegroundColorTokens.BrandForeground1].value()
    val background2 =
        aliasTokens.brandBackgroundColor[FluentAliasTokens.BrandBackgroundColorTokens.BrandBackground2].value()
    val neutralBackground =
        aliasTokens.neutralBackgroundColor[FluentAliasTokens.NeutralBackgroundColorTokens.Background1].value()
    val neutralForeground =
        aliasTokens.neutralForegroundColor[FluentAliasTokens.NeutralForegroundColorTokens.Foreground1].value()

    ToDoneTheme {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable(onClick = { viewModel.onSelectedNodeChanged(null) })
                .background(background1)
        ) {
            Column(modifier = Modifier.padding(Dimens.ScreenPadding)) {
                TopBarComposable(
                    title = stringResource(id = R.string.app_name),
                    modifier = Modifier.background(background2)
                )
                OutlinedTextField(
                    value = newListTitle,
                    onValueChange = { newListTitle = it },
                    label = {
                        Text(
                            stringResource(id = R.string.new_list_title),
                            color = foreground1
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = foreground1,
                        unfocusedIndicatorColor = neutralForeground,
                        focusedLabelColor = foreground1,
                        unfocusedLabelColor = neutralForeground,
                        focusedTextColor = foreground1,
                        unfocusedTextColor = neutralForeground,
                        cursorColor = foreground1

                    )
                )
                Button(
                    onClick = {
                        if (newListTitle.isNotBlank()) {
                            viewModel.addList(newListTitle)
                            newListTitle = ""
                        }
                    },
                    modifier = Modifier.padding(top = Dimens.ListTopPadding),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = background2,
                        contentColor = foreground1
                    )
                ) {
                    Text(stringResource(id = R.string.add_list), color = foreground1)
                }
                Spacer(modifier = Modifier.height(Dimens.ScreenPadding))
                LazyColumn {
                    items(lists) { list ->
                        TodoNode(
                            list = list,
                            viewModel = viewModel,
                            onLongPress = {
                                viewModel.onSelectedNodeChanged(list)
                            }
                        )
                        Spacer(modifier = Modifier.height(Dimens.ListItemSpacing))
                    }
                }
            }
            // Bottom bar for actions
            if (shouldShowBottomBar) {
                Surface(modifier = Modifier.align(Alignment.BottomCenter)) {
                    BottomActionBar(
                        onDelete = {
                            viewModel.deleteList()
                        },
                        onEdit = {
                            showEditDialog = true
                            editText = viewModel.getSelectedTitle()
                        }
                    )
                }
            }
            // Edit dialog
            if (showEditDialog) {
                EditAlertDialog(
                    value = editText,
                    onValueChange = { editText = it },
                    onDismiss = { showEditDialog = false },
                    onSave = {
                        viewModel.editList(editText)
                        showEditDialog = false
                    }
                )
            }
        }
    }
}

