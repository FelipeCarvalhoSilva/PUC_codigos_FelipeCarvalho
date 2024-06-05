#include <stdio.h>

float media(int n,float somaNotas){
  return (somaNotas/n);
}
int main(void) {
  int n,contador=0;
  float nota,somaNotas=0;
  scanf("%d",&n);
  for(int i=1;i<=n;i++){
    contador++;
    scanf("%f",&nota);
    if(nota>=6)somaNotas+=nota;
    else contador--;
  }
  printf("%.1f\n",media(contador,somaNotas));
  return 0;
}