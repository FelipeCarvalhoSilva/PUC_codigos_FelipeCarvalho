#include <stdio.h>

int main(void) {
  FILE* arqRecebe=fopen("temp","w");
  char recebe[25];
  fgets(recebe, sizeof(recebe), stdin);
  fprintf(arqRecebe,"%s",recebe);
  fclose(arqRecebe);
  return 0;
}