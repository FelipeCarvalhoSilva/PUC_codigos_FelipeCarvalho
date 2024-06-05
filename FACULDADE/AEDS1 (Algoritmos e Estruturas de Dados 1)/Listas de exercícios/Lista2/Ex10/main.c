#include <stdio.h>

int main(void) {
  //variaveis
  int votos;
  int c1=0,c2=0,c3=0,c4=0,nulo=0,branco=0;
  //calculos
  for(int i=0;i<7;i++){
    scanf("%d",&votos);
    if(votos==1){
      c1++;
    }else if(votos==2){
      c2++;
    }else if(votos==3){
      c3++;
    }else if(votos==4){
      c4++;
    }else if(votos==5){
      nulo++;
    }else if(votos==6){
      branco++;
    }
  }
  printf("%d\n",c1);
  printf("%d\n",c2);
  printf("%d\n",c3);
  printf("%d\n",c4);
  printf("%d\n",nulo);
  printf("%d\n",branco);
  
  return 0;
}