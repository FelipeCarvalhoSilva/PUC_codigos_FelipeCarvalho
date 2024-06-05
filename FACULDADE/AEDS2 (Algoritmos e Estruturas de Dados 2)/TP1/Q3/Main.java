import java.util.Scanner;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {
    String input = "nda";
    Scanner scanner = new Scanner(System.in);

    while (!input.equals("FIM")) {

      input = scanner.nextLine();
if(!input.equals("FIM")){
      System.out.println(cifra(input));
}
    }
    scanner.close();
  }

  public static String cifra(String input) {
      StringBuilder cifrado = new StringBuilder();
      for (int i = 0; i < input.length(); i++) {
          char caractere = input.charAt(i);
          if (caractere == '\uFFFD') {
             
              cifrado.append(caractere);
          } else {
              
              char cifradoChar = (char) (caractere + 3);
              cifrado.append(cifradoChar);
          }
      }
      
      return cifrado.toString();
  }

  // @Test
  // void addition() {
  // assertEquals(2, 1 + 1);
  // }
}
