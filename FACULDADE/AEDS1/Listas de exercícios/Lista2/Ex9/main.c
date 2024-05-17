#include <stdio.h>

int main(void) {
  /*
  Um comerciante deseja fazer o levantamento do lucro das mercadorias que ele comercializa. Para isto, mandou digitar uma linha para cada mercadoria com o preço de compra e de venda de cada uma. A última linha contém preço de compra igual a 0. Escreva um programa que:
  
a) Determine e escreva quantas mercadorias proporcionaram:
• Lucro < 10%
• 10% <= lucro <= 20%
• Lucro > 20%

b) Determine e escreva o valor total de compra e de venda de todas as mercadorias, assim
como o lucro total.

*/

  //variaveis
  float precoCompra, precoVenda,lucro,lucroTotal=0.0,valorCompraTotal=0.0,ValorVendaTotal=0.0;

  int lMenos10=0,lentre10=0,lmaior20=0;

  //caluculos
  while(precoCompra!=0){
    
    scanf("%f %f",&precoCompra,&precoVenda);
    valorCompraTotal+=precoCompra;
    ValorVendaTotal+=precoVenda;
    lucro=precoVenda-precoCompra;
    lucroTotal+=lucro;
    if(lucro<(precoCompra/100)*10){
      lMenos10++;
      
    }else if(lucro>(precoCompra/100)*20){
      lmaior20++;
      
    }else if((precoCompra/100)*10<=lucro<=(precoCompra/100)*20){
      lentre10++;
      
    }
    
  }
  printf("%d\n",lMenos10);
  printf("%d\n",lentre10);
  printf("%d\n",lmaior20);
  printf("%.2f\n",valorCompraTotal);
  printf("%.2f\n",ValorVendaTotal);
  printf("%.2f\n",lucroTotal);
  
  return 0;
}