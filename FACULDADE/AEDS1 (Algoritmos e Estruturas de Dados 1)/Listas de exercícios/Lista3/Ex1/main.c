#include <stdio.h>
/*
Faça um procedimento que recebe as 3 notas de um aluno por parâmetro e uma letra.
Se a letra for ‘A’, o procedimento calcula e escreve a média aritmética das notas do aluno, 
se for ‘P’, calcula e escreve a sua média ponderada (pesos: 5, 3 e 2). 
Faça um programa que leia 3 notas de N alunos e acione o procedimento para cada aluno. (N deve ser lido do teclado)
*/

void procedimento(int N,char media,int nota1,int nota2,int nota3){
  
    float aritmetica, ponderada,somaNota=0;
    somaNota=nota1+nota2+nota3;
    aritmetica=somaNota/3;
    ponderada=((nota1*5.0)+(nota2*3.0)+(nota3*2.0))/10.0;
    switch(media){
      case('A'):printf("%.2f\n",aritmetica);break;
      case('P'):printf("%.2f\n",ponderada);break;
      default:printf("\n");break;
    }
}

int main(void) {
  int N;
  float notas;
  char media;
  int nota1,nota2,nota3;
  scanf("%d",&N);
  for(int i=1;i<=N;i++){
  scanf("%d %d %d %c",&nota1,&nota2,&nota3,&media);
  procedimento(N, media, nota1,nota2,nota3);
  }
  return 0;
}