package com.example.spendwise

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val PREFS_NAME = "MyPrefsFile"
const val BUDGET_KEY = "budget"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settingsScreen() {
    var totalBudget by rememberSaveable { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var budgetInputText by remember { mutableStateOf("") }

    val context = LocalContext.current
    DisposableEffect(context) {
        val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        totalBudget = sharedPrefs.getInt(BUDGET_KEY, 0)
        onDispose { }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Current Budget",
            fontSize = 25.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "₹" + totalBudget.toString(),
                fontSize = 50.sp,
                fontWeight = Bold
            )
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        showDialog = true
                    },
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit"
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            modifier = Modifier.fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Developed by Sushant Kumar 😁")
        }

        if (showDialog) {
            BudgetInputDialog(
                onDismiss = { showDialog = false },
                onConfirm = { newBudget ->
                    totalBudget = newBudget.toIntOrNull() ?: totalBudget
                    showDialog = false
                    val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    with(sharedPrefs.edit()) {
                        putInt(BUDGET_KEY, totalBudget)
                        apply()
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetInputDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var budgetInputText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Enter budget") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = budgetInputText,
                    onValueChange = { budgetInputText = it },
                    label = { Text("Enter Budget") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(budgetInputText)
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SettingsScreenPreview() {
    settingsScreen()
}
