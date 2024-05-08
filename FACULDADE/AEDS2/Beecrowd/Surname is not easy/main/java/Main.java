import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    String input;
    int n, consoante, tam;
    Scanner scanner = new Scanner(System.in);
    char current;
    Boolean seguidos=false;
    n = scanner.nextInt();
    scanner.nextLine(); 
    for (int i = 0; i < n; i++) {
      input = scanner.nextLine();
      tam = input.length();
      consoante = 0; 
      for (int x = 0; x < tam; x++) {
        current = input.charAt(x);
        if (Character.toLowerCase(current) > 'a' && Character.toLowerCase(current) <= 'z'
            && Character.toLowerCase(current) != 'e' && Character.toLowerCase(current) != 'i'
            && Character.toLowerCase(current) != 'o' && Character.toLowerCase(current) != 'u') {
          consoante++;
          if(consoante>=3){
            seguidos=true;
            break;
          }
        }
        else consoante=0;
      }
      System.out.println(input+(seguidos ? " nao eh facil":" eh facil"));
      seguidos=false;
    }
    scanner.close();
  }
}
