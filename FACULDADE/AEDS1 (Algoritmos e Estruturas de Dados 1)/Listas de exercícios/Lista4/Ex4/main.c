#include <stdio.h>

int resto (int a, int b){
  if((a-b)<b) return a-b;
  else return resto(a-b,b);
}

int main(void) {
  int divisor, dividido;
  scanf("%d %d",&dividido,&divisor);
  printf("%d",resto(dividido, divisor));
  return 0;
}