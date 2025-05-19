import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

void main() {
  runApp(const App());
}

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(home: Home());
  }
}

class Home extends StatefulWidget {
  const Home({super.key});

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  // Método para recuperar ou abrir o banco de dados
  _recuperarBD() async {
    // Obtém o caminho onde o banco de dados será salvo no dispositivo
    final caminho = await getDatabasesPath();
    final local = join(caminho, "bancodados.db");

    // Abre o banco de dados e cria a tabela 'usuarios' se ainda não existir
    var retorno = await openDatabase(
      local,
      version: 2, // Aumenta a versão para forçar a migração
      onCreate: (db, dbVersaoRecente) {
        // SQL para criar a tabela 'usuarios' com colunas de ID, matrícula, nome, idade e curso
        String sql =
            "CREATE TABLE usuarios ("
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            "matricula TEXT UNIQUE, "
            "nome VARCHAR, "
            "idade INTEGER, "
            "curso VARCHAR)";
        db.execute(sql);
      },
      onUpgrade: (db, oldVersion, newVersion) async {
        // Verifica se a tabela 'usuarios' já existe
        var tables = await db.rawQuery(
          "SELECT name FROM sqlite_master WHERE type='table' AND name='usuarios'",
        );
        if (tables.isNotEmpty) {
          // Se esta atualizando para a versão 2 e a tabela usuarios existe, verificamos se tem as colunas que precisamos
          if (oldVersion == 1 && newVersion == 2) {
            try {
              // Verifica se as colunas 'matricula' e 'curso' já existem
              var colunas = await db.rawQuery("PRAGMA table_info(usuarios)");
              bool temMatricula = false;
              bool temCurso = false;

              for (var coluna in colunas) {
                if (coluna['name'] == 'matricula') temMatricula = true;
                if (coluna['name'] == 'curso') temCurso = true;
              }

              // Adiciona as colunas faltantes se necessário
              if (!temMatricula) {
                await db.execute(
                  "ALTER TABLE usuarios ADD COLUMN matricula TEXT",
                );
              }
              if (!temCurso) {
                await db.execute(
                  "ALTER TABLE usuarios ADD COLUMN curso VARCHAR",
                );
              }
            } catch (e) {
              print("Erro ao migrar: $e");
            }
          }
        } else {
          // Se a tabela 'usuarios' não existe, criamos ela
          String sql =
              "CREATE TABLE usuarios ("
              "id INTEGER PRIMARY KEY AUTOINCREMENT, "
              "matricula TEXT UNIQUE, "
              "nome VARCHAR, "
              "idade INTEGER, "
              "curso VARCHAR)";
          await db.execute(sql);
        }
      },
    );

    print("Aberto ${retorno.isOpen.toString()}");

    return retorno;
  }

  // Método para inserir um novo aluno no banco de dados
  _salvarDados(
    BuildContext context,
    String matricula,
    String nome,
    int idade,
    String curso,
  ) async {
    if (matricula.isEmpty) {
      _mostrarDialogo(context, "A matrícula é obrigatória!");
      return;
    }

    Database db = await _recuperarBD();

    // Verifica se já existe um aluno com a mesma matrícula
    List alunos = await db.query(
      "usuarios",
      where: "matricula = ?",
      whereArgs: [matricula],
    );

    if (alunos.isNotEmpty) {
      _mostrarDialogo(context, "Já existe um aluno com esta matrícula!");
      return;
    }

    // Dados a serem inseridos, representados como um mapa
    Map<String, dynamic> dadosAluno = {
      "matricula": matricula,
      "nome": nome,
      "idade": idade,
      "curso": curso,
    };

    try {
      // Insere os dados na tabela 'usuarios' e retorna o ID do novo registro
      int id = await db.insert("usuarios", dadosAluno);
      print("Salvo $id");

      // Exibe um diálogo para o usuário confirmar que o registro foi salvo
      _mostrarDialogo(context, "Aluno salvo com sucesso!");

      // Limpa os campos após salvar
      _limparCampos();
    } catch (e) {
      _mostrarDialogo(context, "Erro ao salvar: $e");
    }
  }

