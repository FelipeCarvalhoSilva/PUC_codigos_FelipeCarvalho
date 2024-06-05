import java.io.*;
import java.nio.charset.*;

// import org.junit.jupiter.api.Test;

//Crie um método iterativo que recebe uma string e retorna true se a mesma é composta somente por vogais. Crie outro método iterativo} que recebe uma string e retorna true se a mesma é composta somente por consoantes. Crie um terceiro método iterativo que recebe uma string e retorna true se a mesma corresponde a um número inteiro. Crie um quarto método iterativo que recebe uma string e retorna true se a mesma corresponde a um número real. Na saída padrão, para cada linha de entrada, escreva outra de saída da seguinte forma X1 X2 X3 X4 onde cada Xi é um booleano indicando se a é entrada é: composta somente por vogais (X1); composta somente somente por consoantes (X2); um número inteiro (X3); um número real (X4). Se Xi for verdadeiro, seu valor será SIM, caso contrário, NÃO.
public class Main {
  public static void main(String[] args) {
    String input = "nda";
    while (!input.equals("FIM")) {
      input = MyIO.readLine();
      if (!input.equals("FIM")) {
        String resp1;
        String resp2;
        String resp3;
        String resp4;
        if (eVogal(input) == true)
          resp1 = "SIM";
        else
          resp1 = "NAO";

        if (eConsoante(input) == true)
          resp2 = "SIM";
        else
          resp2 = "NAO";

        if (eInteiro(input) == true)
          resp3 = "SIM";
        else
          resp3 = "NAO";

        if (eReal(input) == true)
          resp4 = "SIM";
        else
          resp4 = "NAO";
        System.out.println(resp1 + " " + resp2 + " " + resp3 + " " + resp4);

      }
    }
  }

  // vogal
  public static boolean eVogal(String input) {

    for (int i = 0; i < input.length(); i++) {
      char currentChar = Character.toLowerCase(input.charAt(i));

      if (currentChar != 'a' && currentChar != 'e' && currentChar != 'i' && currentChar != 'o' && currentChar != 'u')
        return false;
    }

    return true;
  }

  // consoante
  public static boolean eConsoante(String input) {
    for (int i = 0; i < input.length(); i++) {
      char currentChar = Character.toLowerCase(input.charAt(i));
      if (!(currentChar >= 'b' && currentChar <= 'z' && currentChar != 'a' && currentChar != 'e' && currentChar != 'i'
          && currentChar != 'o' && currentChar != 'u'))
        return false;
    }
    return true;
  }

  // inteiro
  public static boolean eInteiro(String input) {

    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (!(currentChar >= '0' && currentChar <= '9'))
        return false;
    }

    return true;
  }

  // real
  public static boolean eReal(String input) {
    int n = 0;
    
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (currentChar != ',' && currentChar != '.'&&currentChar<'0'||currentChar>'9')return false;
      if (currentChar == ',' || currentChar == '.')
        n++;
    }

    if (n == 1) {
      for (int i = 0; i < input.length(); i++) {
        char currentChar = input.charAt(i);
        if (!(currentChar >= '0' && currentChar <= '9' || currentChar == ',' || currentChar == '.'))
          return false;
      }
      return true;
    }
    if ((eInteiro(input) == false) && n > 1) {
      String[] casasDecimais = input.split("[,.]");
      for (String casa : casasDecimais) {
        if (!casa.isEmpty()) {
          if (casa.length() < 3)
            return false;
        }
      }

    }
    return true;
  }

  class MyIO {

    private static BufferedReader in = new BufferedReader(
        new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
    private static String charset = "ISO-8859-1";

    public static void setCharset(String charset_) {
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
    }

    public static void print() {
    }

    public static void print(int x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void print(float x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void print(double x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void print(String x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void print(boolean x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void print(char x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void println() {
    }

    public static void println(int x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.println(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void println(float x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.println(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void println(double x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.println(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void println(String x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.println(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void println(boolean x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.println(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void println(char x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.println(x);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static void printf(String formato, double x) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.printf(formato, x);// "%.2f"
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
    }

    public static double readDouble() {
      double d = -1;
      try {
        d = Double.parseDouble(readString().trim().replace(",", "."));
      } catch (Exception e) {
      }
      return d;
    }

    public static double readDouble(String str) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(str);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
      return readDouble();
    }

    public static float readFloat() {
      return (float) readDouble();
    }

    public static float readFloat(String str) {
      return (float) readDouble(str);
    }

    public static int readInt() {
      int i = -1;
      try {
        i = Integer.parseInt(readString().trim());
      } catch (Exception e) {
      }
      return i;
    }

    public static int readInt(String str) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(str);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
      return readInt();
    }

    public static String readString() {
      String s = "";
      char tmp;
      try {
        do {
          tmp = (char) in.read();
          if (tmp != '\n' && tmp != ' ' && tmp != 13) {
            s += tmp;
          }
        } while (tmp != '\n' && tmp != ' ');
      } catch (IOException ioe) {
        System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
    }

    public static String readString(String str) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(str);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
      return readString();
    }

    public static String readLine() {
      String s = "";
      char tmp;
      try {
        do {
          tmp = (char) in.read();
          if (tmp != '\n' && tmp != 13) {
            s += tmp;
          }
        } while (tmp != '\n');
      } catch (IOException ioe) {
        System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
    }

    public static String readLine(String str) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(str);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
      return readLine();
    }

    public static char readChar() {
      char resp = ' ';
      try {
        resp = (char) in.read();
      } catch (Exception e) {
      }
      return resp;
    }

    public static char readChar(String str) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(str);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
      return readChar();
    }

    public static boolean readBoolean() {
      boolean resp = false;
      String str = "";

      try {
        str = readString();
      } catch (Exception e) {
      }

      if (str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") ||
          str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")) {
        resp = true;
      }

      return resp;
    }

    public static boolean readBoolean(String str) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(str);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
      return readBoolean();
    }

    public static void pause() {
      try {
        in.read();
      } catch (Exception e) {
      }
    }

    public static void pause(String str) {
      try {
        PrintStream out = new PrintStream(System.out, true, charset);
        out.print(str);
      } catch (UnsupportedEncodingException e) {
        System.out.println("Erro: charset invalido");
      }
      pause();
    }
  }
}