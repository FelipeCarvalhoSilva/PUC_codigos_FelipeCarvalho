import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.Normalizer;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputCSV = "characters.csv";
        String outputBinary = "characters.bin";
        int idbin = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSV));
                FileOutputStream fos = new FileOutputStream(outputBinary);
                DataOutputStream dos = new DataOutputStream(fos)) {

            // Pula a primeira linha (cabeçalho)
            br.readLine();

            String line;
            int lastID = 0;
            dos.writeInt(lastID);
            while ((line = br.readLine()) != null) {
                byte[] lineBytes;
                byte[] lineBytesAux;
                Personagem personagem = new Personagem();

                personagem.ler(line);

                dos.writeBoolean(false); // lapide -> false-não é lápide / true-é lápide
                int tamanho = getTamanhoPersonagem(personagem); // Escreve o tamanho do Personagem
                dos.writeInt(tamanho);
                dos.writeInt(idbin++);

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
        System.out.println("COMANDOS:\n1- Create\n2- Read\n3- Update\n4- Delete\n FIM para terminar o programa.\n");
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
                default:
                    break;
            }
            input = scanner.nextLine();
        }
        String outputCSV = "new_characters.csv";
        convertBinaryToCSV(outputBinary, outputCSV);
        scanner.close();
    }// FIM MAIN

     // Ordenação Externa
     private static void ordenacaoExterna(String inputBinary){
        String inputCSV = "characters.csv";
        String outputBinary = "ordered_characters.bin";
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSV));
                FileOutputStream fos = new FileOutputStream(outputBinary);
                DataOutputStream dos = new DataOutputStream(fos)){

                }catch (IOException e) {
                    System.out.println("Ocorreu um erro ao processar os arquivos.");
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
                    if (tamanhoPersonagemNovo > tamanho) { // Se personagem atualizado > personagem desatualizado

                        // Posiciona o ponteiro no início da lápide e escreve 1
                        raf.seek(posLapide);
                        raf.writeByte(1);

                        Create(inputBinary, "new_characters.csv", id, personagemNovo);// Cria um novo personagem
                        raf.seek(raf.length());
                        registroEncontrado = true;

                        System.out.println("Atualizado com sucesso.");
                        raf.close();
                        break; // Sai do loop após encontrar e marcar o registro
                    } else if (tamanhoPersonagemNovo <= tamanho) {// Se personagem atualizado <= personagem
                                                                  // desatualizado
                        byte[] lineBytes;
                        posicaoAtual = posLapide + 9;
                        String idString, nome, alternate_names, house, ancestry, species, patronus, hogwartsStaff,hogwartsStudent, actorName, alive;
                        long dateMili;
                        Date dateOfBirth;
                        String eyeColor, gender, hairColour, wizard;
                        int yearOfBirth;

                        posicaoAtual = raf.getFilePointer();
                        // id
                        int tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        idString=new String(lineBytes, "UTF-8");                        
                        
                        if (idString.length() > personagemNovo.getId().length()
                                && idString.compareTo(personagemNovo.getId()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (idString.length() <= personagemNovo.getId().length()
                                && idString.compareTo(personagemNovo.getId()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(idString);
                            dos.close();
                            break;
                        }
                        
                        posicaoAtual = raf.getFilePointer();
                        // Name
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        nome=new String(lineBytes, "UTF-8");                        
                        
                        if (nome.length() > personagemNovo.getName().length()
                                && nome.compareTo(personagemNovo.getName()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (nome.length() <= personagemNovo.getName().length()
                                && nome.compareTo(personagemNovo.getName()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(nome);
                            dos.close();
                            break;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // AlternateNames
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        alternate_names=new String(lineBytes, "UTF-8");                        
                        
                        if (alternate_names.length() > personagemNovo.getAlternateNames().length()
                                && alternate_names.compareTo(personagemNovo.getAlternateNames()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (alternate_names.length() <= personagemNovo.getAlternateNames().length()
                                && alternate_names.compareTo(personagemNovo.getAlternateNames()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(alternate_names);
                            dos.close();
                            break;
                        }
                        
                        posicaoAtual = raf.getFilePointer();
                        // House
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        house=new String(lineBytes, "UTF-8");                        
                        
                        if (house.length() > personagemNovo.getHouse().length()
                                && house.compareTo(personagemNovo.getHouse()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (house.length() <= personagemNovo.getHouse().length()
                                && house.compareTo(personagemNovo.getHouse()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(house);
                            dos.close();
                            break;
                        }
                        
                        posicaoAtual = raf.getFilePointer();
                        // Ancestry
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        ancestry=new String(lineBytes, "UTF-8");                        
                        
                        if (ancestry.length() > personagemNovo.getAncestry().length()
                                && ancestry.compareTo(personagemNovo.getAncestry()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (ancestry.length() <= personagemNovo.getAncestry().length()
                                && ancestry.compareTo(personagemNovo.getAncestry()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(ancestry);
                            dos.close();
                            break;
                        }
                        
                        posicaoAtual = raf.getFilePointer();
                        // Species
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        species=new String(lineBytes, "UTF-8");                        
                        
                        if (species.length() > personagemNovo.getSpecies().length()
                                && species.compareTo(personagemNovo.getSpecies()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (species.length() <= personagemNovo.getSpecies().length()
                                && species.compareTo(personagemNovo.getSpecies()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(species);
                            dos.close();
                            break;
                        }
                        
                        posicaoAtual = raf.getFilePointer();
                        // Patronus
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        patronus=new String(lineBytes, "UTF-8");                        
                        
                        if (patronus.length() > personagemNovo.getPatronus().length()
                                && patronus.compareTo(personagemNovo.getPatronus()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (patronus.length() <= personagemNovo.getPatronus().length()
                                && patronus.compareTo(personagemNovo.getPatronus()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(patronus);
                            dos.close();
                            break;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // HogwartsStaff
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        hogwartsStaff=new String(lineBytes, "UTF-8");                        
                        
                        if (hogwartsStaff.length() > personagemNovo.getHogwartsStaff().length()
                                && hogwartsStaff.compareTo(personagemNovo.getHogwartsStaff()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (hogwartsStaff.length() <= personagemNovo.getHogwartsStaff().length()
                                && hogwartsStaff.compareTo(personagemNovo.getHogwartsStaff()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(hogwartsStaff);
                            dos.close();
                            break;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // HogwartsStudent
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        hogwartsStudent=new String(lineBytes, "UTF-8");                        
                        
                        if (hogwartsStudent.length() > personagemNovo.getHogwartsStudent().length()
                                && hogwartsStudent.compareTo(personagemNovo.getHogwartsStudent()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (hogwartsStudent.length() <= personagemNovo.getHogwartsStudent().length()
                                && hogwartsStudent.compareTo(personagemNovo.getHogwartsStudent()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(hogwartsStudent);
                            dos.close();
                            break;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // ActorName
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        actorName=new String(lineBytes, "UTF-8");                        
                        
                        if (actorName.length() > personagemNovo.getActorName().length()
                                && actorName.compareTo(personagemNovo.getActorName()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (actorName.length() <= personagemNovo.getActorName().length()
                                && actorName.compareTo(personagemNovo.getActorName()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(actorName);
                            dos.close();
                            break;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // Alive
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        alive=new String(lineBytes, "UTF-8");                        
                        
                        if (alive.length() > personagemNovo.getAlive().length()
                                && alive.compareTo(personagemNovo.getAlive()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (alive.length() <= personagemNovo.getAlive().length()
                                && alive.compareTo(personagemNovo.getAlive()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(alive);
                            dos.close();
                            break;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // dateOfBirth
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        dateMili = raf.readLong();
                        dateOfBirth = new Date(dateMili);

                        // Convertendo data pra string para facilitar comparacao
                        aux = sdf.format(dateOfBirth);
                        String aux1=sdf.format(personagemNovo.getDateOfBirth());
                        if (aux.compareTo(aux1) != 0) {
                            raf.writeLong(dateMili);;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // EyeColor
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        eyeColor=new String(lineBytes, "UTF-8");                        
                        
                        if (eyeColor.length() > personagemNovo.getEyeColor().length()
                                && eyeColor.compareTo(personagemNovo.getEyeColor()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (eyeColor.length() <= personagemNovo.getEyeColor().length()
                                && eyeColor.compareTo(personagemNovo.getEyeColor()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(eyeColor);
                            dos.close();
                            break;
                        }

                        posicaoAtual = raf.getFilePointer();
                        // Gender
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        gender=new String(lineBytes, "UTF-8");                        
                        
                        if (gender.length() > personagemNovo.getGender().length()
                                && gender.compareTo(personagemNovo.getGender()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (gender.length() <= personagemNovo.getGender().length()
                                && gender.compareTo(personagemNovo.getGender()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(gender);
                            dos.close();
                            break;
                        }
                        
                        posicaoAtual = raf.getFilePointer();
                        // HairColour
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        hairColour=new String(lineBytes, "UTF-8");                        
                        
                        if (hairColour.length() > personagemNovo.getHairColour().length()
                                && hairColour.compareTo(personagemNovo.getHairColour()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (hairColour.length() <= personagemNovo.getHairColour().length()
                                && hairColour.compareTo(personagemNovo.getHairColour()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(hairColour);
                            dos.close();
                            break;
                        }
                        
                        posicaoAtual = raf.getFilePointer();
                        // Wizard
                        tamanhoDados = raf.readUnsignedShort();  // lê dois bytes e retorna um int

                        // lê o numero de bytes do readUnsignedShort
                        lineBytes = new byte[tamanhoDados]; 
                        raf.readFully(lineBytes); 
                        wizard=new String(lineBytes, "UTF-8");                        
                        
                        if (wizard.length() > personagemNovo.getWizard().length()
                                && wizard.compareTo(personagemNovo.getWizard()) != 0) {
                            raf.seek(posLapide);
                            raf.writeByte(1);
                            Create(inputBinary, "new_characters.csv", id, personagemNovo);
                            break;
                        } else if (wizard.length() <= personagemNovo.getWizard().length()
                                && wizard.compareTo(personagemNovo.getWizard()) != 0) {
                            raf.seek(posicaoAtual);                            
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeUTF(wizard);
                            dos.close();
                            break;
                        }

                        yearOfBirth = raf.readInt();
                        if(yearOfBirth!=personagemNovo.getYearOfBirth()){
                            FileDescriptor fd = raf.getFD();
                            FileOutputStream fos = new FileOutputStream(fd);
                            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
                            dos.writeInt(yearOfBirth);
                            dos.close();
                            break;
                        }

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
}