import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String inputCSV = "characters.csv";
        String outputBinary = "characters.bin";

        try (BufferedReader br = new BufferedReader(new FileReader(inputCSV));
             FileOutputStream fos = new FileOutputStream(outputBinary);
             DataOutputStream dos = new DataOutputStream(fos)) {
            // Pula a primeira linha (cabeçalho)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                byte[] lineBytes = line.getBytes();
                dos.writeInt(lineBytes.length); // Escreve o tamanho da linha
                dos.write(lineBytes);            // Escreve a linha em bytes
            }

            System.out.println("Arquivo binário criado com sucesso: " + outputBinary);

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao processar os arquivos.");
            e.printStackTrace();
        }
    }
}
