import java.util.Scanner;
public class Main {
   public static void main(String[] args) throws Exception {
       
       int n,linhas,colunas,val;
       Scanner input=new Scanner(System.in);

       n=input.nextInt();
       for (int i=0;i<n;i++){

         //primeira matriz
         linhas=input.nextInt();
         colunas=input.nextInt();
         MatrizDeInteiros matriz = new MatrizDeInteiros(linhas, colunas);
         for(int l=0;l<linhas;l++){
            for(int c=0;c<colunas;c++){
               val=input.nextInt();
               matriz.setValor(l,c,val);
            }
         }
         matriz.mostrarDiagonalPrincipal();
         System.out.printf("\n");
         matriz.mostrarDiagonalSecundaria();
         System.out.printf("\n");
         

         //segunda matriz
         linhas=input.nextInt();
         colunas=input.nextInt();
         MatrizDeInteiros matriz2 = new MatrizDeInteiros(linhas, colunas);
         for(int l=0;l<linhas;l++){
            for(int c=0;c<colunas;c++){
               val=input.nextInt();
               matriz2.setValor(l,c,val);
            }
         }
         MatrizDeInteiros matrizSoma = matriz.somaMatrizes(matriz2);
         matrizSoma.imprimir();

         matrizSoma.multiplicaColunasPeloNumeroDeLinhas();
         matrizSoma.imprimir();
        
         
       }
      
       input.close();
   }
}


class MatrizDeInteiros {
   private int[][] matriz;
   private int linhas, colunas;

   public MatrizDeInteiros(int linhas, int colunas) {
       this.linhas = linhas;
       this.colunas = colunas;
       this.matriz = new int[linhas][colunas];
   }

   // Método para definir um valor na matriz
   public void setValor(int linha, int coluna, int valor) {
       if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas) {
           matriz[linha][coluna] = valor;
       } else {
           System.out.println("Posição inválida!");
       }
   }

   // Método para obter um valor da matriz
   public int getValor(int linha, int coluna) {
       if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas) {
           return matriz[linha][coluna];
       } else {
           System.out.println("Posição inválida!");
           return -1; // Valor inválido para indicar erro
       }
   }

  // Método para preencher a matriz com um valor específico
  public void preencher(int valor) {
   for (int i = 0; i < linhas; i++) {
       for (int j = 0; j < colunas; j++) {
           matriz[i][j] = valor;
       }
   }
}
   // Método para pesquisar um elemento na matriz
   public boolean pesquisar(int elemento) {
       for (int i = 0; i < linhas; i++) {
           for (int j = 0; j < colunas; j++) {
               if (matriz[i][j] == elemento) {
                   return true;
               }
           }
       }
       return false;
   }

   // Método para imprimir a matriz
   public void imprimir() {
       for (int i = 0; i < linhas; i++) {
           for (int j = 0; j < colunas; j++) {
               System.out.print(matriz[i][j] + " ");
           }
           System.out.println();
       }
   }

   // Método de soma
   public void somaMatriz(){
      int soma=0;
      for(int i=0;i<this.linhas;i++){
         for(int j=0;j<this.colunas;j++){
         soma+=matriz[i][j];
         }
      }
      System.out.println(soma);
   }

  // Método de multiplicação
   public void multiplicaMatriz(){
      int multiplica=1;
      for(int i=0;i<this.linhas;i++){
         for(int j=0;j<this.colunas;j++){
            multiplica*=matriz[i][j];
         }
      }
      System.out.println(multiplica);
   }

   // Método diagonal principal
   public void mostrarDiagonalPrincipal(){
      int j=0;
      for(int i=0;i<this.linhas&&i<this.colunas;i++){
         j=i;
         System.out.printf("%d ",matriz[i][j]);
      }
   }

    // Método diagonal secundária
    public void mostrarDiagonalSecundaria(){
      int i=0;
      for(int j=this.colunas-1;j>=0;j--){
         System.out.printf("%d ",matriz[i++][j]);
      }
   }
   
   // Método soma de matriz
   public MatrizDeInteiros somaMatrizes(MatrizDeInteiros matriz2) {
      if (this.linhas != matriz2.linhas || this.colunas != matriz2.colunas) {
          throw new IllegalArgumentException("As matrizes devem ter o mesmo tamanho");
      }

      MatrizDeInteiros matrizSoma = new MatrizDeInteiros(this.linhas, this.colunas);

      for (int l = 0; l < this.linhas; l++) {
          for (int c = 0; c < this.colunas; c++) {
              matrizSoma.setValor(l, c, this.matriz[l][c] + matriz2.matriz[l][c]);
          }
      }

      return matrizSoma;
  }

      // Método para multiplicar todas as colunas da matriz pelo número de linhas
      public void multiplicaColunasPeloNumeroDeLinhas() {
         for (int j = 0; j < colunas; j++) {
             for (int i = 0; i < linhas; i++) {
                 matriz[i][j] *= linhas;
             }
         }
         for (int j = 1; j < colunas; j++) {
            for (int i = 0; i < linhas; i++) {
                matriz[i][j] =  matriz[i][0];
            }
        }
     }
}
