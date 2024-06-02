import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String filePath = "path/to/your/file.txt";

        // Create a map to store character counts
        Map<Character, Integer> charCountMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int currentChar;

            while ((currentChar = br.read()) != -1) {
                char character = (char) currentChar;

                // Update the count in the map
                charCountMap.put(character, charCountMap.getOrDefault(character, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	    /* hii
		heloo */

        // Display character counts
        System.out.println("Character Counts:");
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue() + " occurrences");
        }
    }
}
