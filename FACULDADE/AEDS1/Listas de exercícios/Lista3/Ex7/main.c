#include <stdio.h>
#include <stdbool.h>

bool verificador(int num){
  if(num>0)return true;
  else return false;
  return false;
}

int main(void) {
  int n,numero;
  scanf("%d",&n);
  for(int i=0;i<n;i++){
    scanf("%d",&numero);
    if(verificador(numero)==true)printf("SIM\n");
    else printf("NAO\n");
  }
  return 0;
}