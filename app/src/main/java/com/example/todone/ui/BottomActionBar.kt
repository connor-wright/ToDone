package com.example.todone.ui

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width

@Composable
fun BottomActionBar(
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    BottomAppBar {
        Button(onClick = onDelete) { Text("Delete") }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = onEdit) { Text("Edit") }
    }
}

