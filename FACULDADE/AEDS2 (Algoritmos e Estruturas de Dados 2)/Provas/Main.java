import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        int elemento;
        //int input=Integer.parseInt(scanner.nextLine());
        int input=Integer.parseInt(scanner.nextLine());
        for(int i=0;i<input;i++){
            
            Arvore tree=new Arvore();
            int input2=scanner.nextInt();
            int vetor[]=new int[100];
            for(int j=0;j<input2;j++){
                elemento=scanner.nextInt();
                vetor[j]=input2;
                tree.inserir(elemento);
            }
            System.out.println("Case "+(i+1)+":");
            for(int x=0;x<input2;x++){
                System.out.printf(vetor[x]+" ");
            }
            tree.caminhar();
            System.out.printf("\n");
        }
        scanner.close();
    }
    public static class Arvore{
        No raiz;
        Arvore(){
            this.raiz=null;
        }
        public static class No{
            int elemento;
            No esq,dir;
            No(int x){
                this.elemento=x;
                this.esq=null;
                this.dir=null;
            }
        }
        
        public void inserir(int x){
            inserir(x, this.raiz);
        }
        public void inserir(int x,No raiz){
            if(raiz==null){
                raiz= new No(x);
                raiz.elemento=x;
            }
            else{
                if(x>raiz.elemento){
                    inserir(x, raiz.dir);
                }else if(x<raiz.elemento){
                    inserir(x, raiz.esq);
                }
            }
        }
        void caminhar(){
            caminhar(this.raiz);
        }
        void caminhar(No raiz){
            if(raiz!=null){
                caminhar(raiz.esq);
                System.out.print(raiz.elemento+" ");
                caminhar(raiz.dir);
            }
        }
    }
}
