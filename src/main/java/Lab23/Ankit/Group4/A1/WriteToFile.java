package Lab23.Ankit.Group4.A1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    private String fileName;

    public WriteToFile(String fileName, boolean appendMode) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }
        this.fileName = fileName;

        // If not in append mode, clear the file content
        if (!appendMode) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
                bufferedWriter.write("");  // Clear content
            } catch (IOException e) {
                System.err.println("Error clearing the file content: " + fileName);
                e.printStackTrace();
            }
        }
    }

    public void appendToFile(String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
   }
}