  // Método para limpar os campos do formulário
  _limparCampos() {
    _matriculaController.clear();
    _nomeController.clear();
    _idadeController.clear();
    _cursoController.clear();
  }

  // Método para exibir diálogos de confirmação e mensagens
  _mostrarDialogo(BuildContext context, String mensagem) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text("Resultado"),
          content: Text(mensagem),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text("OK"),
            ),
          ],
        );
      },
    );
  }

  // Método para listar todos os alunos armazenados no banco de dados
  _listarAlunos(BuildContext context) async {
    Database db = await _recuperarBD();
    String sql = "SELECT * FROM usuarios";
    List alunos = await db.rawQuery(sql);

    // Imprime os dados de cada aluno listado no banco
    for (var aluno in alunos) {
      print(
        "id: ${aluno['id']} | matrícula: ${aluno['matricula'] ?? 'Não informada'} | nome: ${aluno['nome']} | idade: ${aluno['idade']} | curso: ${aluno['curso'] ?? 'Não informado'}",
      );
    }

    if (alunos.isEmpty) {
      _mostrarDialogo(context, "Nenhum aluno cadastrado.");
    } else {
      _mostrarDialogo(context, "Lista de alunos impressa no console.");
    }
  }

  // Método para listar um aluno específico com base na matrícula
  _listarUmAluno(BuildContext context, String matricula) async {
    if (matricula.isEmpty) {
      _mostrarDialogo(context, "Por favor, insira uma matrícula válida.");
      return;
    }

    Database db = await _recuperarBD();

    // Faz a consulta na tabela 'usuarios' com a matrícula fornecida
    List alunos = await db.query(
      "usuarios",
      columns: ["id", "matricula", "nome", "idade", "curso"],
      where: "matricula = ?",
      whereArgs: [matricula],
    );

    // Verifica se o aluno existe e exibe um diálogo com as informações
    if (alunos.isNotEmpty) {
      var aluno = alunos.first;
      _mostrarDialogo(
        context,
        "ID: ${aluno['id']} \nMatrícula: ${aluno['matricula'] ?? 'Não informada'} \nNome: ${aluno['nome']} \nIdade: ${aluno['idade']} \nCurso: ${aluno['curso'] ?? 'Não informado'}",
      );

      // Preenche os campos para facilitar a atualização
      _matriculaController.text = aluno['matricula']?.toString() ?? '';
      _nomeController.text = aluno['nome']?.toString() ?? '';
      _idadeController.text = aluno['idade']?.toString() ?? '';
      _cursoController.text = aluno['curso']?.toString() ?? '';
    } else {
      _mostrarDialogo(
        context,
        "Aluno com matrícula $matricula não encontrado.",
      );
    }
  }

  // Método para excluir um aluno com base na matrícula
  _excluirAluno(BuildContext context, String matricula) async {
    if (matricula.isEmpty) {
      _mostrarDialogo(context, "Por favor, insira uma matrícula válida.");
      return;
    }

    Database db = await _recuperarBD();

    // Exclui o registro de acordo com a matrícula fornecida
    int retorno = await db.delete(
      "usuarios",
      where: "matricula = ?",
      whereArgs: [matricula],
    );

    print("Itens excluídos: $retorno");

    // Exibe um diálogo para confirmar a exclusão
    if (retorno > 0) {
      _mostrarDialogo(
        context,
        "Aluno com matrícula $matricula excluído com sucesso.",
      );
      _limparCampos();
    } else {
      _mostrarDialogo(
        context,
        "Aluno com matrícula $matricula não encontrado.",
      );
    }
  }

  // Método para atualizar informações de um aluno existente
  _atualizarAluno(
    BuildContext context,
    String matricula,
    String? nome,
    int? idade,
    String? curso,
  ) async {
    if (matricula.isEmpty) {
      _mostrarDialogo(context, "Por favor, insira uma matrícula válida.");
      return;
    }

    Database db = await _recuperarBD();

    // Cria um mapa para atualizar os dados somente dos campos não nulos
    Map<String, dynamic> dadosAluno = {};
    if (nome != null && nome.isNotEmpty) {
      dadosAluno["nome"] = nome;
    }
    if (idade != null) {
      dadosAluno["idade"] = idade;
    }
    if (curso != null && curso.isNotEmpty) {
      dadosAluno["curso"] = curso;
    }

    // Realiza a atualização caso existam campos para modificar
    if (dadosAluno.isNotEmpty) {
      int retorno = await db.update(
        "usuarios",
        dadosAluno,
        where: "matricula = ?",
        whereArgs: [matricula],
      );

      print("Itens atualizados: $retorno");

      if (retorno > 0) {
        _mostrarDialogo(
          context,
          "Aluno com matrícula $matricula atualizado com sucesso.",
        );
      } else {
        _mostrarDialogo(
          context,
          "Aluno com matrícula $matricula não encontrado.",
        );
      }
    } else {
      _mostrarDialogo(context, "Nenhuma informação para atualizar.");
    }
  }

  final TextEditingController _matriculaController = TextEditingController();
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _idadeController = TextEditingController();
  final TextEditingController _cursoController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Cadastro de Alunos"),
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(16.0),
          alignment: Alignment.center,
          child: Column(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                margin: const EdgeInsets.all(0.5),
                width: 300,
                alignment: Alignment.center,
                child: TextField(
                  controller: _matriculaController,
                  decoration: const InputDecoration(
                    labelText: "Matrícula do Aluno",
                    border: OutlineInputBorder(),
                    hintText: "Digite a matrícula única do aluno",
                  ),
                ),
              ),
              const SizedBox(height: 10),
              Container(
                margin: const EdgeInsets.all(0.5),
                width: 300,
                alignment: Alignment.center,
                child: TextField(
                  controller: _nomeController,
                  decoration: const InputDecoration(
                    labelText: "Nome do Aluno",
                    border: OutlineInputBorder(),
                  ),
                ),
              ),
              const SizedBox(height: 10),
              Container(
                margin: const EdgeInsets.all(0.5),
                width: 300,
                alignment: Alignment.center,
                child: TextField(
                  controller: _idadeController,
                  decoration: const InputDecoration(
                    labelText: "Idade do Aluno",
                    border: OutlineInputBorder(),
                  ),
                  keyboardType: TextInputType.number,
                ),
              ),
              const SizedBox(height: 10),
              Container(
                margin: const EdgeInsets.all(0.5),
                width: 300,
                alignment: Alignment.center,
                child: TextField(
                  controller: _cursoController,
                  decoration: const InputDecoration(
                    labelText: "Nome do Curso",
                    border: OutlineInputBorder(),
                    hintText: "Digite o nome do curso",
                  ),
                ),
              ),
              const SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: () {
                      _salvarDados(
                        context,
                        _matriculaController.text,
                        _nomeController.text,
                        int.tryParse(_idadeController.text) ?? 0,
                        _cursoController.text,
                      );
                    },
                    child: const Text("Salvar Aluno"),
                  ),
                  const SizedBox(width: 10),
                  ElevatedButton(
                    onPressed: () => _listarAlunos(context),
                    child: const Text("Listar Todos"),
                  ),
                ],
              ),
              const SizedBox(height: 20),
              const Divider(),
              const SizedBox(height: 20),
              Text(
                "Operações por Matrícula",
                style: Theme.of(context).textTheme.titleMedium,
              ),
              const SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: () {
                      _listarUmAluno(context, _matriculaController.text);
                    },
                    child: const Text("Listar um usuário"),
                  ),
                  const SizedBox(width: 10),
                  ElevatedButton(
                    onPressed: () {
                      _excluirAluno(context, _matriculaController.text);
                    },
                    child: const Text("Excluir usuário"),
                  ),
                  const SizedBox(width: 10),
                  ElevatedButton(
                    onPressed: () {
                      _atualizarAluno(
                        context,
                        _matriculaController.text,
                        _nomeController.text.isNotEmpty
                            ? _nomeController.text
                            : null,
                        int.tryParse(_idadeController.text),
                        _cursoController.text.isNotEmpty
                            ? _cursoController.text
                            : null,
                      );
                    },
                    child: const Text("Atualizar usuário"),
                  ),
                ],
              ),
              const SizedBox(height: 10),
              ElevatedButton(
                onPressed: _limparCampos,
                child: const Text("Limpar Campos"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
