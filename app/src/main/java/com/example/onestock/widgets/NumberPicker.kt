package com.example.onestock.widgets

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment


@Composable
fun NumberPicker(
    selectedNumber: MutableState<String>,

    numberRange: IntRange = 1..10,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val numbers = numberRange.map { it.toString() }

    Box(modifier = modifier.wrapContentWidth(Alignment.Start)) {
        TextField(
            value = selectedNumber.value,
            onValueChange = { newValue ->
                selectedNumber.value = newValue.filter { it.isDigit() }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.ArrowDropDown, "Expand")
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            numbers.forEach { number ->
                DropdownMenuItem(onClick = {
                    selectedNumber.value = number
                    expanded = false
                }) {
                    Text(text = number)
                }
            }
        }
    }
}