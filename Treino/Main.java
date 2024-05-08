import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    /*Andy de apenas 8 anos tem um sonho - ele deseja criar o seu próprio dicionário. 
    Isto não é uma tarefa fácil para ele, pois conhece poucas palavras. 
    Bem, ao invés de pensar nas palavras que sabe, ele teve uma idéia brilhante. 
    A partir do seu livro de histórias favorito, ele vai criar um dicionário com todas 
    as palavras distintas que existem nele. Ordenando estas palavras em ordem alfabética, 
    o trabalho estará feito. É claro, isso é uma tarefa que toma um certo tempo e portanto, a
    ajuda de um programador de computador como você é muito bemvinda.
    Você foi convidado a escrever um programa que liste todas as diferentes palavras que 
    existem em um texto. Neste caso, uma palavra é definida como uma sequência de letras, 
    maiúsculas ou minúsculas. Palavras com apenas uma letra também deverão ser consideradas. 
    Portanto, seu programa deverá ser "CaSe InSeNsItIvE". Por exemplo, palavras como "Apple", 
    "apple" ou "APPLE" deverão ser consideradas como a mesma palavra.
 */

 /*
 -So detectar letras
 -Separar por espaço ou caractere que nao for letra
 -Comparar Strings lexicograficamente com equalsIgnoreCase() 
 -
  */
    public static void main(String[] args) throws Exception {
        String input;
        Scanner scanner=new Scanner(System.in);
        LinkedList<String> palavras=new LinkedList<String>();

        //int i=0;
        //&&i++<2
        while(scanner.hasNext()){
            input=scanner.nextLine();
            input=input.toLowerCase();
            input= input.replaceAll("[^a-zA-Z]", " ");
                
            
            String[]partes=input.split(" ");
            for (String parte : partes) {
                if (!parte.isEmpty()&&!palavras.contains(parte)) { // Verifica se a parte não está vazia
                    palavras.add(parte);
                }
            }
        }
        palavras.sort(null);
        for (String palavra : palavras) {
            System.out.println(palavra);
        }
    scanner.close();
}
}
