#include <stdio.h>
#include <math.h>


int main(void){
    int L;
    int termo1=0,termo2=1;
    int proximo=termo1+termo2;
    scanf("%d",&L);
    printf("%d %d ",termo1+1,termo2);
  
for(int i=3; i<L;i++){
  
  termo1=termo2;
  termo2=proximo;
  proximo=termo1+termo2;
  if(proximo<L){
    printf("%d ",proximo);
  }else{
    break;
  }
  
}
    return 0;
}
