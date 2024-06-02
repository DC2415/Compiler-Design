import java.io.*;
 // practical 1 B
public class RemoveCommentFromFile {
    public static void main(String[] args) {
        String inputFileName = "test.java";
        String outputFileName = "output.java";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            StringBuilder Content = new StringBuilder();
            boolean inside = false;
            
            String line;
            while ((line = br.readLine()) != null) {
                inside = check(line, inside);
                line = remove(line, inside);
               
                Content.append(line).append("\n");
            }
            bw.write(Content.toString());
            System.out.println("Comments removed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String remove(String line, boolean inside) {
        if (inside) {
            if (line.contains("*/")) {
                inside = false;
                return line.substring(line.indexOf("*/") + 2);
            } else {
                return "";
            }
        } else {
            if (line.contains("/*")) {
                inside = true;
                return remove(line.substring(0, line.indexOf("/*")), inside);
            } else {
                return line.replaceAll("//.*", "");
            }
        }
    }

    private static boolean check(String line, boolean inside) {
        return inside || line.contains("/*");
    }
}
