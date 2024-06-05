#include <stdio.h>

int main(void) {/*Aluno: Felipe Carvalho
A prefeitura de uma cidade fez uma pesquisa entre seus habitantes, coletando dados sobre o
salário e número de filhos de cada habitante. A prefeitura deseja saber:
a) média do salário da população;
b) média do número de filhos;
c) maior salário;
d) percentual de pessoas com salário até R$100,00.
O final da leitura de dados se dará com a entrada de um salário negativo.
*/

  //variaveis
  int percentual=0,n=0;
  float salario=1,somaSalarios=0.0,mediaSalario=0.0,maiorSalario=0.0,mediaFilhos,nfilhos,somaFilhos=0.0;

  //calculos
  while(1){
    
    scanf("%f %f",&salario,&nfilhos);
    if(salario<=0){
      break;
    }
      
    somaSalarios+=salario;
    somaFilhos+=nfilhos;

    //define maior salario
    if(salario>maiorSalario){
      maiorSalario=salario;
    }
    //armazena percentual de salario menor que 100
    if(salario<=100.00){
      percentual++;
    }
    
    n++;
    
  }
  mediaSalario= somaSalarios/n;
  mediaFilhos= somaFilhos/n;
  float verdadeiroPercentual=(float)percentual/(float)n*100.00;

  //retorna resultados
  printf("%.2f\n",mediaSalario);
  printf("%.0f\n",mediaFilhos);
  printf("%.2f\n",maiorSalario);
  printf("%.2f\n",verdadeiroPercentual);
  return 0;
}