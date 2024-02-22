import java.io.*;
import java.util.*;

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Expense {
    String date;
    String category;
    double amount;

    public Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }
}

public class ExpenseTracker {
    private Map<String, String> credentials;
    private List<Expense> expenses;

    public ExpenseTracker() {
        credentials = new HashMap<>();
        expenses = new ArrayList<>();
    }

    public void registerUser(String username, String password) {
        credentials.put(username, password);
    }

    public boolean login(String username, String password) {
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }

    public void addExpense(String date, String category, double amount) {
        expenses.add(new Expense(date, category, amount));
    }

    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("Date\t\tCategory\tAmount");
        for (Expense expense : expenses) {
            System.out.println(expense.date + "\t" + expense.category + "\t\t" + expense.amount);
        }
    }

    public void calculateCategorySum() {
        Map<String, Double> categorySum = new HashMap<>();
        for (Expense expense : expenses) {
            categorySum.put(expense.category, categorySum.getOrDefault(expense.category, 0.0) + expense.amount);
        }
        System.out.println("Category-wise Summation:");
        for (Map.Entry<String, Double> entry : categorySum.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void saveExpensesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Expense expense : expenses) {
                writer.println(expense.date + "," + expense.category + "," + expense.amount);
            }
        } catch (IOException e) {
            System.err.println("Error saving expenses to file: " + e.getMessage());
        }
    }

    public void loadExpensesFromFile(String filename) {
        expenses.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    expenses.add(new Expense(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading expenses from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker expenseTracker = new ExpenseTracker();

        // Sample user registration
        expenseTracker.registerUser("user1", "password");

        // Sample login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (expenseTracker.login(username, password)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
            return;
        }

        // Sample expense entry
        expenseTracker.addExpense("2024-02-22", "Food", 50.0);
        expenseTracker.addExpense("2024-02-23", "Transportation", 30.0);
        expenseTracker.addExpense("2024-02-24", "Shopping", 100.0);

        // Sample expense listing
        expenseTracker.listExpenses();

        // Sample category-wise summation
        expenseTracker.calculateCategorySum();

        // Sample saving and loading expenses from file
        expenseTracker.saveExpensesToFile("expenses.txt");
        expenseTracker.loadExpensesFromFile("expenses.txt");
    }
}
