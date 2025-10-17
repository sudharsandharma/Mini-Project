import java.util.*;
import java.io.*;

class Transaction {
    String type;
    String category;
    double amount;
    String date;
    String note;

    Transaction(String type, String category, double amount, String date, String note) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-10s | ₹%-10.2f | %-10s | %s",
                type, category, amount, date, note);
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== DAILY EXPENSE TRACKER =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. View Summary");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addTransaction("Income");
                case 2 -> addTransaction("Expense");
                case 3 -> viewTransactions();
                case 4 -> viewSummary();
                case 5 -> saveData();
                case 6 -> System.out.println("Exiting... Have a nice day!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);
    }

    static void addTransaction(String type) {
        sc.nextLine(); // clear buffer
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter amount: ₹");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();
        System.out.print("Enter note/description: ");
        String note = sc.nextLine();

        transactions.add(new Transaction(type, category, amount, date, note));
        System.out.println(" " + type + " added successfully!");
    }

    static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No records found!");
            return;
        }
        System.out.println("\n--- Transaction History ---");
        System.out.printf("%-8s | %-10s | %-10s | %-10s | %s\n", "Type", "Category", "Amount", "Date", "Note");
        System.out.println("---------------------------------------------------------------");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    static void viewSummary() {
        double totalIncome = 0, totalExpense = 0;
        for (Transaction t : transactions) {
            if (t.type.equalsIgnoreCase("Income")) totalIncome += t.amount;
            else totalExpense += t.amount;
        }
        double balance = totalIncome - totalExpense;
        System.out.println("\n--- Summary ---");
        System.out.println("Total Income: ₹" + totalIncome);
        System.out.println("Total Expense: ₹" + totalExpense);
        System.out.println("Remaining Balance: ₹" + balance);
    }

    static void saveData() {
        try (FileWriter fw = new FileWriter("expenses.txt")) {
            for (Transaction t : transactions) {
                fw.write(t.type + "," + t.category + "," + t.amount + "," + t.date + "," + t.note + "\n");
            }
            System.out.println("Data saved successfully to expenses.txt");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}

