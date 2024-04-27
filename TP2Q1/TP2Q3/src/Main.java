import java.io.File;
import java.util.Date;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {

    public static void main(String[] args) throws Exception {
        String nomeArquivo = "/tmp/characters.csv";
        Scanner scanner = new Scanner(new File(nomeArquivo));
        scanner.nextLine(); // Ignorar o cabeçalho

        String input = "";

        Personagem[] personagemArray = new Personagem[1000];
        int i = 0;

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(";");
            Personagem personagem = new Personagem();
            personagem.setId(dados[0]);
            personagem.setName(dados[1]);
            personagem.setAlternateNames(dados[2]);
            personagem.setHouse(dados[3]);
            personagem.setAncestry(dados[4]);
            personagem.setSpecies(dados[5]);
            personagem.setPatronus(dados[6]);
            personagem.setHogwartsStaff(dados[7]);
            personagem.setHogwartsStudent(dados[8]);
            personagem.setActorName(dados[9]);
            personagem.setAlive(dados[10]);
            personagem.setAlternateActors(dados[11]);
            personagem.setBirthDate(converterParaData(dados[12]));
            personagem.setBirthDateString(dados[12]);
            personagem.setYearOfBirth(Integer.parseInt(dados[13]));
            personagem.setEyeColour(dados[14]);
            personagem.setGender(dados[15]);
            personagem.setHairColour(dados[16]);
            personagem.setWizard(dados[17]);
            personagemArray[i] = personagem;
            i++;
        }
        scanner.close();
        Scanner scanner2 = new Scanner(System.in); 
        input = scanner2.nextLine(); 
        Personagem[]inseridos=new Personagem[405];
        int inseridosIndex = 0;
        while (!input.equals("FIM")) {
            for (int x = 0; x < i; x++) {
                if (input.equals(personagemArray[x].getId())) {
                    inseridos[inseridosIndex++]=personagemArray[x];
                    break;
                }
            }
            input = scanner2.nextLine(); 
        }
        

        //Scanner scanner3 = new Scanner(System.in); 
       String input2 = scanner2.nextLine(); 
       while (!input2.equals("FIM")) {
        boolean encontrado = false;
        for (int z = 0; z < inseridosIndex; z++) {
            if (input2.equals(inseridos[z].getName())) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
        input2 = scanner2.nextLine();
    }
        scanner2.close();
        
    }


  public static class Personagem {
      private String id;
      private String name;
      private String alternateNames; 
      private String house;
      private String species;
      private String patronus;
      private boolean hogwartsStaff; // Transformado em String
      private boolean hogwartsStudent; // Transformado em String
      private String actorName;
      private boolean alive; // Transformado em String
      private Date birthDate;
      private String birthDateString;
      public String getBirthDateString() {
          return birthDateString;
      }

      public void setBirthDateString(String birthDateString) {
          this.birthDateString = birthDateString;
      }
      private int yearOfBirth;
      private String eyeColour;
      private String gender;
      private boolean wizard; // Transformado em String
      private String ancestry; 
      private String alternateActors; 
      private String hairColour; 

      public String getHairColour() {
          return hairColour;
      }

      public void setHairColour(String hairColour) {
          this.hairColour = hairColour;
      }

      public String getAlternateActors() {
          return alternateActors;
      }

      public void setAlternateActors(String alternateActors) {
          this.alternateActors = alternateActors;
      }

      public Personagem(String id, String name) {
          this.id = id;
          this.name = name;
      }

      public Personagem() {
          this.id = "0";
          this.name = "";
          this.species = "";
      }
      SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
      public void imprimir() {
          MyIO.print("[" + id + " ## " + name + " ## " + alternateNames + " ## " + house +" ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
                  hogwartsStaff + " ## " + hogwartsStudent + " ## " + actorName + " ## " + alive + " ## " + birthDateString +
                  " ## " + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## " + hairColour +" ## " + wizard +  ']'+'\n');
      }
      public String imprimirString() {
        return("[" + id + " ## " + name + " ## " + alternateNames + " ## " + house +" ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
                hogwartsStaff + " ## " + hogwartsStudent + " ## " + actorName + " ## " + alive + " ## " + birthDateString +
                " ## " + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## " + hairColour +" ## " + wizard +  ']');
    }
      // Métodos getters e setters omitidos para brevidade

      public String getId() {
          return id;
      }

      public void setId(String id) {
          this.id = id;
      }
      public String getName() {
          return name;
      }
      public void setName(String name) {
          this.name = name;
      }
      public String getAlternateNames() {
          return alternateNames;
      }

      public void setAlternateNames(String alternateNames) {
          this.alternateNames = alternateNames;
      }

      public String getHouse() {
          return house;
      }

      public void setHouse(String house) {
          this.house = house;
      }

      public String getSpecies() {
          return species;
      }

      public void setSpecies(String species) {
          this.species = species;
      }

      public String getPatronus() {
          return patronus;
      }

      public void setPatronus(String patronus) {
          this.patronus = patronus;
      }

      public Boolean getHogwartsStaff() {
          return hogwartsStaff;
      }

      public void setHogwartsStaff(String dado) {
          if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
              this.hogwartsStaff = false;
          } else {
              this.hogwartsStaff = false;
          }
      }

      public Boolean getHogwartsStudent() {
          return hogwartsStudent;
      }

      public void setHogwartsStudent(String dado) {
          if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
              this.hogwartsStudent = false;
          } else {
              this.hogwartsStudent = false;
          }
      }

      public String getActorName() {
          return actorName;
      }

      public void setActorName(String actorName) {
          this.actorName = actorName;
      }

      public boolean getAlive() {
          return alive;
      }

      public void setAlive(String dado) {
          if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
              this.alive = false;
          } else {
              this.alive = false;
          }
      }

      public Date getBirthDate() {
          return birthDate;
      }

      public void setBirthDate(Date birthDate) {
          this.birthDate = birthDate;
      }

      public int getYearOfBirth() {
          return yearOfBirth;
      }

      public void setYearOfBirth(int yearOfBirth) {
          this.yearOfBirth = yearOfBirth;
      }

      public String getEyeColour() {
          return eyeColour;
      }

      public void setEyeColour(String eyeColour) {
          this.eyeColour = eyeColour;
      }

      public String getGender() {
          return gender;
      }

      public void setGender(String gender) {
          this.gender = gender;
      }

      public Boolean getWizard() {
          return wizard;
      }

      public void setWizard(String dado) {
          if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
              this.wizard = false;
          } else {
              this.wizard = false;
          }
      }

      public String getAncestry() {
          return ancestry;
      }

      public void setAncestry(String ancestry) {
          this.ancestry = ancestry;
      }
  }

    public static Date converterParaData(String dataString) throws ParseException {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd-MM-yyyy");
        return formatoEntrada.parse(dataString);
    }

}

