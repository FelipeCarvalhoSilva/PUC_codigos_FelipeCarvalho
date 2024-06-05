// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {
    int input, tamanho;
    String operacoes = new String();
    char charVet[] = new char[999];
    while (true) {
      input = MyIO.readInt();
      if(input<=0)break;
        String[] valores = lerValores(input);
        operacoes = MyIO.readLine();
        tamanho = operacoes.length();
        // preenche vetor
        for (int i = 0; i < tamanho; i++) {
          charVet[i] = operacoes.charAt(i);
        }
        // tira , e ' '
        for (int i = 0; i < tamanho; i++) {
          if (charVet[i] == ' ' || charVet[i] == ',') {
            for (int j = i; j < tamanho - 1; j++) {
              charVet[j] = charVet[j + 1];
            }
            i--;
            // coloca nada onde era o Fim do vetor
            charVet[tamanho] = '\0';
            tamanho--;
          }
        }
        trocar(charVet, valores, tamanho);
        MyIO.print(Contas(charVet, tamanho)+"\n");
      
    }
  }

  // coloca valores booleanos em vetor string
  public static String[] lerValores(int nInt) {
    String[] listaValores = new String[nInt];
    for (int i = 0; i < nInt; i++) {
      listaValores[i] = MyIO.readString();
    }
    return listaValores;
  }

  // troca letra por valor binário
  public static void trocar(char[] charVet, String[] listaValores, int tam) {
    int index = 0;
    for (int i = 0; i < listaValores.length; i++) {
      for (int j = 0; j < tam; j++) {
        if ((int) charVet[j] == index + 65) {
          charVet[j] = (char) listaValores[index].charAt(0);
        }
      }
      index++;
    }
  }

  public static char Contas(char[] charVet, int tam) {

    char substituir;
    char[] valores = new char[10];
    int numVar = 0;

    for (int i = 0; i < tam; i++) {
      if (charVet[i] == ')') {

        for (int j = i; j > 0; j--) {
          if (charVet[j] == '(') {
            // not
            if (charVet[j - 1] == 't') {
              valores[numVar] = charVet[j + 1];
              numVar++;

              substituir = not(valores, numVar);
              charVet[j - 3] = substituir;
              for (int k = 0; k < numVar + 4; k++) {
                for (int w = j - 2; w < tam - 1; w++) {
                  charVet[w] = charVet[w + 1];
                }
                charVet[tam - k] = '\0';
              }
              tam -= numVar + 4;
              numVar = 0;
              i = 0;
              j = 0;
            }

            // and
            else if (charVet[j - 1] == 'd') {
              for (int k = j + 1; k < i; k++) {
                valores[numVar] = charVet[k];
                numVar++;
              }
              substituir = and(valores, numVar);
              charVet[j - 3] = substituir;

              for (int k = 0; k < numVar + 4; k++) {
                for (int w = j - 2; w < tam - 1; w++) {
                  charVet[w] = charVet[w + 1];
                }
                charVet[tam - k] = '\0';
              }

              tam -= numVar + 4;
              numVar = 0;
              i = 0;
              j = 0;
            }

            // or
            else if (charVet[j - 1] == 'r') {
              for (int k = j + 1; k < i; k++) {
                valores[numVar] = charVet[k];
                numVar++;
              }
              substituir = or(valores, numVar);
              charVet[j - 2] = substituir;

              for (int k = 0; k < numVar + 3; k++) {
                for (int w = j - 1; w < tam - 1; w++) {
                  charVet[w] = charVet[w + 1];
                }
                charVet[tam - k] = '\0';
              }
              tam -= numVar + 3;
              numVar = 0;
              i = 0;
              j = 0;
            }
          }
        }
      }
    }
    return charVet[0];
  }

  // not
  public static char not(char[] valores, int numVar) {
    char retorno = '1';
    for (int i = 0; i < numVar; i++) {

      if (valores[i] == '1') {
        retorno = '0';
        i = numVar;
        valores = new char[10];
      }
    }
    return retorno;

  }

  // and
  public static char and(char[] valores, int numVar) {
    char retorno = '1';
    for (int i = 0; i < numVar; i++) {

      if (valores[i] == '0') {
        retorno = '0';
        i = numVar;
        valores = new char[10];
      }
    }
    return retorno;

  }

  // or
  public static char or(char[] valores, int numVar) {
    char retorno = '0';
    for (int i = 0; i < numVar; i++) {

      if (valores[i] == '1') {
        retorno = '1';
        i = numVar;
        valores = new char[10];
      }
    }
    return retorno;
  }
  // @Test
  // void addition() {
  // assertEquals(2, 1 + 1);
  // }
}