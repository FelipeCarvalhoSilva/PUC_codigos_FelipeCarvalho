import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);

        int n=scanner.nextInt();
        int linha=scanner.nextInt();
        int coluna=scanner.nextInt();

        CelulaMatriz current=new CelulaMatriz();
        cabeca inicio=new cabeca();
        inicio.inicio=current; 
        int iteracao=1;
        for(int i=1; i<=(n); i++) {
            for(int j=1;j<=n;j++){
                current.elemento=iteracao++;
                System.out.println(current.elemento+" inserido");
                current.prox=new CelulaMatriz();
                current.prox.ant=current;
                current=current.prox;
            }
            System.out.println(current.ant.elemento);
            current=current.ant.ant.ant.ant;
            current.down=new CelulaMatriz();
            current.down.up=current;
            current=current.down;
        }
        mostrar(inicio.inicio,4);
        /* current=inicio.inicio;
            for(int j=1;j<=n;j++){
                current=current.down;

                current.up.prox.down=current.prox;
                current.prox.up=current.up.prox;
                
                current.up.prox.prox.down=current.prox.prox;
                current.prox.prox.up= current.up.prox.prox;
                
                current.up.prox.prox.prox.down=current.prox.prox.prox;
                current.prox.prox.prox.up=current.up.prox.prox.prox;
                
            } */
        


        CelulaMatriz current2=new CelulaMatriz();
        current2 = inicio.inicio;
        iteracao=1;
        for(int i=1; i<=(n); i++){
           for(int j=1;j<=n;j++){
            System.out.println(current2.elemento+" i-"+iteracao++);
            current2=current2.prox;
           }
           current2=current2.ant.ant.ant.ant.down;
        }
         

}

public static void yurayura(CelulaMatriz inicio,int col,int linha){
    CelulaMatriz atual=inicio; //ajustar para ficar onde col e linha pedem
    int percorridos=1;
    while(atual!=null){
        System.out.println(atual.elemento);
        percorridos++;
        atual=atual.down;
    }
    System.out.println(percorridos);
}
public static void mostrar(CelulaMatriz inicio, int n) { 
    CelulaMatriz atual = inicio;
    for (int z = 1; z <= n; z++) {
        for (int x = 1; x <= n; x++) {
            System.out.println(atual.elemento+" caminhando");
            
            atual = atual.prox;

        }
        atual = atual.ant.ant.ant.ant;
        atual = atual.down;
//ultra gambiarra
      if(z!=4){  atual.up.prox.down = atual.prox;
        atual.prox.up = atual.up.prox;
        
        atual.up.prox.prox.down = atual.prox.prox;
        atual.prox.prox.up = atual.up.prox.prox;
        
        atual.up.prox.prox.prox.down = atual.prox.prox.prox;
        atual.prox.prox.prox.up = atual.up.prox.prox.prox;}
        
        
    }
}
    public static class cabeca{
        CelulaMatriz inicio;
    }
    public static class CelulaMatriz{
        CelulaMatriz prox;
        CelulaMatriz ant;
        CelulaMatriz up;
        CelulaMatriz down;
        int elemento;
        public void inserir(int elemento,int colunas,int linhas){
            this.elemento=elemento;
            this.prox=new CelulaMatriz();
        }
        //fazer for duplo
      
}
}
