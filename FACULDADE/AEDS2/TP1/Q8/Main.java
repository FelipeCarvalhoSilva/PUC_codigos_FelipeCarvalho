import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            MyIO.setCharset("ISO-8859-1");

          
            int n = MyIO.readInt();

           
            File file = new File("valores.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("\n");

            // Escreve os valores no arquivo
            for (int i = 0; i < n; i++) {
                double valor = MyIO.readDouble();
                // Verifica se o valor é inteiro ou não
                if (valor == (int) valor) {
                    writer.write(Integer.toString((int) valor));
                } else {
                    writer.write(Double.toString(valor));
                }
                // Adiciona uma quebra de linha 
                if (i != n - 1) {
                    writer.write("\n");
                }
                writer.flush(); // Força a escrita imediata
            }

           
            writer.close();

            // Reabre o arquivo 
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            long fileSize = raf.length();
            long currentPosition = fileSize - 1;

            // Faz a leitura reversa 
            while (currentPosition >= 0) {
                raf.seek(currentPosition);
                char c = (char) raf.readByte();

                if (c == '\n') {
                    raf.seek(currentPosition + 1);
                    String linha = raf.readLine();
                    MyIO.print(linha + "\n");
                }

                currentPosition--;
            }

            // Fecha
            raf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
