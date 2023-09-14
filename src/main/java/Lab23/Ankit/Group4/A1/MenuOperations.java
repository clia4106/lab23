package Lab23.Ankit.Group4.A1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class MenuOperations {

    public static int currentOrderNumber = 1;
    public static Scanner scanner = new Scanner(System.in);
    public static WriteToFile customerFileWriter = new WriteToFile("customerOutput.txt", false);
    public static WriteToFile adminFileWriter = new WriteToFile("adminOutput.txt", true);
    
    public static Map<Integer, dish> dishesMap = new HashMap<>();

    static {
            loadCurrentOrderNumber();
        }
    public static final String ORDER_NUMBER_FILE = "orderNumber.txt";

    public static void saveCurrentOrderNumber() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDER_NUMBER_FILE))) {
            writer.println(currentOrderNumber);
        } catch (IOException e) {
            System.err.println("Error saving order number.");
            e.printStackTrace();
        }
    }

    public static void loadCurrentOrderNumber() {
        File file = new File(ORDER_NUMBER_FILE);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                if (scanner.hasNextInt()) {
                    currentOrderNumber = scanner.nextInt();
                }
            } catch (IOException e) {
                System.err.println("Error loading order number.");
                e.printStackTrace();
            }
        }
    } 
    
    public static void DisplayMenu(Map<Integer, dish> dishesMap, List<dish> ShowOrdered) {
        while (true) {
            System.out.println("Please order:");
            for (Integer dishId : dishesMap.keySet()) {
                dish dishItem = dishesMap.get(dishId);
                System.out.println(dishItem.getId() + ". " + dishItem.getName() + " - " + dishItem.getDescription() + " - $" + dishItem.getPrice());
            }
            System.out.println("Press 0 to return to main menu.");
            
            int choice = scanner.nextInt();
            if (choice == 0) {
                return; // Return to main menu
            } else if (dishesMap.containsKey(choice)) {
                dish selectedDish = dishesMap.get(choice);
                boolean found = false;
                for (dish d : ShowOrdered) {
                    if (d.getId() == selectedDish.getId()) {
                        d.increaseQuantity();
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ShowOrdered.add(new dish(selectedDish));  // Using the copy constructor
                }
                System.out.println("You added " + selectedDish.getName());
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void ShowOrdered(Map<Integer, dish> dishesMap, List<dish> ShowOrdered) {
        while (true) {
            System.out.println("You ordered: ");
            int index = 1;
            for (dish d : ShowOrdered) {
                System.out.println(index + ". " + d.getName() + " x" + d.getQuantity() + " Total: $" + d.getTotalPrice());
                index++;
            }
            System.out.println("\n--- Options ---");
            System.out.println("1. Return to menu to order more dishes");
            System.out.println("2. Remove a dish");
            System.out.println("3. Back to main");
    
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    DisplayMenu(dishesMap, ShowOrdered); // Return to the dish selection menu
                    return;
                case 2:
                    System.out.println("Enter the number of the dish you want to remove: ");
                    int dishNumber = scanner.nextInt();
                    if (dishNumber > 0 && dishNumber <= ShowOrdered.size()) {
                        dish selectedDish = ShowOrdered.get(dishNumber - 1);
                        if (selectedDish.decreaseQuantity()) { 
                            System.out.println("Decreased quantity for " + selectedDish.getName());
                        } else {
                            ShowOrdered.remove(selectedDish);
                            System.out.println("Removed " + selectedDish.getName() + " from your order.");
                        }
                    } else {
                        System.out.println("Invalid dish number!");
                    }
                    break;
                case 3:
                    return;                     
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
    
    
    public static void processPayment(List<dish> ShowOrdered, List<Payment> paymentList){
        if (ShowOrdered.isEmpty()){
            System.out.println("The order is empty, please go to buy.");
            return;
        }

        List<dish> paymentDishList = new ArrayList<>();
        double total = 0;
        StringBuilder details = new StringBuilder();
        int index = 1;

        for(dish dish : ShowOrdered){
            total += dish.getTotalPrice();  // 注意这里要乘以数量
            paymentDishList.add(dish);
            details.append(index).append(". ").append(dish.getName()).append(" x").append(dish.getQuantity()).append(" Total: $").append(dish.getTotalPrice());
            if(index != ShowOrdered.size()){
                details.append("\n\r");
            }
            index ++;
        }

        // Choose Delivery or Pickup Option
        System.out.println("Choose your preferred delivery method: ");
        System.out.println("1. Delivery");
        System.out.println("2. Pickup");
        int deliveryChoice = scanner.nextInt();
        String deliveryMethod = (deliveryChoice == 1) ? "Delivery" : "Pickup";

        String output = "Order Number: " + currentOrderNumber + "\nDelivery Method: " + deliveryMethod + "\nThe price for this meal is: $" + total + ", details are :\n\r" + details;
        System.out.println(output);

        // Save to customer file and also append to admin file.
        customerFileWriter.appendToFile(output);
        adminFileWriter.appendToFile(output);

        while (true) {
            System.out.println("\n--- Options ---");
            System.out.println("1. Pay the order");
            System.out.println("2. Back to main");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentOrderNumber++;
                    saveCurrentOrderNumber();
                    Payment payment = new Payment(paymentDishList, total, new Date(), currentOrderNumber);
                    paymentList.add(payment);
                    ShowOrdered.clear();
                    System.out.println("Your order has been paid successfully with order number: " + currentOrderNumber);
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        
    }


    static String loadOrderDetailsByOrderNumber(int orderNumber) {
        File file = new File("adminOutput.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains("Order Number: " + orderNumber)) {
                        // Found the order. Return its details.
                        StringBuilder orderDetails = new StringBuilder(line);  // Start with the found line
                        while (scanner.hasNextLine()) {
                            line = scanner.nextLine();
                            if (line.startsWith("Order Number: ")) {
                                // Found the next order. Stop reading.
                                break;
                            }
                            orderDetails.append("\n").append(line);
                        }
                        return orderDetails.toString();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading order details by order number.");
                e.printStackTrace();
            }
        }
        return null;  // Order not found
    }
    

    public static void historyOrder(List<Payment> paymentList) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
        while (true) {
            if(paymentList.isEmpty() && isFileEmpty("adminOutput.txt")){
                System.out.println("No historical order");
                return;  // If there are no historical orders, you can exit the method.
            }
    
            if(!paymentList.isEmpty()) {
                System.out.println("Order History:");
                for (int i = 0; i < paymentList.size(); i++) {
                    Payment payment = paymentList.get(i);
                    System.out.println((i+1)+". Order number: " + payment.getOrderNumber() + ". Paid time: " + format.format(payment.getPaymentDate()) + ", total money:$" + payment.getTotal() + ", total count: " + payment.getDishes().size());
                }
            }
    
            System.out.println("Enter order number to view details or press 0 to go back to the main menu");
            int orderNumber = scanner.nextInt();
    
            if (orderNumber == 0) {
                return; // Return to main menu
            } 
    
            boolean orderFound = false;
            for (Payment payment : paymentList) {
                if (payment.getOrderNumber() == orderNumber) {
                    orderFound = true;
                    List<dish> dishList = payment.getDishes();
                    System.out.println("Details for order number " + payment.getOrderNumber() + ":");
                    for (dish dishItem : dishList) {
                        System.out.println(dishItem.getName() + " x" + dishItem.getQuantity() + " Total: $" + dishItem.getTotalPrice());
                    }
                    break;
                }
            }
                
            if (!orderFound) {
                String orderDetails = loadOrderDetailsByOrderNumber(orderNumber);
                if (orderDetails != null) {
                    System.out.println("Details for order number " + orderNumber + ":");
                    System.out.println(orderDetails);
                } else {
                    System.out.println("Order not found!");
                }
            }
        }
    }
    
    public static boolean isFileEmpty(String filename) {
        File file = new File(filename);
        return file.length() == 0;
    }
}

