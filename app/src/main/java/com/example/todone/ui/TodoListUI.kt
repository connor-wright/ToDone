package com.example.todone.ui

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



@Composable
fun TodoListsScreen(modifier: Modifier = Modifier, viewModel: TodoListViewModel = hiltViewModel()) {
    val lists by viewModel.lists.collectAsState()
    var newListTitle by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(Dimens.ScreenPadding)) {
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
                Text(text = list.title)
                Spacer(modifier = Modifier.height(Dimens.ListItemSpacing))
            }
        }
    }
}

/*
@Composable
fun TodoListView(list: TodoList, onListUpdate: (TodoList) -> Unit) {
    var newItemTitle by remember { mutableStateOf("") }
    Column {
        Text(text = list.title)
        Row {
            OutlinedTextField(
                value = newItemTitle,
                onValueChange = { newItemTitle = it },
                label = { Text(stringResource(id = R.string.new_sublist_item)) },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newItemTitle.isNotBlank()) {
                    val updatedList = list.copy(items = list.items + TodoItem(newItemTitle))
                    onListUpdate(updatedList)
                    newItemTitle = ""
                }
            }, modifier = Modifier.padding(start = Dimens.ListTopPadding)) {
                Text(stringResource(id = R.string.add))
            }
        }
        Column(modifier = Modifier.padding(start = Dimens.SublistStartPadding, top = Dimens.SublistTopPadding)) {
            list.items.forEach { item ->
                Text(text = "- ${item.title}")
            }
        }
    }
}*/


