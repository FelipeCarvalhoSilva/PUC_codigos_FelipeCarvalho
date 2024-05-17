#include <stdio.h>

int main(void) {

  //variaveis
  int quantidadeNumeros,numero,positivos=0,negativos=0,zeros=0;
  scanf("%d",&quantidadeNumeros);

  for(int i=0;i<quantidadeNumeros;i++){
    printf("%d",i);
    scanf("%d",&numero);
    if(numero>0){
      positivos++;
    }else if(numero<0){
      negativos++;
    }else{
      zeros++;
    }
  }
  printf("%d POSITIVOS\n%d NEGATIVOS\n%d ZEROS",positivos,negativos,zeros);

  
  return 0;
}