import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Main {
    static BTree bTree;
    public static void main(String[] args) throws IOException {
        String inputCSV = "characters.csv";
        String outputBinary = "characters.bin";
    
        int ordem = 3;
        BTree bTree = new BTree(ordem);  // Inicializa a árvore B
        
        int idbin = 0;
        long posicaoAtual = 0;  // Posição atual no arquivo binário
    
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSV));
             FileOutputStream fos = new FileOutputStream(outputBinary);
             DataOutputStream dos = new DataOutputStream(fos)) {
    
            // Pula a primeira linha (cabeçalho)
            br.readLine();
    
            String line;
            int lastID = 0;
            dos.writeInt(lastID);
            posicaoAtual += Integer.BYTES;  // Incrementa pela escrita de lastID (4 bytes)
    
            while ((line = br.readLine()) != null) {
                byte[] lineBytes;
                Personagem personagem = new Personagem();
                personagem.ler(line);
    
                long posicaoID = posicaoAtual;  // Armazena a posição do ID atual
    
                dos.writeBoolean(false);  // lápide -> false (1 byte)
                posicaoAtual += 1;
    
                int tamanho = getTamanhoPersonagem(personagem);  // Escreve o tamanho do Personagem
                dos.writeInt(tamanho);  // (4 bytes)
                posicaoAtual += Integer.BYTES;

                // Insere o ID e a posição na árvore B
                bTree.insert(idbin, posicaoID);

                dos.writeInt(idbin++);  // Escreve o ID binário (4 bytes)
                posicaoAtual += Integer.BYTES;
    
                // Grava cada campo e atualiza a posição com o número de bytes escritos
                // ID
                dos.writeUTF(personagem.getId());
                posicaoAtual += personagem.getId().getBytes().length + 2;  // `writeUTF` adiciona 2 bytes extras para o comprimento
    
                // Name
                dos.writeUTF(personagem.getName());
                posicaoAtual += personagem.getName().getBytes().length + 2;
    
                // AlternateNames
                dos.writeUTF(personagem.getAlternateNames());
                posicaoAtual += personagem.getAlternateNames().getBytes().length + 2;
    
                // House
                dos.writeUTF(personagem.getHouse());
                posicaoAtual += personagem.getHouse().getBytes().length + 2;
    
                // Ancestry
                dos.writeUTF(personagem.getAncestry());
                posicaoAtual += personagem.getAncestry().getBytes().length + 2;
    
                // Species
                dos.writeUTF(personagem.getSpecies());
                posicaoAtual += personagem.getSpecies().getBytes().length + 2;
    
                // Patronus
                dos.writeUTF(personagem.getPatronus());
                posicaoAtual += personagem.getPatronus().getBytes().length + 2;
    
                // HogwartsStaff
                dos.writeUTF(personagem.getHogwartsStaff());
                posicaoAtual += personagem.getHogwartsStaff().getBytes().length + 2;
    
                // HogwartsStudent
                dos.writeUTF(personagem.getHogwartsStudent());
                posicaoAtual += personagem.getHogwartsStudent().getBytes().length + 2;
    
                // ActorName
                dos.writeUTF(personagem.getActorName());
                posicaoAtual += personagem.getActorName().getBytes().length + 2;
    
                // Alive
                dos.writeUTF(personagem.getAlive());
                posicaoAtual += personagem.getAlive().getBytes().length + 2;
    
                // DateOfBirth
                long timeInMillis = personagem.getDateOfBirth().getTime();
                dos.writeLong(timeInMillis);  // Escreve 8 bytes para a data
                posicaoAtual += Long.BYTES;
    
                // EyeColor
                dos.writeUTF(personagem.getEyeColor());
                posicaoAtual += personagem.getEyeColor().getBytes().length + 2;
    
                // Gender
                dos.writeUTF(personagem.getGender());
                posicaoAtual += personagem.getGender().getBytes().length + 2;
    
                // HairColour
                dos.writeUTF(personagem.getHairColour());
                posicaoAtual += personagem.getHairColour().getBytes().length + 2;
    
                // Wizard
                dos.writeUTF(personagem.getWizard());
                posicaoAtual += personagem.getWizard().getBytes().length + 2;
    
                // YearOfBirth
                dos.writeInt(personagem.getYearOfBirth());  // 4 bytes para o ano de nascimento
                posicaoAtual += Integer.BYTES;
            }
            System.out.println("Arquivo binário criado com sucesso: " + outputBinary);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao processar os arquivos.");
            e.printStackTrace();
        }
        try {
            // Abre o arquivo em modo leitura e escrita
            RandomAccessFile arquivo = new RandomAccessFile("characters.bin", "rwd");

            // Define a posição do cabeçalho (por exemplo, início do arquivo)
            long posicaoCabecalho = 0; // A posição onde o int deve ser escrito

            // Move o ponteiro de leitura/escrita para a posição do cabeçalho
            arquivo.seek(posicaoCabecalho);

            // Escreve o int no cabeçalho
            idbin--;
            arquivo.writeInt(idbin);

            // Fecha o arquivo
            arquivo.close();

            System.out.println("Cabeçalho atualizado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
        

        // COMANDOS CRUD
        Scanner scanner = new Scanner(System.in);
        System.out.println("COMANDOS:\n1- Create\n2- Read\n3- Update\n4- Delete\n5- Ordenar\n FIM para terminar o programa.\n");
        String input = scanner.nextLine();

        while (!input.equals("FIM")) {
            String idString, nome, alternate_names = "[", house, ancestry, species, patronus, hogwartsStaff,
                    hogwartsStudent, actorName, alive, dateOfBirth, yearOfBirth, eyeColor, gender, hairColour,
                    wizard;
            switch (input) {
                case "Create":
                    Personagem personagemNovo = new Personagem();

                    // Recebendo informações do novo personagem

                    System.out.println("Digite o id: "); // idString
                    idString = scanner.nextLine();
                    personagemNovo.setId(idString);

                    System.out.println("Digite o nome: "); // Nome
                    nome = scanner.nextLine();
                    personagemNovo.setName(nome);

                    System.out.println("Digite o alternate_names(Ex: Nome1, Nome2): "); // alternate_Names
                    alternate_names += scanner.nextLine();
                    alternate_names += "]";
                    if (alternate_names.length() >= 1) {
                        alternate_names = alternate_names.replaceAll("'", "");
                        String[] nomesAlternados = alternate_names.split(",");
                        for (int i = 0; i < nomesAlternados.length; i++) {
                            personagemNovo.setAlternateNames(nomesAlternados[i]);
                        }
                    } else {
                        personagemNovo.setAlternateNames("");
                    }

                    System.out.println("Digite o house: "); // House
                    house = scanner.nextLine();
                    personagemNovo.setHouse(house);

                    System.out.println("Digite o ancestry: "); // Ancestry
                    ancestry = scanner.nextLine();
                    personagemNovo.setAncestry(ancestry);

                    System.out.println("Digite o species: "); // Species
                    species = scanner.nextLine();
                    personagemNovo.setSpecies(species);

                    System.out.println("Digite o patronus: "); // Patronus
                    patronus = scanner.nextLine();
                    personagemNovo.setPatronus(patronus);

                    System.out.println("Digite o hogwartsStaff: "); // hogwartsStaff
                    hogwartsStaff = scanner.nextLine();
                    personagemNovo.setHogwartsStaff(hogwartsStaff);

                    System.out.println("Digite o hogwartsStudent: "); // hogwartsStudent
                    hogwartsStudent = scanner.nextLine();
                    personagemNovo.setHogwartsStudent(hogwartsStudent);

                    System.out.println("Digite o actorName: "); // actorName
                    actorName = scanner.nextLine();
                    personagemNovo.setActorName(actorName);

                    System.out.println("Digite o alive: "); // Alive
                    alive = scanner.nextLine();
                    personagemNovo.setAlive(alive);

                    System.out.println("Digite o dateOfBirth(formato: dd-MM-yyyy): "); // dateOfBirth
                    dateOfBirth = scanner.nextLine();
                    personagemNovo.setDateOfBirth(dateOfBirth);

                    System.out.println("Digite o yearOfBirth: "); // yearOfBirth
                    yearOfBirth = scanner.nextLine();
                    personagemNovo.setYearOfBirth(yearOfBirth);

                    System.out.println("Digite o eyeColor: "); // eyeColor
                    eyeColor = scanner.nextLine();
                    personagemNovo.setEyeColor(eyeColor);

                    System.out.println("Digite o gender: "); // gender
                    gender = scanner.nextLine();
                    personagemNovo.setGender(gender);

                    System.out.println("Digite o hairColour: "); // hairColour
                    hairColour = scanner.nextLine();
                    personagemNovo.setHairColour(hairColour);

                    System.out.println("Digite o wizard: "); // wizard
                    wizard = scanner.nextLine();
                    personagemNovo.setWizard(wizard);

                    Create(outputBinary, "new_characters.csv", ++idbin, personagemNovo);
                    try { // Atualiza cabeçalho abre o arquivo em modo leitura e escrita
                        RandomAccessFile arquivo = new RandomAccessFile("characters.bin", "rwd");
                        arquivo.seek(0); // Posiciona o ponteiro no cabeçalho
                        arquivo.writeInt(idbin);// Escreve o int no cabeçalho
                        // Fecha o arquivo
                        arquivo.close();
                        System.out.println("Cabeçalho atualizado com sucesso!");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case "Read":
                    System.out.println("Insira o id do registro a ser lido:");
                    int id = scanner.nextInt(); // idSequencial do personagem a ser lido
                    Read("characters.bin", id);
                    break;
                case "Update":
                    personagemNovo = new Personagem();
                    System.out.println("Insira o id do registro a ser atualizado:");
                    int idAtulizar;
                    idAtulizar = scanner.nextInt();
                    scanner.nextLine();
                    // Recebendo informações do personagem que vai ser atualizado

                    System.out.println("Digite o id: "); // idString
                    idString = scanner.nextLine();
                    personagemNovo.setId(idString);

                    System.out.println("Digite o nome: "); // Nome
                    nome = scanner.nextLine();
                    personagemNovo.setName(nome);

                    System.out.println("Digite o alternate_names(Ex: Nome1, Nome2): "); // alternate_Names
                    alternate_names += scanner.nextLine();
                    alternate_names += "]";
                    if (alternate_names.length() >= 1) {
                        alternate_names = alternate_names.replaceAll("'", "");
                        String[] nomesAlternados = alternate_names.split(",");
                        for (int i = 0; i < nomesAlternados.length; i++) {
                            personagemNovo.setAlternateNames(nomesAlternados[i]);
                        }
                    } else {
                        personagemNovo.setAlternateNames("");
                    }

                    System.out.println("Digite o house: "); // House
                    house = scanner.nextLine();
                    personagemNovo.setHouse(house);

                    System.out.println("Digite o ancestry: "); // Ancestry
                    ancestry = scanner.nextLine();
                    personagemNovo.setAncestry(ancestry);

                    System.out.println("Digite o species: "); // Species
                    species = scanner.nextLine();
                    personagemNovo.setSpecies(species);

                    System.out.println("Digite o patronus: "); // Patronus
                    patronus = scanner.nextLine();
                    personagemNovo.setPatronus(patronus);

                    System.out.println("Digite o hogwartsStaff: "); // hogwartsStaff
                    hogwartsStaff = scanner.nextLine();
                    personagemNovo.setHogwartsStaff(hogwartsStaff);

                    System.out.println("Digite o hogwartsStudent: "); // hogwartsStudent
                    hogwartsStudent = scanner.nextLine();
                    personagemNovo.setHogwartsStudent(hogwartsStudent);

                    System.out.println("Digite o actorName: "); // actorName
                    actorName = scanner.nextLine();
                    personagemNovo.setActorName(actorName);

                    System.out.println("Digite o alive: "); // Alive
                    alive = scanner.nextLine();
                    personagemNovo.setAlive(alive);

                    System.out.println("Digite o dateOfBirth(formato: dd-MM-yyyy): "); // dateOfBirth
                    dateOfBirth = scanner.nextLine();
                    personagemNovo.setDateOfBirth(dateOfBirth);

                    System.out.println("Digite o yearOfBirth: "); // yearOfBirth
                    yearOfBirth = scanner.nextLine();
                    personagemNovo.setYearOfBirth(yearOfBirth);

                    System.out.println("Digite o eyeColor: "); // eyeColor
                    eyeColor = scanner.nextLine();
                    personagemNovo.setEyeColor(eyeColor);

                    System.out.println("Digite o gender: "); // gender
                    gender = scanner.nextLine();
                    personagemNovo.setGender(gender);

                    System.out.println("Digite o hairColour: "); // hairColour
                    hairColour = scanner.nextLine();
                    personagemNovo.setHairColour(hairColour);

                    System.out.println("Digite o wizard: "); // wizard
                    wizard = scanner.nextLine();
                    personagemNovo.setWizard(wizard);

                    Update("characters.bin", idAtulizar, personagemNovo);
                    break;
                case "Delete":
                    System.out.println("Insira o id do registro a ser excluido:");
                    int idExcluir = scanner.nextInt();
                    Delete("characters.bin", idExcluir);
                    break;
                case "Ordenar":
                ordenacaoExterna("characters.bin");
                System.out.println("Arquivo ordenado ordered_characters.bin criado.");
                break;
                default:
                    break;
            }
            input = scanner.nextLine();
        }
        String outputCSV = "new_characters.csv";
        convertBinaryToCSV(outputBinary, outputCSV);
        ordenacaoExterna("characters.bin");
        scanner.close();
        bTree.search(404);
    }// FIM MAIN

    private static void ordenacaoExterna(String inputBinary) {
        String outputBinary = "ordered_characters.bin";
        try (FileInputStream fis = new FileInputStream(inputBinary);
             DataInputStream dis = new DataInputStream(fis);
             FileOutputStream fos = new FileOutputStream(outputBinary);
             DataOutputStream dos = new DataOutputStream(fos)) {
    
            // Lê o último ID inserido
            int ultimoID = dis.readInt();
    
            // Escreve o último ID no arquivo binário de saída
            dos.writeInt(ultimoID);
    
            while (dis.available() > 0) {
                // Verifica se é um registro lápide
                boolean isLapide = dis.readBoolean();
                // Escreve a lápide no arquivo binário de saída
    
                if (isLapide) {
                    int tamanhoLapide = dis.readInt();
                    dis.skipBytes(tamanhoLapide); // Pula os bytes do registro se for lápide
                } else {
                    // Lê o tamanho do registro
                    dos.writeBoolean(isLapide); 
                    int tamanho = dis.readInt();
                    dos.writeInt(tamanho); // Escreve o tamanho no arquivo binário de saída
    
                    // Lê o ID e escreve no arquivo binário de saída
                    int id = dis.readInt();
                    dos.writeInt(id);
    
                    // Lê e escreve o ID do personagem
                    String aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve o nome do personagem
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve os nomes alternativos
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve a casa
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve a ascendência
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve a espécie
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve o patrono
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve se é funcionário de Hogwarts
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve se é estudante de Hogwarts
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve o nome do ator
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve se está vivo
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve a data de nascimento
                    long dateOfBirthMillis = dis.readLong();
                    dos.writeLong(dateOfBirthMillis); // Escreve a data em milissegundos
    
                    // Lê e escreve a cor dos olhos
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve o gênero
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve a cor do cabelo
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve se é bruxo
                    aux = dis.readUTF();
                    dos.writeUTF(aux);
    
                    // Lê e escreve o ano de nascimento
                    int ano = dis.readInt();
                    dos.writeInt(ano);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertBinaryToCSV(String inputBinary, String outputCSV) {
    try (FileInputStream fis = new FileInputStream(inputBinary);
         DataInputStream dis = new DataInputStream(fis);
         BufferedWriter bw = new BufferedWriter(new FileWriter(outputCSV))) {

        // Escreve cabeçalho do CSV
        bw.write("idSequencial;idString;nome;alternate_names;house;ancestry;species;patronus;hogwartsStaff;hogwartsStudent;actorName;alive;dateOfBirth;eyeColor;gender;hairColour;wizard;ano\n");

        // Lê o último ID inserido
        int ultimoID = dis.readInt();
        System.out.println("Último id: " + ultimoID);

        while (dis.available() > 0) {
            String aux;

            // Verifica se é um registro lápide
            boolean isLapide = dis.readBoolean();
            if (isLapide) {
                bw.write('X');
                bw.newLine();
                dis.skipBytes(dis.readInt()); // Pula os bytes do registro se for lápide
            } else {
                // Lê o tamanho do registro
                int tamanho = dis.readInt();
                System.out.println("Tamanho do registro: " + tamanho);

                // Lê o ID
                int id = dis.readInt();
                bw.write(id + ";");

                // Lê e escreve o ID do personagem
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve o nome do personagem
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve os nomes alternativos
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve a casa
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve a ascendência
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve a espécie
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve o patrono
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve se é funcionário de Hogwarts
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve se é estudante de Hogwarts
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve o nome do ator
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve se está vivo
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve a data de nascimento
                long dateOfBirthMillis = dis.readLong();
                Date dateOfBirth = new Date(dateOfBirthMillis);
                bw.write(new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth) + ";");

                // Lê e escreve a cor dos olhos
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve o gênero
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve a cor do cabelo
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve se é bruxo
                aux = dis.readUTF();
                bw.write(aux + ";");

                // Lê e escreve o ano de nascimento
                int ano = dis.readInt();
                bw.write(ano + ";");

                bw.newLine();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    // CREATE
    private static void Create(String inputBinary, String outputCSV, int id, Personagem personagem) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("characters.bin", "rw");

        byte[] lineBytes;

        raf.seek(raf.length()); // posiciona o ponteiro no fim do arquivo
        FileDescriptor fd = raf.getFD();
        FileOutputStream fos = new FileOutputStream(fd);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));

        dos.writeBoolean(false); // lapide -> false-não é lápide / true-é lápide
        int tamanho = getTamanhoPersonagem(personagem); // Escreve o tamanho do Personagem

        dos.writeInt(tamanho);
        dos.writeInt(id);

        // id
        lineBytes = personagem.getId().getBytes(); // transforma ID em um array de bytes
        dos.writeUTF(personagem.getId()); // Escreve o ID

        // name
        lineBytes = personagem.getName().getBytes(); // transforma Name em um array de bytes
        dos.writeUTF(personagem.getName()); // Escreve o Name

        // AlternateNames
        lineBytes = personagem.getAlternateNames().getBytes();
        dos.writeUTF(personagem.getAlternateNames());

        // House
        lineBytes = personagem.getHouse().getBytes();
        dos.writeUTF(personagem.getHouse());

        // Ancestry
        lineBytes = personagem.getAncestry().getBytes();
        dos.writeUTF(personagem.getAncestry());

        // Species
        lineBytes = personagem.getSpecies().getBytes();
        dos.writeUTF(personagem.getSpecies());

        // Patronus
        lineBytes = personagem.getPatronus().getBytes();
        dos.writeUTF(personagem.getPatronus());

        // HogwartsStaff
        lineBytes = personagem.getHogwartsStaff().getBytes();
        dos.writeUTF(personagem.getHogwartsStaff());

        // HogwartsStudent
        lineBytes = personagem.getHogwartsStudent().getBytes();
        dos.writeUTF(personagem.getHogwartsStudent());

        // ActorName
        lineBytes = personagem.getActorName().getBytes();
        dos.writeUTF(personagem.getActorName());

        // Alive
        lineBytes = personagem.getAlive().getBytes();
        dos.writeUTF(personagem.getAlive());

        // DateOfBirth
        // Converte a data para milissegundos desde 1 de janeiro de 1970
        long timeInMillis = personagem.getDateOfBirth().getTime();
        lineBytes = ByteBuffer.allocate(Long.BYTES).putLong(timeInMillis).array();
        dos.write(lineBytes);

        // EyeColor
        lineBytes = personagem.getEyeColor().getBytes();
        dos.writeUTF(personagem.getEyeColor());

        // Gender
        lineBytes = personagem.getGender().getBytes();
        dos.writeUTF(personagem.getGender());

        // HairColour
        lineBytes = personagem.getHairColour().getBytes();
        dos.writeUTF(personagem.getHairColour());

        // Wizard
        lineBytes = personagem.getWizard().getBytes();
        dos.writeUTF(personagem.getWizard());

        // YearOfBirth
        dos.writeInt(personagem.getYearOfBirth());
        dos.close();

        raf.close();
    }

    // READ
    private static void Read(String inputBinary, int id) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputBinary))) {
            // Lê o último ID inserido (não usado neste método, mas segue a estrutura)
            int ultimoID = dis.readInt();
            boolean registroEncontrado = false;
            while (dis.available() > 0) {
                String aux;
                String line = "";
                // Verifica se é um registro lápide
                byte lapide = dis.readByte();
                // Lê o tamanho do registro
                int tamanho = dis.readInt();
                // Lê o ID do registro
                int idAtual = dis.readInt();

                // idString
                int tamanhoDados = dis.readUnsignedShort();// Lê o numero de bytes que o registro ocupa
                byte[] data = new byte[tamanhoDados];//Lê o numero exato de bytes
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // nome
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // alternate_names
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // house
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // ancestry
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // species
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // patronus
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // hogwartsStaff
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // hogwartsStudent
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // actorName
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // alive
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // dateOfBirth
                //transforma data em string para facilitar a leitura no printf
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date dataNascimento = new Date(dis.readLong());
                aux = sdf.format(dataNascimento);
                line += aux;
                line += ";";

                // eyeColor
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // gender
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // hairColour
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;
                line += ";";

                // wizard
                tamanhoDados = dis.readUnsignedShort();
                data = new byte[tamanhoDados];
                dis.readFully(data);
                aux = new String(data, StandardCharsets.UTF_8);
                line += aux;

                // Lê o ano (últimos 4 bytes do registro, pois são armazenados como int)
                int ano = dis.readInt();

                if (lapide == 0 && idAtual == id) {
                    // Converte os dados lidos em string usando UTF-8
                    System.out.println("Registro encontrado: " + line + "; AnoNascimento: " + ano);
                    registroEncontrado = true; // Marca que o registro foi encontrado
                    dis.close();
                    break; // Saia do loop se o registro for encontrado
                }
            }
            dis.close();
            if (!registroEncontrado) {
                System.out.println("Registro não encontrado.");
            }
        } catch (EOFException e) {
            System.err.println("Fim do arquivo alcançado inesperadamente: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    private static void Update(String inputBinary, int id, Personagem personagemNovo) throws IOException {
        int tamanhoPersonagemNovo = 0;
        try (RandomAccessFile raf = new RandomAccessFile(inputBinary, "rw")) {// ; Abre para leitura e escrita
            // Lê o ultimo ID
            raf.readInt();

            long posicaoAtual = raf.getFilePointer();
            boolean registroEncontrado = false;

            while (posicaoAtual < raf.length()) {
                byte lapide = raf.readByte();
                int tamanho = raf.readInt();
                int idAtual = raf.readInt();
                String aux;

                // Salva a posição da lapide
                long posLapide = raf.getFilePointer() - 9;

                if (lapide == 0 && idAtual == id) {
                    
                    tamanhoPersonagemNovo = getTamanhoPersonagem(personagemNovo); // Ver tamanho do personagem
                                                                                  // atualizado
                    if (tamanhoPersonagemNovo > tamanho || tamanhoPersonagemNovo < tamanho) { // Se personagem
                                                                                              // atualizado > personagem
                                                                                              // desatualizado

                        // Posiciona o ponteiro no início da lápide e escreve 1
                        raf.seek(posLapide);
                        raf.writeByte(1);

                        Create(inputBinary, "new_characters.csv", id, personagemNovo);// Cria um novo personagem
                        raf.seek(raf.length());
                        registroEncontrado = true;

                        System.out.println("Atualizado com sucesso.");
                        raf.close();
                        break; // Sai do loop após encontrar e marcar o registro
                    } else if (tamanhoPersonagemNovo == tamanho) {// Se personagem atualizado <= personagem
                                                                  // desatualizado
                                                                  // Marca o registro antigo como lápide e adiciona o
                                                                  // novo ao final
                        registroEncontrado = true;
                        raf.seek(posLapide);
                        
                        raf.writeByte(0); // Marca como lápide
                        
                        raf.writeInt(tamanhoPersonagemNovo);
                        raf.writeInt(id); // Assumindo que você tem um método para obter o ID como int

                        raf.writeUTF(personagemNovo.getId());
                        raf.writeUTF(personagemNovo.getName());
                        raf.writeUTF(personagemNovo.getAlternateNames());
                        raf.writeUTF(personagemNovo.getHouse());
                        raf.writeUTF(personagemNovo.getAncestry());
                        raf.writeUTF(personagemNovo.getSpecies());
                        raf.writeUTF(personagemNovo.getPatronus());
                        raf.writeUTF(personagemNovo.getHogwartsStaff());
                        raf.writeUTF(personagemNovo.getHogwartsStudent());
                        raf.writeUTF(personagemNovo.getActorName());
                        raf.writeUTF(personagemNovo.getAlive());

                        // DataOfBirth
                        long timeInMillis = personagemNovo.getDateOfBirth().getTime();
                        raf.writeLong(timeInMillis);

                        raf.writeUTF(personagemNovo.getEyeColor());
                        raf.writeUTF(personagemNovo.getGender());
                        raf.writeUTF(personagemNovo.getHairColour());
                        raf.writeUTF(personagemNovo.getWizard());
                        raf.writeInt(personagemNovo.getYearOfBirth());

                        System.out.println("Registro substituído por um novo.");
                        break;
                    }
                }
                // Pular para o próximo registro
                posicaoAtual = posLapide + 1;
                raf.seek(posicaoAtual);
            }

            if (!registroEncontrado) {
                System.out.println("Registro não encontrado.");
            }

        } catch (EOFException e) {
            System.err.println("Fim do arquivo alcançado inesperadamente: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // DELETE
    private static void Delete(String inputBinary, int id) {
        try (RandomAccessFile raf = new RandomAccessFile(inputBinary, "rw")) {// ; Abre para leitura e escrita
            // Lê o ultimo ID
            raf.readInt();

            long posicaoAtual = raf.getFilePointer();
            boolean registroEncontrado = false;

            while (posicaoAtual < raf.length()) {
                byte lapide = raf.readByte();
                int tamanho = raf.readInt();
                int idAtual = raf.readInt();

                // Salva a posição da lapide
                long posLapide = raf.getFilePointer() - 9;
                if (lapide == 0 && idAtual == id) {

                    // Posiciona o ponteiro no início da lápide e escreve 1
                    raf.seek(posLapide);
                    raf.writeByte(1);

                    registroEncontrado = true;

                    System.out.println("Registro excluido.");
                    break; // Sai do loop após encontrar e marcar o registro
                }
                // Pular para o próximo registro
                posicaoAtual = posLapide + 1;
                raf.seek(posicaoAtual);
            }
            raf.close();
            if (!registroEncontrado) {
                System.out.println("Registro não encontrado.");
            }

        } catch (EOFException e) {
            System.err.println("Fim do arquivo alcançado inesperadamente: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Conta quantos bytes o personagem vai usar
    public static int getTamanhoPersonagem(Personagem personagem) throws IOException {
        // Inicializa o tamanho com o tamanho do campo ID
        int tamanho = 4;

        // Calcula o tamanho de cada campo em bytes, usando UTF-8 explicitamente
        byte[] lineBytes = personagem.getId().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getName().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getAlternateNames().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHouse().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getAncestry().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getSpecies().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getPatronus().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHogwartsStaff().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHogwartsStudent().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getActorName().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getAlive().getBytes();
        tamanho += lineBytes.length + 2;

        tamanho += 8; // dateOfBirth

        tamanho += 4; // yearOfBirth

        lineBytes = personagem.getEyeColor().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getGender().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getHairColour().getBytes();
        tamanho += lineBytes.length + 2;

        lineBytes = personagem.getWizard().getBytes();
        tamanho += lineBytes.length + 2;

        System.out.println("Tamanho: " + tamanho);
        return tamanho;
    }
private static void escreverRegistro(RandomAccessFile raf, Personagem personagem,int id) throws IOException {
    // Escreve um novo registro no arquivo
    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(raf.getFD())));
    
    dos.writeBoolean(false); // lápide
    int tamanho = getTamanhoPersonagem(personagem);
    dos.writeInt(tamanho);
    dos.writeInt(id); 
    
    dos.writeUTF(personagem.getId());
    dos.writeUTF(personagem.getName());
    dos.writeUTF(personagem.getAlternateNames());
    dos.writeUTF(personagem.getHouse());
    dos.writeUTF(personagem.getAncestry());
    dos.writeUTF(personagem.getSpecies());
    dos.writeUTF(personagem.getPatronus());
    dos.writeUTF(personagem.getHogwartsStaff());
    dos.writeUTF(personagem.getHogwartsStudent());
    dos.writeUTF(personagem.getActorName());
    dos.writeUTF(personagem.getAlive());
    
    // DataOfBirth
    long timeInMillis = personagem.getDateOfBirth().getTime();
    dos.writeLong(timeInMillis);
    
    dos.writeUTF(personagem.getEyeColor());
    dos.writeUTF(personagem.getGender());
    dos.writeUTF(personagem.getHairColour());
    dos.writeUTF(personagem.getWizard());
    dos.writeInt(personagem.getYearOfBirth());
    
    dos.flush();
    dos.close();
}

    static class Personagem {
        private String id;
        private String name;
        private List<String> alternate_names = new ArrayList<String>();
        private String house;
        private String ancestry;
        private String species;
        private String patronus;
        private String hogwartsStaff;
        private String hogwartsStudent;
        private String actorName;
        private String alive;
        private Date dateOfBirth;
        private int yearOfBirth;
        private String eyeColor;
        private String gender;
        private String hairColour;
        private String wizard;

        // --------------------------------------------------Classe_Personagem--------------------------------------------------

        // Contrutor
        public Personagem() {
            id = "";
            name = "";
            alternate_names = new ArrayList<String>();
            house = "";
            ancestry = "";
            species = "";
            patronus = "";
            hogwartsStaff = "";
            hogwartsStudent = "";
            actorName = "";
            alive = "";
            dateOfBirth = new Date();
            yearOfBirth = 0;
            eyeColor = "";
            gender = "";
            hairColour = "";
            wizard = "";
        }

        // Contrutor
        public Personagem(String id, String name, ArrayList<String> alternate_names, String house, String ancestry,
                String species,
                String patronus, String hogwartsStaff,
                String hogwartsStudent, String actorName, String alive, Date dateOfBirth, int yearOfBirth,
                String eyeColor, String gender, String hairColour, String wizard) {
            this.id = id;
            this.name = name;
            this.alternate_names = new ArrayList<String>();
            for (String temp : alternate_names) {
                this.alternate_names.add(temp);
            }
            this.house = house;
            this.ancestry = ancestry;
            this.species = species;
            this.patronus = patronus;
            this.hogwartsStaff = hogwartsStaff;
            this.hogwartsStudent = hogwartsStudent;
            this.actorName = actorName;
            this.alive = alive;
            this.dateOfBirth = new Date();
            this.yearOfBirth = yearOfBirth;
            this.eyeColor = eyeColor;
            this.gender = gender;
            this.hairColour = hairColour;
            this.wizard = wizard;
        }

        // getters
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAlternateNames() {
            StringBuilder sb = new StringBuilder();
            for (String name : alternate_names) {
                sb.append(name).append(", ");
            }
            // Remover a última vírgula e espaço, se houver
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 2);
            }

            return sb.toString();
        }

        public String getHouse() {
            return house;
        }

        public String getAncestry() {
            return ancestry;
        }

        public String getSpecies() {
            return species;
        }

        public String getPatronus() {
            return patronus;
        }

        public String getHogwartsStaff() {
            return hogwartsStaff;
        }

        public String getHogwartsStudent() {
            return hogwartsStudent;
        }

        public String getActorName() {
            return actorName;
        }

        public String getAlive() {
            return alive;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public String getEyeColor() {
            return eyeColor;
        }

        public String getGender() {
            return gender;
        }

        public String getHairColour() {
            return hairColour;
        }

        public String getWizard() {
            return wizard;
        }

        // Setters
        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            if (name.equals(" ")) {
                name = "";
            }
            this.name = name;
        }

        public void setAlternateNames(String nomesAlterados) {
            if (nomesAlterados.charAt(0) == ' ') {
                StringBuilder nomesAlteradosNovo = new StringBuilder();
                for (int i = 1; i < nomesAlterados.length(); i++) {
                    nomesAlteradosNovo.append(nomesAlterados.charAt(i));
                }
                String x = nomesAlteradosNovo.toString();
                this.alternate_names.add(x);
            } else {
                this.alternate_names.add(nomesAlterados);
            }
        }

        public void setHouse(String house) {
            if (house.equals(" ")) {
                house = "";
            }
            this.house = house;
        }

        public void setAncestry(String ancestry) {
            if (ancestry.equals(" ")) {
                ancestry = "";
            }
            this.ancestry = ancestry;
        }

        public void setSpecies(String species) {
            if (species.equals(" ")) {
                species = "";
            }
            this.species = species;
        }

        public void setPatronus(String patronus) {
            if (patronus.equals(" ")) {
                patronus = "";
            }
            this.patronus = patronus;
        }

        public void setHogwartsStaff(String hogwartsStaff) {
            if (hogwartsStaff.equals(" ")) {
                hogwartsStaff = "";
            }
            this.hogwartsStaff = hogwartsStaff;
        }

        public void setHogwartsStudent(String hogwartsStudent) {
            if (hogwartsStudent.equals(" ")) {
                hogwartsStudent = "";
            }
            this.hogwartsStudent = hogwartsStudent;
        }

        public void setActorName(String actorName) {
            if (actorName.equals(" ")) {
                actorName = "";
            }
            this.actorName = actorName;
        }

        public void setAlive(String alive) {
            if (alive.equals(" ")) {
                alive = "";
            }
            this.alive = alive;
        }

        public void setDateOfBirth(String dateOfBirth) {
            if (dateOfBirth.equals(" ")) {
                dateOfBirth = "";
            }
            String[] partes = dateOfBirth.split("-");
            StringBuilder temp = new StringBuilder();

            if (partes[0].length() < 2) {
                temp.append("0");
                temp.append(partes[0] + "-");
            } else {
                temp.append(partes[0] + "-");
            }
            if (partes[1].length() < 2) {
                temp.append("0");
                temp.append(partes[1] + "-");
            } else {
                temp.append(partes[1] + "-");
            }
            temp.append(partes[2]);
            String tempString = temp.toString();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            try {
                this.dateOfBirth = formato.parse(tempString);
            } catch (ParseException e) {
                System.err.println("Erro ao inserir data nascimento.");
            }
        }

        public void setYearOfBirth(String yearOfBirth) {
            int anoNascimento = 0;
            try {
                anoNascimento = Integer.parseInt(yearOfBirth);
                this.yearOfBirth = anoNascimento;
            } catch (Exception e) {
                this.yearOfBirth = 0;
            }
        }

        public void setEyeColor(String eyeColor) {
            if (eyeColor.equals(" ")) {
                eyeColor = "";
            }
            this.eyeColor = eyeColor;
        }

        public void setGender(String gender) {
            if (gender.equals(" ")) {
                gender = "";
            }
            this.gender = gender;
        }

        public void setHairColour(String hairColour) {
            if (hairColour.equals(" ")) {
                hairColour = "";
            }
            this.hairColour = hairColour;
        }

        public void setWizard(String wizard) {
            this.wizard = wizard;
        }

        public void ler(String x) {
            // 0 = id; 1 = name; 2 = alternate_names; 3 = house;
            // 4 = ancestry; 5 = species; 6 = patronus; 7 = hogwartsStaff;
            // 8 = hogwartsStudent; 9 = actorName; 10 = alive; 12 = dateOfBirth;
            // 13 = yearOfBirth; 14 = eyeColor; 15 = gender; 16 = hairColour;
            // 17 = wizard;

            String[] partes = x.split(";");
            setId(partes[0]);
            setName(partes[1]);
            if (partes[2].length() >= 1) {
                partes[2] = partes[2].replaceAll("'", "");
                String[] nomesAlternados = partes[2].split(",");
                for (int i = 0; i < nomesAlternados.length; i++) {
                    setAlternateNames(nomesAlternados[i]);
                }
            } else {
                setAlternateNames("");
            }
            setHouse(partes[3]);
            setAncestry(partes[4]);
            setSpecies(partes[5]);
            setPatronus(partes[6]);
            if (partes[7].equals("FALSO")) {
                setHogwartsStaff("false");
            } else if (partes[7].equals("VERDADEIRO")) {
                setHogwartsStaff("true");
            }
            if (partes[8].equals("FALSO")) {
                setHogwartsStudent("false");
            } else if (partes[8].equals("VERDADEIRO")) {
                setHogwartsStudent("true");
            }
            setActorName(partes[9]);
            if (partes[10].equals("FALSO")) {
                setAlive("false");
            } else if (partes[10].equals("VERDADEIRO")) {
                setAlive("true");
            }
            setDateOfBirth(partes[12]);
            setYearOfBirth(partes[13]);
            setEyeColor(partes[14]);
            setGender(partes[15]);
            setHairColour(partes[16]);
            if (partes[17].equals("FALSO")) {
                setWizard("false");
            } else if (partes[17].equals("VERDADEIRO")) {
                setWizard("true");
            }
        }
    }
    // ARVORE B
    static class BTreeNode {
        int[] keys;
        int t;  // Ordem da árvore B
        BTreeNode[] children;
        int numKeys;
        boolean isLeaf;
    
        public BTreeNode(int t, boolean isLeaf) {
            this.t = t;
            this.isLeaf = isLeaf;
            this.keys = new int[2 * t - 1];
            this.children = new BTreeNode[2 * t];
            this.numKeys = 0;
        }
    }
    static class BTree {
        BTreeNode root;
        int t;  // Ordem mínima da árvore B
    
        public BTree(int t) {
            this.root = null;
            this.t = t;
        }
    
        public void insert(int key, long position) {
            // Se a árvore estiver vazia, cria a raiz
            if (root == null) {
                root = new BTreeNode(t, true);
                root.keys[0] = key;
                root.numKeys = 1;
            } else {
                // Se a raiz estiver cheia, precisamos dividir
                if (root.numKeys == 2 * t - 1) {
                    BTreeNode newRoot = new BTreeNode(t, false);
                    newRoot.children[0] = root;
                    splitChild(newRoot, 0, root);
                    root = newRoot;
                }
                insertNonFull(root, key, position);
            }
        }
    
        private void splitChild(BTreeNode parent, int i, BTreeNode fullChild) {
            BTreeNode newNode = new BTreeNode(fullChild.t, fullChild.isLeaf);
            newNode.numKeys = t - 1;
            
            for (int j = 0; j < t - 1; j++)
                newNode.keys[j] = fullChild.keys[j + t];
            
            if (!fullChild.isLeaf) {
                for (int j = 0; j < t; j++)
                    newNode.children[j] = fullChild.children[j + t];
            }
            
            fullChild.numKeys = t - 1;
            
            for (int j = parent.numKeys; j >= i + 1; j--)
                parent.children[j + 1] = parent.children[j];
            
            parent.children[i + 1] = newNode;
            
            for (int j = parent.numKeys - 1; j >= i; j--)
                parent.keys[j + 1] = parent.keys[j];
            
            parent.keys[i] = fullChild.keys[t - 1];
            parent.numKeys++;
        }
    
        private void insertNonFull(BTreeNode node, int key, long position) {
            int i = node.numKeys - 1;
            if (node.isLeaf) {
                while (i >= 0 && key < node.keys[i]) {
                    node.keys[i + 1] = node.keys[i];
                    i--;
                }
                node.keys[i + 1] = key;
                node.numKeys++;
            } else {
                while (i >= 0 && key < node.keys[i])
                    i--;
                if (node.children[i + 1].numKeys == 2 * t - 1) {
                    splitChild(node, i + 1, node.children[i + 1]);
                    if (key > node.keys[i + 1])
                        i++;
                }
                insertNonFull(node.children[i + 1], key, position);
            }
        }
    
        public void search(int key) {
            searchNode(root, key);
        }
    
        private void searchNode(BTreeNode node, int key) {
            int i = 0;
            while (i < node.numKeys && key > node.keys[i])
                i++;
            
            if (i < node.numKeys && key == node.keys[i])
                System.out.println("Key found at position: " + i);
            else if (node.isLeaf)
                System.out.println("Key not found.");
            else
                searchNode(node.children[i], key);
        }
    }
        
}