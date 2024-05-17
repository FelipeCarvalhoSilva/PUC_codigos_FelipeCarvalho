#include <stdio.h>

int main(void) {
  
/*
Faça um programa que receba dez números e verifique se eles são divisíveis por 3 e 9 (ao mesmo tempo), por 2 e por 5. Caso algum número não seja divisível por nenhum desses números mostre a mensagem “Número não é divisível pelos valores”. Apresente também ao final a quantidade de números divisíveis por 3 e 9, por 2 e por 5.
*/

  //variaveis
  int quantos=10;
  int div2=0,div5=0,div9e3=0,nada=0;
  float numeros;

  //loop
  for(int i=0;i<quantos;i++){
    scanf("%f",&numeros);
    
    if((int)numeros%3==0&&(int)numeros%9==0){
      div9e3++;
      if((int)numeros%2==0){
      div2++;
        if((int)numeros%5==0){
      div5++;}
    }
      
    }else if((int)numeros%2==0){
      div2++;
    }else if((int)numeros%5==0){
      div5++;
    }else{
      nada++;
    }
  }
  for(int o=1;o<nada;o++){
    printf("Número não é divisível pelos valores\n");
  }
  printf("%d Números são divisíveis por 3 e por 9\n",div9e3);
  printf("%d Números são divisíveis por 2\n",div2);
  printf("%d Números são divisíveis por 5\n",div5);
  return 0;
}