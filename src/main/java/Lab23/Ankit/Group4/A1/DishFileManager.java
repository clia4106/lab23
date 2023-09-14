package Lab23.Ankit.Group4.A1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class DishFileManager {

    private final String fileName;

    public DishFileManager(String fileName) {
        this.fileName = fileName;
    }

    public Map<Integer, dish> loadDishesFromFile() {
        Map<Integer, dish> dishesMap = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String description = parts[2].trim();  
                double price = Double.parseDouble(parts[3].trim());
                dishesMap.put(id, new dish(id, name, description, price));
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error processing the file: " + fileName);
            e.printStackTrace();
        }
        return dishesMap;
    }

    public void saveDishToFile(dish newDish) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(newDish.getId() + "," + newDish.getName() + "," + newDish.getDescription() + "," + newDish.getPrice());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }
}