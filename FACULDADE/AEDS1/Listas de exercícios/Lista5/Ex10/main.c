#include <stdio.h>
/*
Considere um arquivo texto entrada.txt que armazene números em ponto flutuante
em cada uma de suas linhas. Escreva um programa em C que determine o valor
máximo, o valor mínimo e a média desses valores armazenados no arquivo. Imprima
esses valores na tela.
*/
int main(void) {
  int i=0;
  FILE *ler=fopen("entrada.txt", "r");
  float min=99999,max=-99999,guarda,media=0;
  while(fscanf(ler,"%f",&guarda)!=EOF){
    i++;
    media+=guarda;
    if(guarda>max)max=guarda;
    if(guarda<min)min=guarda;
  }
  printf("%.2f\n%.2f\n%.2f\n",max,min,media/i);
  return 0;
}