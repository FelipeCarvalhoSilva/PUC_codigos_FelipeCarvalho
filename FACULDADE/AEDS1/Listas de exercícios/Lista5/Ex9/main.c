#include <stdio.h>

int main(void) {
  int n;
  long int matricula,telefone;
  scanf("%d",&n);
  FILE *saida=fopen("saida.txt", "w");
  FILE *entrada=fopen("entrada.txt", "r");
  if(n==1){
    scanf("%ld %ld",&matricula,&telefone);
    fprintf(saida,"%ld %ld\n",matricula,telefone);
  }else{
    while(fscanf(entrada, "%ld %ld",&matricula,&telefone)!=EOF){
      fprintf(saida,"%ld %ld\n",matricula,telefone);
    }
  }
  fclose(saida);
  return 0;
}