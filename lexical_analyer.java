

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class lexical_analyer {

    public static void main(String args[]) throws IOException {
        String filePath = "Circle.java"; // Replace with the actual file path

        try {
            String line = readFileContent(filePath);

            List<String> wordsList = Arrays.asList(line.split("\\s+"));
            String wordsAsString = String.join(" ", wordsList);

            for (int i = 0; i < wordsList.size(); i++) {
                if (isJavaOperator(wordsList.get(i))) {
                    System.out.print(wordsList.get(i)+" ");
                    continue;
                }
                if (isJavaKeyword(wordsList.get(i))) {
                    System.out.print( wordsList.get(i)+" ");
                    continue;
                }
                if (isValidIdentifier(wordsList.get(i))) {
                    System.out.print("Id"+" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFileContent(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static boolean isJavaOperator(String token) {
        List<String> operators = Arrays.asList("+", "-", "*", "/", "%", ">", "<", "==", "!=", ">=", "<=");

        return operators.contains(token);
    }

    private static boolean isJavaKeyword(String token) {
        String[] keywords = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
                "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "if", "implements", "import", "instanceof", "int", "interface",
                "long", "native", "new", "package", "private", "protected", "public", "return", "short",
                "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while"};

        return Arrays.asList(keywords).contains(token);
    }

    private static boolean isValidIdentifier(String token) {
        return token.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }
}
