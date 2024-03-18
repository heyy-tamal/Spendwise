package com.example.spendwise

data class Transactions(
    val id:Int,
    var name: String,
    var amount: Double,
    var date: String,
    var direction: Boolean,
)
//val totalBudget: Double = 15000.00
val transactionList = listOf(
    Transactions(1, "Rahul", 1250.50, "15-11-2023", true),
    Transactions(2, "Amit", 800.0, "16-11-2023", true),
    Transactions(3, "Priya", 150.75, "17-11-2023", true),
    Transactions(4, "Vikram", 300.25, "18-11-2023", true),
    Transactions(5, "Anjali", 950.0, "19-11-2023", true),
    Transactions(6, "Raj", 550.50, "20-11-2023", true),
    Transactions(7, "Neha", 200.0, "21-11-2023", true),
    Transactions(8, "Sarika", 700.75, "22-11-2023", false),
    Transactions(9, "Prakash", 400.25, "23-11-2023", false),
    Transactions(10, "Kavita", 1200.0, "24-11-2023", true),
    Transactions(11, "Arun", 550.0, "25-11-2023", true),
    Transactions(12, "Shweta", 300.75, "26-11-2023", true),
    Transactions(13, "Rajat", 800.25, "27-11-2023", true),
    Transactions(14, "Divya", 950.50, "28-11-2023", true),
    Transactions(15, "Vivek", 400.0, "29-11-2023", true),
    Transactions(16, "Ananya", 700.50, "30-11-2023", false),
    Transactions(17, "Rohit", 1200.75, "01-12-2023", true),
    Transactions(18, "Pooja", 200.25, "02-12-2023", false),
    Transactions(19, "Sanjay", 1100.0, "03-12-2023", true),
    Transactions(20, "Naina", 600.25, "04-12-2023", true),
    Transactions(21, "Kiran", 350.50, "05-12-2023", true),
    Transactions(22, "Aradhya", 900.0, "06-12-2023", true),
    Transactions(23, "Hari", 500.75, "07-12-2023", true),
    Transactions(24, "Sneha", 250.25, "08-12-2023", true),
    Transactions(25, "Ravi", 770.0, "09-12-2023", false),
    Transactions(26, "Meera", 320.50, "10-12-2023", true),
    Transactions(27, "Vinod", 880.25, "11-12-2023", true),
    Transactions(28, "Kavitha", 420.0, "12-12-2023", true),
    Transactions(29, "Rahul", 690.75, "13-12-2023", false),
    Transactions(30, "Swati", 1100.25, "14-12-2023", true)

)
