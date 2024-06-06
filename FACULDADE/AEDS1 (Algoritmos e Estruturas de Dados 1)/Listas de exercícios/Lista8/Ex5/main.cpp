#include <cstring>
#include <iostream>

using namespace std;
// classe elevador
typedef class Elevador {
private:
  int andarAtual;
  int totalAndares;
  int capacidade;
  int pessoasPresentes;

public:
  Elevador() {
    this->andarAtual = 0;
    this->totalAndares = 0;
    this->capacidade = 0;
    this->pessoasPresentes = 0;
  }
  Elevador(int cCapacidade, int tTotalAndares) {
    setCapacidade(cCapacidade);
    setTotalAndares(tTotalAndares);
    this->andarAtual = 0;
    this->pessoasPresentes = 0;
  }
  void setCapacidade(int novo) { this->capacidade = novo; }
  void setTotalAndares(int novo) { this->totalAndares = novo; }
  void mechePessoa(string acaoPessoa) {
    if (acaoPessoa == "entrar") {
      this->pessoasPresentes++;
    } else if (acaoPessoa == "sair") {
      this->pessoasPresentes--;
    }
  }
  void mecheElevador(string acaoElevador) {
    if (strcmp(acaoElevador.c_str(), "subir") == 0) {
      this->andarAtual++;
    } else if (acaoElevador == "descer") {
      this->andarAtual--;
    }
  }

  int getAndarAtual() { return (andarAtual); }
  int getTotalAndares() { return (totalAndares); }
  int getCapacidade() { return (capacidade); }
  int getPessoasPresentes() { return (pessoasPresentes); }
  string Exibe() {
    return ("Andar atual: " + to_string(getAndarAtual()) + "\n" +
            "Pessoas presentes: " + to_string(getPessoasPresentes()) + "\n");
  }
} Elevador;

// main
int main() {
  Elevador Elevador;
  int andares, capacidadeElevador, n;
  string acao;
  cin >> capacidadeElevador >> andares >> n;
  cin.ignore();
  for (int i = 0; i < n; i++) {
    getline(cin, acao);
    Elevador.mecheElevador(acao);
    Elevador.mechePessoa(acao);
    cout << Elevador.Exibe();
  }
}