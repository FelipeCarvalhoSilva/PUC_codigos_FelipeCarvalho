#include <stdio.h>
#include <math.h>


int main(void){
    int fibonat;
    int termo1=0,termo2=1;
    int proximo=termo1+termo2;
    scanf("%d",&fibonat);
    printf("\nFibonacci: \n%d \t\titeraçao-1\n%d\t\titeraçao-2\n",termo1,termo2);
for(int i=3; i<=fibonat;i++){
  printf("%d \t\titeraçao-%d\n",proximo,i);
  termo1=termo2;
  termo2=proximo;
  proximo=termo1+termo2;
}
    return 0;
}
