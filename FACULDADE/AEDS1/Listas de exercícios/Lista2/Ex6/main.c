#include <stdio.h>

int main(void) {
  //variaveis
  int N;
  float E=0;
  scanf("%d",&N);

  //calculos
  for (int i = 1; i <= N; i++){
    
    E+=1.0/(float)i;
}
 
  printf("%.2f\n",E);
  return 0;
}