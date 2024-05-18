#include <stdio.h>

int divide (int a, int b){
  if((a-b)<b) return 1;
  else return divide(a-b,b)+1;
}

int main(void) {
  int divisor, dividido;
  scanf("%d %d",&dividido,&divisor);
  printf("%d",divide(dividido, divisor));
  return 0;
}