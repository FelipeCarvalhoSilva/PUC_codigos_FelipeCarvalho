#include <stdio.h>
#include <string.h>

// tem que declarar antes do main escopo global
typedef struct Aluno {
  char nome[30];
  int idade;
  float notas[3];
} Aluno;

// MAIN
int main(void) {
  int N, maisVelho = -1, idades = 0, nAlunos = 0;
  float media;
  char *nomeMaisvelho;
  char *Alfabetico;
  printf("Digite numero de alunos\n");
  scanf("%d", &N);
  Aluno aux;
  Aluno turma[N];
  for (int i = 0; i < N; i++) {
    printf("Digite o nome: \n");
    // espaço antes de char pra nao bugar
    scanf(" %[^\n]", turma[i].nome);

    printf("Digite a idade: \n");
    scanf("%d", &turma[i].idade);
    // pega media das idades
    idades += turma[i].idade;
    nAlunos++;
    // pega aluno mais velho
    if (turma[i].idade > maisVelho) {
      maisVelho = turma[i].idade;
      nomeMaisvelho = turma[i].nome;
    }

    printf("Digite 3 notas: \n");
    scanf("%f%f%f", &turma[i].notas[0], &turma[i].notas[1], &turma[i].notas[2]);
  }
  // media
  media = idades / nAlunos;
  printf("MEDIA DAS IDADES: %.2f\n", media);
  printf("MAIS VELHO: %d\n", maisVelho);
  printf("NOME DO MAIS VELHO: %s\n", nomeMaisvelho);

  // ordena alfabeticamente
  for (int i = 0; i < N - 1; i++) {
    for (int prox = i + 1; prox < N; prox++) {
      if (strcmp(turma[i].nome, turma[prox].nome) > 0) {
        aux = turma[i];
        turma[i] = turma[prox];
        turma[prox] = aux;
      }
    }
  }
  // printa os dados do vetor que foram ordenados
  for (int i = 0; i < N; i++) {
    printf("Os dados lidos numero:%d foram\nNome=%s\nIdade=%d\nNotas=%.2f %.2f "
           "%.2f\n\n",
           i, turma[i].nome, turma[i].idade, turma[i].notas[0],
           turma[i].notas[1], turma[i].notas[2]);
  }
}