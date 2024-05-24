#include <stdio.h>

int main(void) {
  FILE* arqEntrada=fopen("temp","w");
  for(int i=1;i<=10;i++){
    fprintf(arqEntrada,"%d\n",i);
  }
  fclose(arqEntrada);
  return 0;
}