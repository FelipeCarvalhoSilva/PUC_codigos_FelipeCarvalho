#include <stdio.h>

int main(void) {
  /*Objetivo-
  faca programa que receba 4 valores I,A,B e C, onde I é inteiro e positivo, A,B e C são reais, escreva os numeros A,B,C obedecento a tabela, supondo que o valor de I seja sempre valido  ou seja 1, 2 ou 3 e que os numeros digitados sejam diferentes um do outro

  i=1 A,B,C ordem crescente
  i=2 A,B,C ordem decrescente
  i=3 O maior fica entre os outros dois numeros
  
  Cap 4 Ex07
  Autor-Felipe
  Data-23/08/23
    */

  //variaveis
  int i;
  float num1,num2,num3;

  //recebe opcao e valores
  scanf("%f %f %f",&num1,&num2,&num3);
  scanf("%d",&i);

  
  //calculos
  //opcao 1
  if(i==1){
      //primeiro numero menor
  if(num1<num2&&num1<num3){
    if(num2<num3){
      printf("%.2f %.2f %.2f",num1,num2,num3);
    }else{
      printf("%.2f %.2f %.2f",num1,num3,num2);
    }
    //segundo numero menor
  }else if(num2<num1&&num2<num3){
    if(num1<num3){
      printf("%.2f %.2f %.2f",num2,num1,num3);
    }else{
      printf("%.2f %.2f %.2f",num2,num3,num1);
    }
    //terceiro numero menor
  }else{
    if(num1<num2){
      printf("%.2f %.2f %.2f",num3,num1,num2);
    }else{
      printf("%.2f %.2f %.2f",num3,num2,num1);
    }
  }
  }//opcao 2
  else if(i==2){
      //primeiro numero maior
  if(num1>num2&&num1>num3){
    if(num2>num3){
      printf("%.2f %.2f %.2f",num1,num2,num3);
    }else{
      printf("%.2f %.2f %.2f",num1,num3,num2);
    }
    //segundo numero maior
  }else if(num2>num1&&num2>num3){
    if(num1>num3){
      printf("%.2f %.2f %.2f",num2,num1,num3);
    }else{
      printf("%.2f %.2f %.2f",num2,num3,num1);
    }
    //terceiro numero maior
  }else{
    if(num1>num2){
      printf("%.2f %.2f %.2f",num3,num1,num2);
    }else{
      printf("%.2f %.2f %.2f",num3,num2,num1);
    }
  }
    
  }//opcao 3
  else if(i==3){
      if(num1<num2&&num1<num3){
    if(num2<num3){
      printf("%.2f %.2f %.2f",num1,num3,num2);
    }else{
      printf("%.2f %.2f %.2f",num1,num2,num3);
    }
    //segundo numero menor
  }else if(num2<num1&&num2<num3){
    if(num1<num3){
      printf("%.2f %.2f %.2f",num2,num3,num1);
    }else{
      printf("%.2f %.2f %.2f",num2,num1,num3);
    }
    //terceiro numero menor
  }else{
    if(num1<num2){
      printf("%.2f %.2f %.2f",num3,num2,num1);
    }else{
      printf("%.2f %.2f %.2f",num3,num1,num2);
    }
  }
  }
  
  return 0;
}
