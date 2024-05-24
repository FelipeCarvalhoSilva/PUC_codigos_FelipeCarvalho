#include <stdio.h>

int main(void) {
  FILE* arqCria=fopen("arq3", "w");
  fclose(arqCria);
  char guarda[1000];
  FILE *arqTemp = fopen("arq3", "a");
  FILE *arq1 = fopen("arq1", "r");
  FILE *arq2 = fopen("arq2", "r");
  while (fgets(guarda, sizeof(guarda), arq1) != NULL){
      fprintf(arqTemp,"%s",guarda);
  }
  fprintf(arqTemp,"\n");
  while (fgets(guarda, sizeof(guarda), arq2) != NULL){
      fprintf(arqTemp,"%s",guarda);
  }
  fclose(arqTemp);
  return 0;
}