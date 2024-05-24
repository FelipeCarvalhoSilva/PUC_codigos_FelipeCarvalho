#include <stdio.h>

int main(void) {
  FILE* arqTexto=fopen("texto", "w");
  FILE* arqLer=fopen("texto", "r");
  int n,vogais=0;
  char c;
  scanf("%d",&n);
  for(int i=0;i<n;i++){
    scanf(" %c",&c);
    fprintf(arqTexto,"%c\n",c);
  }
  fclose(arqTexto);
  while(fscanf(arqLer,"%c",&c)!=EOF){
    if(c=='a'||c=='A'||c=='e'||c=='E'||c=='i'||c=='I'||c=='o'||c=='O'||c=='u'||c=='U'){
      vogais++;
    }
  }
  printf("%d\n",vogais);
  return 0;
}