import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// Practical 1 demo
public class FileFunction {
    // count no of characters, spaces, words, and lines in a given programming file
    public static void main(String[] args) {
        String filename = "test.java";
        int cCount = 0, spaceCount = 0, wCount = 0, nLine = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int currentChar;

            while ((currentChar = br.read()) != -1) {
                char character = (char) currentChar;

                // Count characters
                cCount++;

                // Count spaces
                if (Character.isWhitespace(character)) {
                    spaceCount++;
                }

                // Count words (assuming words are separated by spaces)
                if (Character.isWhitespace(character) || character == '\n') {
                    wCount++;
                }

                // Count lines
                if (character == '\n') {
                    nLine++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        // Display the counts
        System.out.println("Number of characters: " + cCount);
        System.out.println("Number of spaces: " + spaceCount);
        System.out.println("Number of words: " + wCount);
        System.out.println("Number of lines: " + nLine);
    }
}
