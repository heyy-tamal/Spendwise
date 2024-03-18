package com.example.spendwise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun allTransactions(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            text = "All Transactions"
        )
        Spacer(modifier = Modifier.size(10.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
        LazyColumn(
            modifier = Modifier.padding(bottom = 15.dp, top = 0.dp)
        ) {
            this.items(transactionList) { transaction ->
                transactionUi(
                    name = transaction.name,
                    amount = transaction.amount,
                    date = transaction.date,
                    direction = transaction.direction
                )
            }
        }
    }
}