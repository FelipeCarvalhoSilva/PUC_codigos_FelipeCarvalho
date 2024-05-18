#include <stdio.h>


int somaDigitos(int n){
  if((n/10)<1)return n;
  else return somaDigitos(n/10)+n%10;
}
int main(void) {
  int n;
  scanf("%d",&n);

  
  printf("%d\n",somaDigitos(n));
  return 0;
}