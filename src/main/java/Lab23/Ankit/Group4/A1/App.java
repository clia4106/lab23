package Lab23.Ankit.Group4.A1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    static Map<Integer, dish> dishesMap = new HashMap<>();
    static List<dish> ShowOrdered = new ArrayList<>();
    static List<Payment> paymentList = new ArrayList<>();
    static boolean isAdminLoggedIn = false;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();        
    }

    public static void start(){
        initDish();
        

        while (true) {
            displayMain();

            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (num) {
                    case 1:
                        MenuOperations.DisplayMenu(dishesMap, ShowOrdered);
                        break;

                    case 2:
                        MenuOperations.ShowOrdered(dishesMap, ShowOrdered);
                        break;

                    case 3:
                        MenuOperations.processPayment(ShowOrdered, paymentList);
                        break;

                    case 4:
                        MenuOperations.historyOrder(paymentList);
                        break;

                    case 5:
                        return;

                    case 6:
                        if (!isAdminLoggedIn) {
                            adminLogin(scanner);
                        } else {
                            isAdminLoggedIn = false; // Exit admin mode
                            System.out.println("Exited admin mode.");
                        }
                        break;

                    case 7:
                        if (isAdminLoggedIn) {
                            adminPanel(scanner);
                        } else {
                            System.out.println("Please log in as an administrator to access admin functions.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else {
                System.out.println("Invalid input.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    public static int countHistoricalOrders() {
        int orderCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("adminOutput.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Order Number:")) {
                    orderCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderCount;
    }

    public static void reloadDishList() {
        DishFileManager fileManager = new DishFileManager("dishes.txt");
        dishesMap = fileManager.loadDishesFromFile();
        System.out.println("Menu reloaded.");
    }

    public static void initDish() {
        DishFileManager fileManager = new DishFileManager("dishes.txt");
        dishesMap = fileManager.loadDishesFromFile();
    }

    public static void displayMain() {
        System.out.println("Main Menu:");
        System.out.println("1. Display Menu");
        System.out.println("2. View Orders");
        System.out.println("3. Process Payment");
        System.out.println("4. View Order History");
        System.out.println("5. Exit");
        System.out.println(isAdminLoggedIn ? "6. Exit Admin Mode" : "6. Admin Login");
        System.out.println(isAdminLoggedIn ? "7. Admin Control Panel" : "");
        System.out.print("Select an operation: ");
    }

    public static void adminLogin(Scanner scanner) {
        System.out.print("Enter administrator username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter administrator password: ");
        String adminPassword = scanner.nextLine();
        isAdminLoggedIn = UserManager.login(adminUsername, adminPassword);
        if (isAdminLoggedIn) {
            System.out.println("Administrator login successful!");
        } else {
            System.out.println("Administrator login failed. Please try again.");
        }
    }

    public static void adminPanel(Scanner scanner) {
        System.out.println("Admin Control Panel:");
        System.out.println("1. Add Item");
        System.out.println("2. Delete Item");
        System.out.println("3. Modify Item");
        System.out.println("4. Register Administrator Account");
        System.out.println("5. Account the total number of order");
        System.out.println("6. Return to Main Menu");
        System.out.print("Select an operation number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                ProjectManager.addProject(scanner);
                reloadDishList(); // 重新加载菜单
                break;
            case 2:
                ProjectManager.deleteProject(scanner);
                reloadDishList(); // 重新加载菜单
                break;
            case 3:
                ProjectManager.modifyProject(scanner);
                reloadDishList(); // 重新加载菜单
                break;
            case 4:
                System.out
                        .print("Enter new administrator username (must start with a letter and not contain spaces): ");
                String newAdminUsername = scanner.nextLine();
                System.out
                        .print("Enter new administrator password (at least 8 characters and cannot contain spaces): ");
                String newAdminPassword = scanner.nextLine();
                UserManager.register(newAdminUsername, newAdminPassword); // Register a new administrator user
                break;
            case 5:
                int totalOrders = countHistoricalOrders();
                System.out.println("Total number of historical orders: " + totalOrders);
                break;
            case 6:
                System.out.println("Exiting Admin Panel.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}