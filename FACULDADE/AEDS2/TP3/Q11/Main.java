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
        String nomeArquivo = "/tmp/characters.csv";
        Scanner scanner = new Scanner(new File(nomeArquivo));
        scanner.nextLine(); // Ignorar o cabeçalho
        String input = "";
        Lista lista=new Lista();
        lista.inicio=new Celula();
        lista.fim=lista.inicio;
        lista.tam=0;
        Personagem[] arquivo=new Personagem[405];
        int a=0;
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
            arquivo[a++]=personagem;
            
        }
        
        scanner.close();
        scanner = new Scanner(System.in);
        input=scanner.nextLine();
         while (!input.equals("FIM")) {
            for(int c=0;c<404;c++){
                if(input.equals(arquivo[c].getId()))lista.inserirFim(arquivo[c]);
            }
            input=scanner.nextLine();
        }
        lista.inicio=lista.inicio.prox;
       /*  int n=scanner.nextInt();
        String comando;
        int pos;
        String personagemAserinserido;
        Celula removido=null;
        for(int i=0;i<n;i++){
            comando=scanner.next();
            switch (comando) {
                //remover inicio
                case "RI":
                lista.tamanho(lista);
                removido=lista.removerInicio();
                if(removido!=null)System.out.println("(R) "+removido.personagem.getName());
                    break;

                //remover fim
                case "RF":
                lista.tamanho(lista);
                removido=lista.removerFim();
                if(removido!=null)System.out.println("(R) "+removido.personagem.getName());
                    break;
                
                //remover qualquer posicao
                case "R*":
                lista.tamanho(lista);
                pos =scanner.nextInt();
                removido=lista.remover(pos);
                if(removido!=null)System.out.println("(R) "+removido.personagem.getName());
                break;

                //inserir inicio
                case "II":
                lista.tamanho(lista);
                personagemAserinserido=scanner.next();
                for(int c=0;c<404;c++){
                    if(personagemAserinserido.equals(arquivo[c].getId()))lista.inserirInicio(arquivo[c]);
                }
                    break;

                //inserir fim
                case "IF":
                lista.tamanho(lista);
                personagemAserinserido=scanner.next();
                for(int c=0;c<404;c++){
                    if(personagemAserinserido.equals(arquivo[c].getId()))lista.inserirFim(arquivo[c]);
                }
                    break;

                //inserir qualaquer posicao
                case "I*":
                lista.tamanho(lista);
                pos =scanner.nextInt();
                personagemAserinserido=scanner.next();
                for(int c=0;c<404;c++){
                    if(personagemAserinserido.equals(arquivo[c].getId()))lista.inserir(arquivo[c], pos);
                }
                    break;

                //default
                default:
                    break;
            }
        }
         */
        
      
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
        lista.tamanho(lista);
        lista.sort();
        lista.print();
        scanner.close();
    }


    //CLASSE LISTA
    public static class Lista{
        Celula inicio;
        Celula fim;
        int tam;
        void print(){
            Celula pos=this.inicio;
            int i=0;
            while (pos!=null){
                if(pos.personagem!=null){
                    pos.personagem.imprimir();
                    pos=pos.prox;
                }else pos=null;
            }
        }
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
            if(this.inicio!=null){
                this.inicio.ant=inicioNovo;
                inicioNovo.prox=this.inicio;
                inicioNovo.personagem=personagemRecebido;
                this.inicio=inicioNovo;
            }else {
                this.inicio=inicioNovo;
                this.inicio.personagem=personagemRecebido;
                this.fim=this.inicio;
            }
        }
        void inserirFim(Personagem personagemRecebido){
            Celula fimNovo=new Celula();
                this.fim.prox=fimNovo;
                fimNovo.ant=this.fim;
                fimNovo.personagem=personagemRecebido;
                this.fim=fimNovo;
      
        }
        void inserir(Personagem personagemRecebido,int pos){
            Celula inseirPos=new Celula();
            inseirPos.personagem=personagemRecebido;
            Celula caminhar=this.inicio;
            int i=0;
            while((i++)!=pos && caminhar!=null){
                caminhar=caminhar.prox;
            }
            if(caminhar==null){
                System.out.println("Posição de inserção inválida");
            }else{
                inseirPos.prox=caminhar;
                inseirPos.ant=caminhar.ant;
                caminhar.ant.prox=inseirPos;
                caminhar.ant=inseirPos;
            }
        }
        Celula removerInicio(){
            
            Celula retorno=new Celula();
            retorno.personagem=this.inicio.personagem;
            this.inicio.personagem=null;
            this.inicio=this.inicio.prox;
            return retorno;
         
        }
        Celula remover(int pos){
            Celula retorno=new Celula();
            Celula percorre=this.inicio;
            int i=0;
            if(pos==0&&this.inicio.prox!=null)this.inicio=this.inicio.prox;
            while(percorre!=null){
                if((i++) ==pos){
                    retorno.personagem=percorre.personagem;
                    percorre.personagem=null;
                    
                    if(percorre.prox!=null)percorre.prox.ant=percorre.ant;
                    percorre.ant.prox=percorre.prox;
                    percorre.prox=null;
                    percorre.ant=null;
                    percorre=null;
                    return retorno;
                }
                if(percorre!=null)percorre=percorre.prox;
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
        Celula partition(Celula low, Celula high) {
            Personagem pivot = high.personagem;
            Celula i = low.ant;
    
            for (Celula j = low; j != high; j = j.prox) {
                if (compare(j.personagem, pivot) < 0) {
                    i = (i == null) ? low : i.prox;
                    swap(i, j);
                }
            }
            i = (i == null) ? low : i.prox;
            swap(i, high);
            return i;
        }
    
        int compare(Personagem a, Personagem b) {
            int houseComparison = a.getHouse().compareTo(b.getHouse());
            if (houseComparison != 0) {
                return houseComparison;
            } else {
                return a.getName().compareTo(b.getName());
            }
        }
    
        void quicksort(Celula low, Celula high, int contador) {
            if (high != null && low != high && low != high.prox && contador < this.tam) {
                Celula p = partition(low, high);
                quicksort(low, p.ant, contador + 1);
                quicksort(p.prox, high, contador + 1);
            }
        }
    
        void sort() {
            quicksort(inicio, fim, 0);
        }
        void sortName() {
            quicksortName(inicio, fim, 0);
        }
        Celula partitionName(Celula low, Celula high) {
            Personagem pivot = high.personagem;
            Celula i = low.ant;
    
            for (Celula j = low; j != high; j = j.prox) {
                if (j.personagem.getName().compareTo(pivot.getName()) < 0) {
                    i = (i == null) ? low : i.prox;
                    swap(i, j);
                }
            }
            i = (i == null) ? low : i.prox;
            swap(i, high);
            return i;
        }
        void quicksortName(Celula low, Celula high, int contador) {
            if (high != null && low != high && low != high.prox && contador < this.tam) {
                Celula p = partition(low, high);
                quicksort(low, p.ant, contador + 1);
                quicksort(p.prox, high, contador + 1);
            }
        }
    
        void swap(Celula a, Celula b) {
            Personagem temp = a.personagem;
            a.personagem = b.personagem;
            b.personagem = temp;
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
            System.out.printf("[" + id + " ## " + name + " ## " + alternateNames + " ## " + house +" ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
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
