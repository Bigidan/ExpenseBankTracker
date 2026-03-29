# 💸 Expense Bank Tracker

Simple web application for tracking personal expenses.

## 🚀 Features

### 🔐 Authentication
- User registration
- User login (session-based)

### 💰 Transactions
- Create transaction
- View all transactions
- Edit transaction
- Delete transaction

### 📅 Filtering
- Filter transactions by date range

### 📥 CSV Import
- Upload CSV file with transactions
- Automatically assigns transactions to logged-in user

---

## 🖥️ Tech Stack

- Java 17+
- Spring Boot
- Spring MVC
- Thymeleaf
- Hibernate / JPA
- MySQL / PostgreSQL (or H2)

---

## 📂 Project Structure

controller/
service/
repository/
entity/
dto/

---

## 📊 Transaction Fields

- ID
- Amount
- Description
- Date
- User (owner)

---

## 📥 CSV Format

CSV must follow this structure:
amount,description,date
100,Food,2026-03-01
50,Taxi,2026-03-02


---

## 🔐 Security

- Each transaction belongs to a user
- Users can only see their own transactions

---
