#include <stdio.h>
double somatiorio(int n){
  if(n==1) return 1;
  else return somatiorio(n-1)*n;
  
}
int main(void) {
  int n;
  double S=0;
  scanf("%d",&n);
  for(int i=1;i<=n;i++){
    S+=1/somatiorio(i);
  }
  printf("%.2f",S);
  return 0;
}