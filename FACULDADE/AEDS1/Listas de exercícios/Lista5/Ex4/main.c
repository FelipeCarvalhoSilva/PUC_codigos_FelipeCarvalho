#include <stdio.h>

int main(void) {
  FILE* arqLeitura=fopen("arquivo", "r");
  char guarda[1000];
  int linhas=0;
    while (fgets(guarda, sizeof(guarda), arqLeitura) != NULL){
      printf("%s",guarda);
      linhas++;
  }
  printf("\n%d LINHAS\n",linhas);
  return 0;
}