#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
  int n;
  scanf("%d",&n);
  char *vetor = (char *)malloc(10 * sizeof(char));
  scanf(" %[^\n]", vetor);
  int tamanho = strlen(vetor);
  for(int i=tamanho-1;i>=0;i--){
    printf("%c",*(vetor+i));
  }
  return 0;
}