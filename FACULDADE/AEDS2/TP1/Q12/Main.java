import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = "";

        while (!inputString.equals("FIM")) {
            inputString = scanner.nextLine();
            if (!inputString.equals("FIM")) {
                System.out.println(cifra(inputString, 0));
            }
        }

        scanner.close();
    }

    public static String cifra(String input, int n) {
        if (n >= input.length())
            return "";

        char a = input.charAt(n);
        char cifrado;

        // Verifica se o caractere é '�', se sim, mantém inalterado, caso contrário, cifra
        if (a == '\uFFFD') {
            cifrado = a;
        } else {
            cifrado = (char) (a + 3);
        }

        return cifrado + cifra(input, n + 1);
    }
}



  // @Test
  // void addition() {
  // assertEquals(2, 1 + 1);
  // }
 
