import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputCSV = "characters.csv";
        String outputBinary = "characters.bin";
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSV));
                FileOutputStream fos = new FileOutputStream(outputBinary);
                DataOutputStream dos = new DataOutputStream(fos)) {
            // Pula a primeira linha (cabeçalho)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {

                byte[] lineBytes;
                Personagem personagem = new Personagem();
                line = line.replaceAll(";;", "; ;").replaceAll("\\[", "{").replaceAll("]", "}");
                personagem.ler(line);

                dos.writeByte(0); // lapide -> 0 == byte vazio e 47 == '/'
                int tamanho = getTamanhoPersonagem(personagem); // Escreve o tamanho do Personagem
                dos.writeInt(tamanho);
                
                // id
                lineBytes = personagem.getId().getBytes("UTF-8");
                dos.write(lineBytes); // Escreve o ID

                // name
                lineBytes = personagem.getName().getBytes("UTF-8");
                dos.write(lineBytes); // Escreve o Nome

                // AlternateNames
                lineBytes = personagem.getAlternateNames().getBytes("UTF-8");
                dos.write(lineBytes); // Escreve o alternateNames em bytes

                // House
                lineBytes = personagem.getHouse().getBytes("UTF-8");
                dos.write(lineBytes);

                // Ancestry
                lineBytes = personagem.getAncestry().getBytes("UTF-8");
                dos.write(lineBytes);

                // Species
                lineBytes = personagem.getSpecies().getBytes("UTF-8");
                dos.write(lineBytes);

                // Patronus
                lineBytes = personagem.getPatronus().getBytes("UTF-8");
                dos.write(lineBytes);

                // HogwartsStaff
                lineBytes = personagem.getHogwartsStaff().getBytes("UTF-8");
                dos.write(lineBytes);

                // HogwartsStudent
                lineBytes = personagem.getHogwartsStudent().getBytes("UTF-8");
                dos.write(lineBytes);

                // ActorName
                lineBytes = personagem.getActorName().getBytes("UTF-8");
                dos.write(lineBytes);

                // Alive
                lineBytes = personagem.getAlive().getBytes("UTF-8");
                dos.write(lineBytes);

                // DateOfBirth
                lineBytes = personagem.getDateOfBirth().getBytes("UTF-8");
                dos.write(lineBytes);

                // YearOfBirth - transforma em string para escrever no arquivo
                String formatString = Integer.toString(personagem.getYearOfBirth());
                lineBytes = formatString.getBytes();
                dos.writeInt(personagem.getYearOfBirth());

                // EyeColor
                lineBytes = personagem.getEyeColor().getBytes("UTF-8");
                dos.write(lineBytes);

                // Gender
                lineBytes = personagem.getGender().getBytes("UTF-8");
                dos.write(lineBytes);

                // HairColour
                lineBytes = personagem.getHairColour().getBytes("UTF-8");
                dos.write(lineBytes);

                // Wizard
                lineBytes = personagem.getWizard().getBytes("UTF-8");
                dos.write(lineBytes);

            }
            System.out.println("Arquivo binário criado com sucesso: " + outputBinary);

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao processar os arquivos.");
            e.printStackTrace();
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("COMANDOS:\n1- Create\n2- Read\n3- Update\n4- Delete FIM para terminar o programa.\n");
        String input=scanner.nextLine();
        //COMANDOS
        while(!input.equals("FIM")){
            
            switch (input) {
                case "Create":
                    
                    break;
                case "Read":
                    
                    break;
                case "Update":
                    
                    break;
                case "Delete":
                    
                    break;
            
                default:
                    break;
            }
            input=scanner.nextLine();
        }
        String outputCSV = "new_characters.csv";
        convertBinaryToCSV(outputBinary, outputCSV);
        scanner.close();
    }
    private static void convertBinaryToCSV(String inputBinary, String outputCSV) {
        try (FileInputStream fis = new FileInputStream(inputBinary);
             DataInputStream dis = new DataInputStream(fis);
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputCSV))) {

            // Escreve cabeçalho do CSV
            bw.write("id;name;alternate_names;house;ancestry;species;patronus;hogwartsStaff;hogwartsStudent;actorName;alive;dateOfBirth;yearOfBirth;eyeColor;gender;hairColour;wizard\n");

            while (dis.available() > 0) {
                dis.readByte(); // lê lápide
                int tamanho = dis.readInt(); // lê o tamanho do registro

                byte[] data = new byte[tamanho];
                dis.readFully(data);
                String linha = new String(data, "UTF-8");
                bw.write(linha);
                bw.newLine();
            }

            System.out.println("Arquivo CSV criado com sucesso: " + outputCSV);

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo binário.");
            e.printStackTrace();
        }
    }
    // Conta quantos bytes o personagem vai usar
    static int getTamanhoPersonagem(Personagem personagem) throws IOException {
        int tamanho = 4;
        byte[] lineBytes = personagem.getId().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getName().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getAlternateNames().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getHouse().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getAncestry().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getSpecies().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getPatronus().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getHogwartsStaff().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getHogwartsStudent().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getActorName().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getAlive().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getDateOfBirth().getBytes();
        tamanho += lineBytes.length + 4;
        String formatString = Integer.toString(personagem.getYearOfBirth());
        lineBytes = formatString.getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getEyeColor().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getGender().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getHairColour().getBytes();
        tamanho += lineBytes.length + 4;
        lineBytes = personagem.getWizard().getBytes();
        tamanho += lineBytes.length + 4;
        return (tamanho);
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

        public String getDateOfBirth() {
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd-MM-yyyy");
            String dataFormatada = formatoSaida.format(dateOfBirth);
            return dataFormatada;
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
