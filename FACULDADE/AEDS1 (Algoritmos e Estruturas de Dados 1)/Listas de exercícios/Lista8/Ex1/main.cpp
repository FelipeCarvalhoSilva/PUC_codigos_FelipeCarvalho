#include <iomanip>
#include <iostream>
#include <string>

using namespace std;

class pessoa {
private:
  string nome;
  int idade;
  float altura;

  void inicializar(string nNome, int iIdade, float aAltura) {
    setNome(nNome);
    setIdade(iIdade);
    setAltura(aAltura);
  }

public:
  pessoa() { inicializar("nada", 1, 1); }
  pessoa(string nNome, int iIdade, float aAltura) {
    inicializar(nNome, iIdade, aAltura);
  }

  void setNome(string novo) {
    this->nome = novo;
  }

  void setIdade(int novo) {
    this->idade = novo;
  }

  void setAltura(float novo) {
    this->altura = novo;
  }

  string getNome() { return nome; }
  int getIdade() { return idade; }
  float getAltura() { return altura; }

string getTudo() {
    stringstream ss;
    ss << fixed << setprecision(2); // Configura a precisão para dois dígitos após o ponto

    ss << "Dados da pessoa:\nNome: " << nome << "\nIdade: " << idade << " anos\n"
       << "Altura: " << altura << " metros\n\n";

    return ss.str();
}

  void Exibe() { cout << getTudo(); }
};

int main() {

  int k;
 


  cin >> k;

  // Limpa o buffer
  cin.ignore();

  pessoa p[k];

  for (int i = 0; i < k; i++) {
    string nome;
    int idade;
    float altura;
    // nome
    cin>>nome>>idade>>altura;

    // Limpa o buffer
    cin.ignore();

    p[i].setNome(nome);
    p[i].setIdade(idade);
    p[i].setAltura(altura);
  }

  for (int i = 0; i < k; i++) {
    
    p[i].Exibe();
  }

  return 0;
}
