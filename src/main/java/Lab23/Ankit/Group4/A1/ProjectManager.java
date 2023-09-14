package Lab23.Ankit.Group4.A1;

import java.io.*;
import java.util.*;

public class ProjectManager {
    private static final String FILE_PATH = "dishes.txt";
    private static final String DELIMITER = ",";

    // Add a project
    public static void addProject(Scanner scanner) {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter product name: ");
        String projectName = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        double price = 0.0;
        boolean validPrice = false;

        while (!validPrice) {
            System.out.print("Enter product price: ");
            String priceInput = scanner.nextLine();

            try {
                price = Double.parseDouble(priceInput);
                validPrice = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number as the price.");
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String existingProductId = parts[0].trim();
                if (existingProductId.equalsIgnoreCase(productId)) {
                    System.out.println("Product ID already exists. Please enter a new one.");
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file:" + e.getMessage());
        }

        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            long fileLength = raf.length();
            if (fileLength > 0) {
                raf.seek(fileLength - 1);
                int lastChar = raf.read();
                if (lastChar != '\n') {
                    raf.writeBytes(System.lineSeparator());
                }
            }
            raf.writeBytes(productId + DELIMITER + projectName + DELIMITER + description + DELIMITER + price);
            System.out.println("The project has been added.");
        } catch (IOException e) {
            System.err.println("Error occurred while adding the project: " + e.getMessage());
        }
    }

    // Delete a project
    public static void deleteProject(Scanner scanner) {
        System.out.print("Enter the product ID to delete: ");
        String productIdToDelete = scanner.nextLine();

        boolean projectFound = false;

        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String productId = parts[0].trim();
                if (!productId.equalsIgnoreCase(productIdToDelete)) {
                    lines.add(line);
                } else {
                    projectFound = true;
                }
            }

            reader.close();

            if (projectFound) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }

                writer.close();
                System.out.println("The project has been deleted.");
            } else {
                System.out.println("Product ID does not exist, cannot delete.");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while deleting the project: " + e.getMessage());
        }
    }

    // Modify an existing project
    public static void modifyProject(Scanner scanner) {
        System.out.print("Enter the product ID to modify: ");
        String productIdToModify = scanner.nextLine();

        boolean projectFound = false;

        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String productId = parts[0].trim();
                if (productId.equalsIgnoreCase(productIdToModify)) {
                    String currentProjectName = parts[1].trim();
                    String currentDescription = parts[2].trim();
                    double currentPrice = Double.parseDouble(parts[3].trim());

                    System.out.print("Enter the new project name (" + currentProjectName + "): ");
                    String newProjectName = scanner.nextLine();

                    System.out.print("Enter the new project description (" + currentDescription + "): ");
                    String newDescription = scanner.nextLine();

                    double newPrice = 0.0;
                    boolean validPrice = false;

                    while (!validPrice) {
                        System.out.print("Enter the new project price (" + currentPrice + "): ");
                        String priceInput = scanner.nextLine();

                        try {
                            newPrice = Double.parseDouble(priceInput);
                            validPrice = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number as the price.");
                        }
                    }

                    String updatedLine = productId + DELIMITER + newProjectName + DELIMITER + newDescription + DELIMITER
                            + newPrice;
                    lines.add(updatedLine);
                    System.out.println("The project has been modified.");
                    projectFound = true;
                } else {
                    lines.add(line);
                }
            }

            reader.close();

            if (!projectFound) {
                System.out.println("Product ID does not exist, cannot modify.");
                return;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error occurred while modifying the project: " + e.getMessage());
        }
    }
}
