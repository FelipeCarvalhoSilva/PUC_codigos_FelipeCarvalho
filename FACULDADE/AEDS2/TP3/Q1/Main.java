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
        //  "/tmp/characters.csv"
        String nomeArquivo = "C:\\Users\\Felipe\\Desktop\\TP3\\tmp\\characters.csv";
        Scanner scanner = new Scanner(new File(nomeArquivo));
        scanner.nextLine(); // Ignorar o cabeçalho
        String input = "";
        Lista lista=new Lista();
        lista.inicio=new Celula();
        lista.fim=lista.inicio;
        lista.tam=0;

        while (scanner.hasNextLine()){
            String linha = scanner.nextLine();
            Personagem personagem=new Personagem();
            String[] dados = linha.split(";");
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
            //colocar na lista 
            lista.inserirFim(personagem);
        }
        scanner.close();
        scanner = new Scanner(System.in);
        input=scanner.nextLine();
         while (!input.equals("FIM")) {
            
            input=scanner.nextLine();
        }
        int n=scanner.nextInt();
        String comando;
        int pos;
        String personagemAserinserido;
        for(int i=0;i<n;i++){
            comando=scanner.next();
            switch (comando) {
                //remover inicio
                case "RI":
                    lista.removerInicio().personagem.imprimir();
                    break;

                //remover fim
                case "RF":
                    
                    break;
                
                //remover qualquer posicao
                case "R*":
                    
                break;

                //inserir inicio
                case "II":
                personagemAserinserido=scanner.next();
                    break;

                //inserir fim
                case "IF":
                personagemAserinserido=scanner.next();
                    break;

                //inserir qualaquer posicao
                case "I*":
                pos =scanner.nextInt();
                personagemAserinserido=scanner.next();
                    break;

                //default
                default:
                    break;
            }
        }
        
        
      
        /*
        IMPRIME A LISTA TODA 
       int contador=0;
       Celula percorre=lista.inicio.prox;
       while(percorre!=null){
            percorre.personagem.imprimir();
            percorre=percorre.prox;
            contador++;
        } 
          System.out.println(contador+" lidos="+i+"  tamanhoLista="+lista.tamanho(lista));
        */

        //lista.inicio.prox.personagem.imprimir();  saber quem esta no inicio
        //lista.fim.personagem.imprimir();          saber quem esta no fim
        //lista.removerFim().personagem.imprimir(); teste de removerFim
        //lista.removerInicio().personagem.imprimir(); teste de removerInicio
        //lista.remover(posição).personagem.imprimir(); teste de remoção com parametro
       
        
        scanner.close();
    }


    //CLASSE LISTA
    public static class Lista{
        Celula inicio;
        Celula fim;
        int tam;
        int tamanho(Lista lista){
            int tam=0;
            Celula percorre=lista.inicio;
            while(percorre!=null){
                tam++;
                percorre=percorre.prox;
            }
            this.tam=tam;
            return tam;
        }
        void inserirInicio(Personagem personagemRecebido){
            Celula inicioNovo=new Celula();
            this.inicio.ant=inicioNovo;
            inicioNovo.prox=this.inicio;
            inicioNovo.personagem=personagemRecebido;
            this.inicio=inicioNovo;
        }
        void inserirFim(Personagem personagemRecebido){
            Celula fimNovo=new Celula();
            this.fim.prox=fimNovo;
            fimNovo.ant=this.fim;
            fimNovo.personagem=personagemRecebido;
            this.fim=fimNovo;
        }
        void inserir(Personagem personagem,int pos){
                
        }
        Celula removerInicio(){
            Celula retorno=new Celula();
            retorno.personagem=this.inicio.prox.personagem;
            this.inicio.personagem=null;
            this.inicio.prox=this.inicio.prox.prox;
            this.inicio.prox.ant=null;
            this.inicio.ant=null;

            return retorno;
        }
        Celula remover(int pos){
            Celula retorno=new Celula();
            Celula percorre=this.inicio;
            int i=0;
            while(percorre!=null){
                if((i++) ==pos+1){
                    retorno.personagem=percorre.personagem;
                    percorre.personagem=null;
                    
                    if(percorre.prox!=null)percorre.prox.ant=percorre.ant;
                    percorre.ant.prox=percorre.prox;
                    percorre.prox=null;
                    percorre.ant=null;
                    return retorno;
                }
                percorre=percorre.prox;
            }
            return retorno;
        }
        Celula removerFim(){
            Celula retorno=new Celula();
            retorno.personagem=this.fim.personagem;
            this.fim.personagem=null;
            this.fim=this.fim.ant;
            this.fim.prox.ant=null;
            this.fim.prox=null;

            return retorno;
        }
    }
    //CLASSE CELULA
    public static class Celula{
        Celula prox;
        Celula ant;
        Personagem personagem;
    }
    //CLASSE PERSONAGEM
    public static class Personagem {
        private String id;
        private String name;
        private String alternateNames; 
        private String house;
        private String species;
        private String patronus;
        private boolean hogwartsStaff; 
        private boolean hogwartsStudent; 
        private String actorName;
        private boolean alive; 
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
        private boolean wizard; 
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
            System.out.println("[" + id + " ## " + name + " ## " + alternateNames + " ## " + house +" ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
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
    public static Date converterParaData(String dataString) throws ParseException {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd-MM-yyyy");
        return formatoEntrada.parse(dataString);
    }
}
