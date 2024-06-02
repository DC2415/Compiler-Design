import java.io.*;

//practical 1 A
public class Line_no_file {
    public static void main(String[] args) {
        String inputFilename = "test.java";
        String outputFilename = "output.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename))) {

            int line = 1;
            String sub;

            while ((sub = br.readLine()) != null) {
                String lineWithNumber = line + " " + sub;
                System.out.println(lineWithNumber);

                bw.write(lineWithNumber);
                bw.newLine();
                
                line++;
            }

            System.out.println("Output written to the file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
