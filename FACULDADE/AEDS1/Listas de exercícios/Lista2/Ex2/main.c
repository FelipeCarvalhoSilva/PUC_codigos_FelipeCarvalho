#include <stdio.h>

int main(void) {

  //variaveis
  int quantidadeNumeros,numero,positivos=0,negativos=0,zeros=0,valorTotal=0;
  char porcent= '%';
  scanf("%d",&quantidadeNumeros);


  //for que recebe os numeros baseado na quantidade pedida
  for(int i=0;i<quantidadeNumeros;i++){
    
    scanf("%d",&numero);
    if(numero>0){
      positivos++;
      valorTotal++;
    }else if(numero<0){
      negativos++;
      valorTotal++;
    }else{
      zeros++;
      valorTotal++;
    }
  }
  int positivosPorcento=0,negativosPorcento=0,zerosPorcento=0;
//calcula as porcentagens
  positivosPorcento=(positivos*100)/valorTotal;
  negativosPorcento=(negativos*100)/valorTotal;
  zerosPorcento=(zeros*100)/valorTotal;

  //resultados
  printf("%d%c POSITIVOS\n%d%c NEGATIVOS\n%d%c ZEROS",positivosPorcento,porcent,negativosPorcento,porcent,zerosPorcento,porcent);

  
  return 0;
}