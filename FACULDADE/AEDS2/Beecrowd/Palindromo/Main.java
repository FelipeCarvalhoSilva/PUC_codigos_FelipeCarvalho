import java.io.*;
import java.nio.charset.*;

// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {
    String input = "nda";
    while (!input.equals("FIM")) {
      input = MyIO.readLine();
      if (!input.equals("FIM")) {
        if (palindromo(input, 0) <= 0)
          System.out.println("SIM");
        else
          System.out.println("NAO");
      }
    }

  }

  public static int palindromo(String input, int n) {
    if (n == input.length() / 2)
      return 0;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) != input.charAt(input.length() - 1 - i))
        return palindromo(input, n + 1) + 1;
    }

    return palindromo(input, n + 1);
  }