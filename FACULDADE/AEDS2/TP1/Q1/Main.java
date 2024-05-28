import java.io.*;

public class Main {
  public static void main(String[] args) {
    String input = "";
    while (!input.equals("FIM")) {
      input = MyIO.readLine();
      if (!input.equals("FIM")) {
        if (metodo(input)) {
          MyIO.println("SIM");
        } else {
          MyIO.println("NAO");
        }
      }
    }
  }

  public static boolean metodo(String palavra) {
    for (int i = 0; i < palavra.length()/2; i++) {
      if (palavra.charAt(i) != palavra.charAt(palavra.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }
}
