#include <stdio.h>

int main(void) {
  FILE* arqSoma=fopen("soma", "w");
  int n,divisor=0;
  scanf("%d",&n);
  for(int i=1;i<n;i++){
    if(n%i==0){
      printf("%d\n",i);
      divisor+=i;
    }
  }
  fprintf(arqSoma, "%d",divisor);
  fclose(arqSoma);
  return 0;
}