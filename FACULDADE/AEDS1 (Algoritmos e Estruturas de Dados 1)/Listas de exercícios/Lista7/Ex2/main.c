#include <stdio.h>
typedef struct loja{
char nome[20];
char telefone[20];
float preco;
}loja;

int main(void) {
  loja lojas[15];
  float soma=0;
  float media;
  for(int i=0;i<15;i++){
    scanf(" %[^\n]",lojas[i].nome);
    scanf(" %[^\n]",lojas[i].telefone);
    scanf("%f",&lojas[i].preco);
    soma+=lojas[i].preco;
   
  }
  
  media=soma/15;
  printf("A média dos preços cadastrados é: %.2f\n",media);
  printf("Lojas com preços abaixo da média:\n");
  for(int o=0;o<15;o++){
    if(lojas[o].preco<media){
      printf("Nome: %s\n",lojas[o].nome);
      printf("Telefone: %s\n",lojas[o].telefone);
    }
  }
  return 0;
}