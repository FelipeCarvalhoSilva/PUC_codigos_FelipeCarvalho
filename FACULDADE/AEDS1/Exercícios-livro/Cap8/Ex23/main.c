#include <stdio.h>
#include <stdbool.h>
/*
Exercicio cap 8 proposto 23
*/


//checa se  e triangulo
bool eTriangulo(int a, int b, int c){
  if(a<=b+c && b<=a+c && c<=a+b && a>0 && b>0 && c>0)return true;
else return false;
}

//tipo de triangulo
int tipoTriangulo(int l1,int l2,int l3){
  if(l1==l2&&l2==l3)return 1;
  else if(l1==l2&&l1==l3)return 2;
}
//main
int main(void) {
  int lado1,lado2,lado3;
  printf("Digite os lados do triângulo: \n");
  scanf("%d %d %d",&lado1,&lado2,&lado3);
  if(eTriangulo(lado1,lado2,lado3)){
    switch(tipoTriangulo(lado1,lado2,lado3)){
      case 1:printf("Equilátero\n");break;
      case 2:printf("Isosceles\n");break;
      case 3:printf("Escaleno\n");break;
      default:printf("Tipo Invalido\n");break;
    }
  }
  else printf("Não forma um triângulo\n");
  return 0;
}
