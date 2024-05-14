import java.util.Scanner;
import java.util.ArrayList;

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
                //System.out.println(current.elemento+" inserido");
                current.prox=new CelulaMatriz();
                current.prox.ant=current;
                current=current.prox;
            }
            //System.out.println(current.ant.elemento);
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
        


       /*  CelulaMatriz current2=new CelulaMatriz();
        current2 = inicio.inicio;
        iteracao=1;
        for(int i=1; i<=(n); i++){
           for(int j=1;j<=n;j++){
            System.out.println(current2.elemento+" i-"+iteracao++);
            current2=current2.prox;
           }
           current2=current2.ant.ant.ant.ant.down;
        } */

        CelulaMatriz current3=aponta(inicio.inicio, coluna, linha);
        System.out.printf("%d ",current3.elemento);
        CelulaMatriz atual=current3;
        int passos=1;
        int direcao=0;
        
        iteracao=0;

           /*  for (int i = 1; i < n && atual != null; i++) {
                if (atual != null) for(int x=1;x<=i+help;x++){atual = umDir(atual);iteracao++; if (atual != null)System.out.printf("%d ", atual.elemento);}
                if (atual != null) for(int x=1;x<=i+help;x++){atual = umDown(atual);iteracao++; if (atual != null)System.out.printf("%d ", atual.elemento);}
                if (atual != null) for(int x=0;x<=i+help;x++){atual = umEsq(atual);iteracao++; if (atual != null)System.out.printf("%d ", atual.elemento);}
                if (atual != null) for(int x=0;x<=i+help;x++){atual = umUp(atual);iteracao++; if (atual != null)System.out.printf("%d ", atual.elemento);}
                help++;
         } */
        while(atual != null){
            
            switch(direcao){
                case 0: for(int i=1;i<=passos;i++){atual=umDir(atual);iteracao++;if (atual == null) break;System.out.printf("%d ", atual.elemento);}break;
                case 1: for(int i=1;i<=passos;i++){atual=umDown(atual);iteracao++;if (atual == null) break;System.out.printf("%d ", atual.elemento);}break;
                case 2: for(int i=1;i<=passos;i++){atual=umEsq(atual);iteracao++;if (atual == null) break;System.out.printf("%d ", atual.elemento);}break;
                case 3: for(int i=1;i<=passos;i++){atual=umUp(atual);iteracao++;if (atual == null) break;System.out.printf("%d ", atual.elemento);}break;
            }
            if (direcao == 1 || direcao == 3) {
                passos++;
            }
            // Muda a direção
            direcao = (direcao + 1) % 4;
        }
        System.out.println("\n"+iteracao);
scanner.close();
}

public static CelulaMatriz aponta(CelulaMatriz inicio,int col,int linha){
    CelulaMatriz retorno;
    retorno=inicio;

    for(int i=0;i<linha;i++){
        retorno=retorno.prox;
    }
    for(int j=0;j<col;j++){  
    retorno=retorno.down;
    }
    return retorno;
}

public static CelulaMatriz umDown(CelulaMatriz atual){
        atual=atual.down;
        return atual;
}
public static CelulaMatriz umDir(CelulaMatriz atual){
    atual=atual.prox;
    return atual;
}
public static CelulaMatriz umEsq(CelulaMatriz atual){
    atual=atual.ant;
    return atual;
}
public static CelulaMatriz umUp(CelulaMatriz atual){
    atual=atual.up;
    return atual;
}

public static void mostrar(CelulaMatriz inicio, int n) { 
    CelulaMatriz atual = inicio;
    for (int z = 1; z <= n; z++) {
        for (int x = 1; x <= n; x++) {
            /* System.out.println(atual.elemento+" caminhando"); */
            
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
}
}
