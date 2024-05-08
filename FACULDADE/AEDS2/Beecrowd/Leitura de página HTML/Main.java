import java.io.*;
import java.net.*;
import java.nio.charset.*;

public class Main {
  public static String getHtml(String endereco) {
    URL url;
    InputStream is = null;
    BufferedReader br;
    StringBuilder resp = new StringBuilder(); // Usando StringBuilder para eficiência
    String line;

    try {
      url = new URL(endereco);
      is = url.openStream(); // throws an IOException
      br = new BufferedReader(new InputStreamReader(is));

      while ((line = br.readLine()) != null) {
        resp.append(line).append("\n"); // Usando StringBuilder
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally { // Feche o InputStream no bloco finally para garantir que seja fechado mesmo em
                // caso de exceção
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return resp.toString();
  }

  public static void main(String[] args) {
    String endereco, html, nome;
    endereco = "";
    nome = "";

    // while de entradas
    do {
      nome = MyIO.readLine();
      if (nome.equals("FIM"))
        break;
      endereco = MyIO.readLine();
      if (!endereco.equals("FIM") || !nome.equals("FIM")) {
        html = getHtml(endereco);
        int a = 0, e = 0, i = 0, o = 0, u = 0, a_acento = 0, e_acento = 0, i_acento = 0, o_acento = 0, u_acento = 0,
            a_agudo = 0, e_agudo = 0, i_agudo = 0, o_agudo = 0,
            u_agudo = 0, a_til = 0, o_til = 0, a_circunflexo = 0, e_circunflexo = 0, i_circunflexo = 0,
            o_circunflexo = 0, u_circunflexo = 0, br = 0, table = 0;
        int consoante = 0;

        // percorre a string
        for (int x = 0; x < html.length(); x++) {

          char current = html.charAt(x);
          switch (current) {
            // a
            case 'a':
              a++;
              break;
            case 225: // á
              a_acento++;
              break;
            case 224: // à
              a_agudo++;
              break;
            case 227: // ã
              a_til++;
              break;
            case 226: // â
              a_circunflexo++;
              break;

            // e
            case 'e':
              e++;
              break;
            case 233: // é
              e_acento++;
              break;
            case 232: // è
              e_agudo++;
              break;
            case 234: // ê
              e_circunflexo++;
              break;

            // i
            case 'i':
              i++;
              break;
            case 237: // í
              i_acento++;
              break;
            case 236: // ì
              i_agudo++;
              break;
            case 238: // î
              i_circunflexo++;
              break;

            // o
            case 'o':
              o++;
              break;
            case 243: // ó
              o_acento++;
              break;
            case 242: // ò
              o_agudo++;
              break;
            case 245: // õ
              o_til++;
              break;
            case 244: // ô
              o_circunflexo++;
              break;

            // u
            case 'u':
              u++;
              break;
            case 250: // ú
              u_acento++;
              break;
            case 249: // ù
              u_agudo++;
              break;
            case 251: // û
              u_circunflexo++;
              break;
            default:
              if (current >= 'a' && current <= 'z') {
                consoante++;
              }
              break;
          }
        }
        if (html.contains("<br>"))
          br++;
        if (html.contains("<table>"))
          table++;
        // resposta
        MyIO.print("a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") á(" + a_acento + ") é(" + e_acento
            + ") í(" + i_acento
            + ") ó(" + o_acento + ") ú(" + u_acento + ") à(" + a_agudo + ") è(" + e_agudo + ") ì(" + i_agudo + ") ò("
            + o_agudo
            + ") ù(" + u_agudo + ") ã(" + a_til + ") õ(" + o_til + ") â(" + a_circunflexo + ") ê(" + e_circunflexo
            + ") î(" + i_circunflexo + ") ô(" + o_circunflexo + ") û(" + u_circunflexo + ") consoante(" + consoante
            + ") <br>(" + br + ") <table>(" + table + ") " + nome + "\n");
      }
    } while (!endereco.equals("FIM") || !nome.equals("FIM"));
  }
}
