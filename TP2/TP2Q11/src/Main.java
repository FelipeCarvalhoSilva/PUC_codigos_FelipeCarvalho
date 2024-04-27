import java.io.File;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                ArrayList<String> alternateNames=new ArrayList<>();
            Pattern pattern = Pattern.compile("'(.*?)'");
            Matcher matcher = pattern.matcher(dados[2]);
            while (matcher.find()){
                alternateNames.add(matcher.group(1));
            }
            StringBuilder formattedAlternateNames = new StringBuilder("{");
            for (int x = 0; x < alternateNames.size(); x++) {
                formattedAlternateNames.append(alternateNames.get(x));
                if (x < alternateNames.size() - 1) {
                    formattedAlternateNames.append(", ");
                }
            }
            formattedAlternateNames.append("}");
            String formatado=formattedAlternateNames.toString();
            
            personagem.setAlternateNames(formatado);
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
        //cria array de personagem com base no id inserido
        while (!input.equals("FIM")) {
            for (int x = 0; x < i; x++) {
                if (input.equals(personagemArray[x].getId())) {
                    inseridos[inseridosIndex++]=personagemArray[x];
                    break;
                }
            }
            input = scanner2.nextLine(); 
        }
        scanner2.close();
        int z=0;
        for(int a=0;a<inseridos.length;a++) {
            if(inseridos[a]==null)break;
            z++;
        }

        Countingsort.sort(inseridos,inseridosIndex);
       sortDesempate(inseridos,inseridosIndex);
        z=0;
        while(inseridos[z]!= null){
            inseridos[z].imprimir();
            z++;
        }
        
        
        /* String dataString1 =inseridos[0].getBirthDateString();
        String dataString2 = inseridos[1].getBirthDateString();
 if (dataString1.compareTo(dataString2) < 0) {
    // dataString1 é posterior a dataString2
    System.out.println(dataString1 + " é posterior a " + dataString2);
} else if (dataString1.compareTo(dataString2) > 0) {
    // dataString1 é anterior a dataString2
    System.out.println(dataString1 + " é anterior a " + dataString2);
} else {
    // dataString1 é igual a dataString2
    System.out.println(dataString1 + " é igual a " + dataString2);
} */
       
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
              this.hogwartsStaff = true;
          } else {
              this.hogwartsStaff = false;
          }
      }

      public Boolean getHogwartsStudent() {
          return hogwartsStudent;
      }

      public void setHogwartsStudent(String dado) {
          if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
              this.hogwartsStudent = true;
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
              this.alive = true;
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
              this.wizard = true;
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

  //acabei nao usando pra nada mas vou deixar se precisar depois
    public static Date converterParaData(String dataString) throws ParseException {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd-MM-yyyy");
        return formatoEntrada.parse(dataString);
    }
    public static void swap(Personagem[] array, int i, int j) {
        Personagem temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    //insercao
    public static void sortInsertion(Personagem[] array, int n) {
        for (int i = 1; i < n; i++) {
            Personagem tmp = array[i]; // Armazenar temporariamente o valor atual
            Date tmpBirthDate = tmp.getBirthDate(); // String de data de nascimento atual
            String tmpName = tmp.getName(); // Nome do personagem atual
            int j = i - 1;
    
            // Ordenação por data de nascimento
            while (j >= 0 && array[j].getBirthDate().after(tmpBirthDate)) {
                array[j + 1] = array[j];
                j--;
            }
            
            // Caso de desempate 
             while (j >= 0 && array[j].getBirthDate().equals(tmpBirthDate) &&
                    array[j].getName().compareTo(tmpName) > 0) {
                array[j + 1] = array[j];
                j--;
            } 
    
            array[j + 1] = tmp; // Inserir o elemento atual na posição correta
        }
    }
    
    public static void sortDesempate(Personagem[] array, int n) {
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++) {
                if (array[menor].getYearOfBirth() == array[j].getYearOfBirth() &&
                    array[menor].getName().compareTo(array[j].getName()) > 0) {
                    menor = j;
                } else if (array[menor].getYearOfBirth() > array[j].getYearOfBirth()) {
                    menor = j;
                }
            }
            swap(array, menor, i);
        }
    }
    //selecao
    public static void sort(Personagem[] array,int n) {
    
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++) {
                if (array[menor].getName().compareTo(array[j].getName()) > 0) {
                    menor = j;
                }
            }
            swap(array, menor, i); 
        }
    }

    //heapsort

    public static class HeapSort {
        public static void sort(Personagem[] array, int n) {
            buildMaxHeap(array, n);
    
            for (int i = n - 1; i > 0; i--) {
                swap(array, 0, i);
                maxHeapify(array, 0, i);
            }
        }
    
        private static void buildMaxHeap(Personagem[] array, int n) {
            for (int i = n / 2 - 1; i >= 0; i--) {
                maxHeapify(array, i, n);
            }
        }
    
        private static void maxHeapify(Personagem[] array, int i, int n) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
    
            if (left < n && compare(array[left], array[largest]) > 0) {
                largest = left;
            }
    
            if (right < n && compare(array[right], array[largest]) > 0) {
                largest = right;
            }
    
            if (largest != i) {
                swap(array, i, largest);
                maxHeapify(array, largest, n);
            }
        }
    
        private static void swap(Personagem[] array, int i, int j) {
            Personagem temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    
        private static int compare(Personagem p1, Personagem p2) {
            int compareResult = p1.getHairColour().compareTo(p2.getHairColour());
            
            // Se as cores do cabelo forem iguais, desempate usando o nome
            if (compareResult == 0) {
                compareResult = p1.getName().compareTo(p2.getName());
            }
            
            return compareResult;
        }
    }
    //counting sort
    static class  Countingsort {
        static public void sort(Personagem[]array ,int n) {
            //Array para contar o numero de ocorrencias de cada elemento
            int[] count = new int[getMaior(array,n) + 1];
            Personagem[] ordenado = new Personagem[n];
      
            //Inicializar cada posicao do array de contagem 
              for (int i = 0; i < count.length; count[i] = 0, i++);
      
            //Agora, o count[i] contem o numero de elemento iguais a i
            for (int i = 0; i < n; count[array[i].getYearOfBirth()]++, i++);
      
            //Agora, o count[i] contem o numero de elemento menores ou iguais a i
            for(int i = 1; i < count.length; count[i] += count[i-1], i++);
      
            //Ordenando
            for(int i = n-1; i >= 0; ordenado[count[array[i].getYearOfBirth()]-1] = array[i], count[array[i].getYearOfBirth()]--, i--);
      
            //Copiando para o array original
            for(int i = 0; i < n; array[i] = ordenado[i], i++);
         }
      
      
          /**
           * Retorna o maior elemento do array.
          * @return maior elemento
           */
          static public int getMaior(Personagem[]array, int n) {
             int maior = array[0].getYearOfBirth();
      
              for (int i = 0; i < n; i++) {
               if(maior < array[i].getYearOfBirth()){
                  maior = array[i].getYearOfBirth();
               }
              }
             return maior;	
          }
    }
    }



      


