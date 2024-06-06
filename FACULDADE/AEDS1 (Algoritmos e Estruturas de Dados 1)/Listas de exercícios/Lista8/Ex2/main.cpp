#include <iomanip>
#include <iostream>

class Relogio {
private:
  int horas;
  int minutos;
  int segundos;

public:
  Relogio() : horas(0), minutos(0), segundos(0) {}

  void definirHorario(int h, int m, int s) {
    horas = h;
    minutos = m;
    segundos = s;
  }

  void avancarSegundo() {
    segundos++;
    if (segundos >= 60) {
      segundos = 0;
      minutos++;
      if (minutos >= 60) {
        minutos = 0;
        horas = (horas + 1) % 24;
      }
    }
  }

  std::string obterHorarioFormatado() {
    std::ostringstream ss;
    ss << std::setw(2) << std::setfill('0') << horas << ":" << std::setw(2)
       << std::setfill('0') << minutos << ":" << std::setw(2)
       << std::setfill('0') << segundos;
    return ss.str();
  }
};

int main() {
  int N;
  std::cin >> N;
  Relogio relogio[N];

  for (int i = 0; i < N; ++i) {
    int h, m, s;
    std::cin >> h >> m >> s;

    relogio[i].definirHorario(h, m, s);
    std::cout << "Horário inicial: " << relogio[i].obterHorarioFormatado()
              << std::endl;
    relogio[i].avancarSegundo();

    std::cout << "Novo horário: " << relogio[i].obterHorarioFormatado()
              << std::endl;
    std::cout << "\n";
  }

  return 0;
}
