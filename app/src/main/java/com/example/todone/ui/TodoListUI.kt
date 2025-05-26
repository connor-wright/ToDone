package com.example.todone.ui

import androidx.compose.foundation.background
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
import com.example.todone.model.TodoListEntity
import androidx.compose.foundation.combinedClickable
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

@Composable
fun TodoListsScreen(modifier: Modifier = Modifier, viewModel: TodoListViewModel = hiltViewModel()) {
    val lists by viewModel.rootLists.collectAsState()
    var newListTitle by remember { mutableStateOf("") }
    val shouldShowBottomBar by viewModel.shouldShowBottomBar.observeAsState(initial = false)
    var showEditDialog by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .combinedClickable(
                onClick = { viewModel.onSelectedNodeChanged(null) },
                onLongClick = {} // No-op for long click on background
            )
    ) {
        Column(modifier = Modifier.padding(Dimens.ScreenPadding)) {
            OutlinedTextField(
                value = newListTitle,
                onValueChange = { newListTitle = it },
                label = { Text(stringResource(id = R.string.new_list_title)) }
            )
            Button(onClick = {
                if (newListTitle.isNotBlank()) {
                    viewModel.addList(newListTitle)
                    newListTitle = ""
                }
            }, modifier = Modifier.padding(top = Dimens.ListTopPadding)) {
                Text(stringResource(id = R.string.add_list))
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

@Composable
fun TodoNode(
    list: TodoListEntity,
    viewModel: TodoListViewModel,
    onLongPress: (() -> Unit)? = null,
    isSelected: Boolean = false
) {
    var newSubListTitle by remember { mutableStateOf("") }
    val subLists by viewModel.getSubLists(list.id).collectAsState(initial = emptyList())
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { onLongPress?.invoke() }
                )
                .then(if (isSelected) Modifier.background(androidx.compose.ui.graphics.Color.LightGray) else Modifier)
        ) {
            Text(
                text = list.title,
                modifier = Modifier.weight(1f)
            )
        }
        // Add sub-list
        Row {
            OutlinedTextField(
                value = newSubListTitle,
                onValueChange = { newSubListTitle = it },
                label = { Text("New sub-list") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newSubListTitle.isNotBlank()) {
                    viewModel.addList(newSubListTitle, parentId = list.id)
                    newSubListTitle = ""
                }
            }, modifier = Modifier.padding(start = Dimens.ListTopPadding)) {
                Text("Add List")
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
