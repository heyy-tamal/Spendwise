package com.example.spendwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.ui.theme.SpendWiseTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            SpendWiseTheme {
                AppContent(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        modifier = Modifier
            .height(60.dp)
    ) {
        NavigationBarItem(
            selected = navController.currentDestination?.route == "HomeScreen",
            onClick = {
                navController.navigate("HomeScreen")
            },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route == "TransactionScreen",
            onClick = {
                navController.navigate("TransactionScreen")
            },
            icon = {
                Icon(imageVector = Icons.Default.List, contentDescription = "Transactions")
            }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == "SettingsPage",
            onClick = {
                navController.navigate("SettingsPage")
            },
            icon = {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        floatingActionButton = {
            var showDialog by remember { mutableStateOf(false) }
            var name by remember { mutableStateOf("") }
            var amount by remember { mutableStateOf("") }
            var date by remember { mutableStateOf("") }
            var isEarned by remember { mutableStateOf(true) }

            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier.padding(16.dp)
                    .clip(CircleShape)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    title = {
                        Text(text = "Add Transactions")
                    },
                    text = {
                        MyForm(
                            onNameChange = { name = it },
                            onAmountChange = { amount = it },
                            onIsEarnedChange = { isEarned = it }
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                            }
                        ) {
                            Text("ADD")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showDialog = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "HomeScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("HomeScreen") {
                homeScreen()
            }
            composable("TransactionScreen") {
                allTransactions()
            }
            composable("SettingsPage") {
                settingsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyForm(
    onNameChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onIsEarnedChange: (Boolean) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val date by rememberUpdatedState(getCurrentDate())
    var isEarned by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange(it); name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = amount,
            onValueChange = { onAmountChange(it); amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle the keyboard done action if needed
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = date,
            onValueChange = { },
            label = { Text("Date") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isEarned,
                onClick = { isEarned = false; onIsEarnedChange(isEarned) },
                modifier = Modifier.align(CenterVertically)
            )
            Text("Spend", modifier = Modifier.clickable { isEarned = false; onIsEarnedChange(isEarned) })

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = !isEarned,
                onClick = { isEarned = true; onIsEarnedChange(isEarned) },
                modifier = Modifier.align(CenterVertically)
            )
            Text("Earned", modifier = Modifier.clickable { isEarned = true; onIsEarnedChange(isEarned) })

        }
    }
}

private fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return sdf.format(Date())
}
