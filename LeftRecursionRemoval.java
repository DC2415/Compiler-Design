import java.io.*;
import java.util.*;

public class LeftRecursionRemoval {
    public static void main(String[] args) {

        String inputFileName = "grammer.txt";
        String outputFileName = "output.txt";

        try {
            Map<String, List<String>> grammar = readGrammarFromFile(inputFileName);
            Map<String, List<String>> modifiedGrammar = removeLeftRecursion(grammar);
            writeGrammarToFile(modifiedGrammar, outputFileName);
            System.out.println("Left recursion removed successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please make sure the file exists.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } 
    }

    public static Map<String, List<String>> readGrammarFromFile(String fileName) throws FileNotFoundException {
        Map<String, List<String>> grammar = new HashMap<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("->");
            String nonTerminal = parts[0].trim();
            String[] productions = parts[1].trim().split("\\|");
            grammar.put(nonTerminal, new ArrayList<>());
            for (String production : productions) {
                grammar.get(nonTerminal).add(production.trim());
            }
        }
        scanner.close();
        return grammar;
    }

    public static Map<String, List<String>> removeLeftRecursion(Map<String, List<String>> grammar) {
        Map<String, List<String>> modifiedGrammar = new HashMap<>();
        List<String> nonTerminals = new ArrayList<>(grammar.keySet());

        for (String nonTerminal : nonTerminals) {
            List<String> productions = grammar.get(nonTerminal);
            List<String> alphaProductions = new ArrayList<>();
            List<String> betaProductions = new ArrayList<>();

            for (String production : productions) {
                if (production.startsWith(nonTerminal)) {
                    alphaProductions.add(production.substring(nonTerminal.length()).trim());
                } else {
                    betaProductions.add(production.trim());
                }
            }

            if (!alphaProductions.isEmpty()) {
                String newNonTerminal = nonTerminal + "'";
                modifiedGrammar.put(newNonTerminal, new ArrayList<>());
                for (String alpha : alphaProductions) {
                    modifiedGrammar.get(newNonTerminal).add(alpha + " " + newNonTerminal);
                }
                modifiedGrammar.put(nonTerminal, new ArrayList<>());
                for (String beta : betaProductions) {
                    modifiedGrammar.get(nonTerminal).add(beta + " " + newNonTerminal);
                }
            } else {
                modifiedGrammar.put(nonTerminal, productions);
            }
        }
        return modifiedGrammar;
    }

    public static void writeGrammarToFile(Map<String, List<String>> grammar, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (Map.Entry<String, List<String>> entry : grammar.entrySet()) {
            String nonTerminal = entry.getKey();
            List<String> productions = entry.getValue();
            for (String production : productions) {
                writer.write(nonTerminal + " -> " + production + "\n");
            }
        }
        writer.close();
    }
}
