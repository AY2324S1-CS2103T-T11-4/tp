package seedu.address.management;

import seedu.address.pojo.Flashcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextFileReader {
    private String filePath;

    public TextFileReader(String filePath) {
        this.filePath = filePath;
    }

    public void readAndProcessFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(""); // Assuming tab-separated values

                if (columns.length == 4) {
                    String word = columns[0];
                    String translation = columns[1];
                    Date reviewDate = dateFormat.parse(columns[2]);
                    int level = Integer.parseInt(columns[3]);

                    Flashcard flashcard = new Flashcard(word, translation, dateFormat.format(reviewDate). level);
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "data.txt";
        TextFileReader reader = new TextFileReader(filePath);
        reader.readAndProcessFile();
    }
}


