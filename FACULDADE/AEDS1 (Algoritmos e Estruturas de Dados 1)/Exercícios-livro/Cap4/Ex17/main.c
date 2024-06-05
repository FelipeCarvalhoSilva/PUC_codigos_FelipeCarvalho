#include <stdio.h>
#include <math.h>

int main(void) {
  /*Objetivo-
  Faça um programa para resolver equações do 2o grau.
  
  Cap 4 Ex17
  Autor-Felipe
  Data-20/08/23
    */

  //variaveis
  float a,b,c,delta,x1,x2;

  //recebe variaveis
  printf("Coloque em ordem as variaveis a, b e c\n");
  scanf(" %f %f %f",&a,&b,&c);

  //calculos e retorna resultados
  
  delta=pow(b,2)-4*a*c;
  x1=(-b+sqrtf(delta))/(2*a);
  x2=(-b-sqrtf(delta))/(2*a);

  
  
  if(delta>0){
    
    printf("existem duas raízes reais.\n");
    printf("X1= %.1f\nX2=%.1f\n",x1,x2);
    
  }else if(delta<0){
    
    printf("não existe raiz real.\n");
    
  }else{
    
    printf("existe uma raiz real, x1 e x2 são iguais.\n");
    printf("X1= %.1f\nX2= %.1f\n",x1,x2);
    
  }

  
  return 0;
}
