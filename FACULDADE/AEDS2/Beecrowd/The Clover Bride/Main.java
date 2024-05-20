import java.util.Scanner;
import java.util.LinkedList;
import java.util.Comparator;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        int leeway=input.nextInt();
        int num=input.nextInt();
        LinkedList<pessoa> lista=new LinkedList<>();
        for(int i=0;i<num;i++){
            pessoa pessoa=new pessoa();
            String horaString=input.next();
            String partes[]=horaString.split(":");
            int pos=1000;
            pessoa.hora=Integer.parseInt(partes[0]);
            pessoa.minuto=Integer.parseInt(partes[1]);
            pessoa.insere=true;
            switch (pessoa.hora) {
                case 23:
                if(pessoa.minuto+leeway<60){
                    pessoa.insere=false;
                    break;}
                pos=(pessoa.minuto-leeway-30)*(-1);    
                    break;
                case 00:
                if(pessoa.minuto-leeway>0){
                    pessoa.insere=false;
                    break;}
                pos=pessoa.minuto-leeway;
                    break;
                default:
                    break;
            }
            pessoa.pos=pos;
            pessoa.nome=input.next();
            lista.add(pessoa);
        }
        lista.sort(Comparator.comparingInt(p->p.pos));
        pessoa polledPessoa = lista.poll();
        while (polledPessoa != null) {
            if(polledPessoa.insere!=false)System.out.println(polledPessoa.nome);
            polledPessoa = lista.poll();
        }
        input.close();
    }
    public static class pessoa{
        String nome;
        int hora;
        int minuto;
        int pos;
        boolean insere;
    }
}
