
public class Main {
  public static void main(String[] args) {
    String input = new String();
    int tam;
    char[] charVet = new char[100000];

    // loop while
    while (input != null) {
      input = MyIO.readLine();
      tam = input.length();
      int subTam;
      int n = 0;

      int index = input.indexOf("[");
      int index2 = input.indexOf("]");
      boolean stopAssigning = false;
      for (int i = 0; i < tam; i++) {
        if (input.charAt(i) == '[') {
          stopAssigning = true; // nao associa quando '[' e detectado

        } else if (input.charAt(i) == ']') {
          stopAssigning = false; // volta a associar quando ']' e detectado
        }
        if (stopAssigning)
          n++;

        if (!stopAssigning) {
          charVet[i - n] = input.charAt(i); // Assign charactere para charVet quando stopAssigning é false
        }
      }

      if (index != -1 && index2 != -1) { // Se encontrado
        // Obtém a parte da string após "["
        input = input.substring(index + 1, index2);
      } else
        input = input.substring(index + 1);
      subTam = input.length();
      for (int i = subTam - 1; i >= 0; i--) {

        insere(charVet, tam, input.charAt(i));
      }
      // so printa se o char nao for [ ou ]
      for (int i = 0; i < tam; i++) {
        if (charVet[i] != ']' && charVet[i] != '[')
          MyIO.print(charVet[i]);
      }
      MyIO.print("\n");

      // limpa o vetor pra novo loop
      charVet = new char[100000];
    }
  }

  // insere char por char no vetor de characteres
  public static void insere(char[] charVet, int tam, char input) {
    for (int i = tam - 1; i >= 0; i--) {
      charVet[i + 1] = charVet[i];
    }

    charVet[0] = input;
  }

  // FIM MAIN
}