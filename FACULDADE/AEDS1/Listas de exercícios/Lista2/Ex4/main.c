#include <stdio.h>

int main(void) {
  /*
  Escrever um algoritmo que lê um valor N inteiro e positivo e que calcula e escreve o valor de E:
E = 1 + 1/1! + 1/2! + 1/3! + .... + 1/N !  
*/
  
//variaveis
  int N,fatorial=1;
  float E=1.0;
  scanf("%d",&N);

  //calcula valor de E
  for (int i = 1; i < N; i++){
    fatorial*=i;
    E+=1.0/(float)fatorial;
    //printf("valor de E=%.2f\n",E);
}
  //printf("%d=fatorial\n",fatorial);
  printf("%.2f\n",E);
  return 0;
}