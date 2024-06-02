import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
// practical 3

public class replace_define_value {

    public static void main(String[] args) {
        String filePath = "test1.c"; // Replace with the actual file path
        String a = null, b = null;
        try {
            String line = printLinesWithWord(filePath, "#define");
            List<String> wordsList = Arrays.asList(line.split("\\s+"));
            for (int i = 0; i < wordsList.size(); i++) {
                if ("#define".equals(wordsList.get(i))) {
                    a = wordsList.get(i + 1);
                    b = wordsList.get(i + 2);
                    break;
                }
            }
            if (a != null && b != null) {
                replaceMacroInFile(filePath, a, b);
                System.out.println("Macros replaced successfully in the file.");
            } else {
                System.out.println("Error: Macro not found in the file.");
            }
        } catch (IOException e) {
            System.err.println("Error reading/writing the file: " + e.getMessage());
        }
    }

    private static void replaceMacroInFile(String filePath, String macro, String replacement) throws IOException {
        StringBuilder modifiedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace(macro, replacement);
                modifiedContent.append(line).append("\n");
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.write(modifiedContent.toString());
        }
    }

    private static String printLinesWithWord(String filePath, String searchWord) throws IOException {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchWord)) {
                    result.append(line).append("\n");
                }
            }
        }

        return result.toString();
    }
}
