import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//  "/tmp/characters.csv"
// C:\Users\Felipe\Desktop\TP's AEDS2\TP4\tmp\characters.csv

public class Main {

    public static void main(String[] args) throws Exception {
        String nomeArquivo = "/tmp/characters.csv";
        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(nomeArquivo, "r");

            // Ignora o cabeçalho (primeira linha)
            file.readLine(); // Ou file.readLine(); se o método anterior não estiver funcionando.

            Lista lista = new Lista();
            lista.inicio = new Celula();
            lista.fim = lista.inicio;
            lista.tam = 0;

            Personagem[] arquivo = new Personagem[405];
            int a = 0;

            // Leitura dos dados
            String line;
            while ((line = file.readLine()) != null) {
                Personagem personagem = new Personagem();
                String[] dados = line.split(";");

                personagem.setId(dados[0]);
                personagem.setName(dados[1]);

                ArrayList<String> alternateNames = new ArrayList<>();
                Pattern pattern = Pattern.compile("'(.*?)'");
                Matcher matcher = pattern.matcher(dados[2]);
                while (matcher.find()) {
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
                String formatado = formattedAlternateNames.toString();

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

                // Adicionar à lista e ao array
                lista.inserirFim(personagem);
                arquivo[a++] = personagem;
            }

            // Interagir com o usuário
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            Alvinegra arvore = new Alvinegra();

            while (!input.equals("FIM")) {
                for (int c = 0; c < 404; c++) {
                    if (input.equals(arquivo[c].getId())) {
                        arvore.inserir(arquivo[c]);
                    }
                }
                input = scanner.nextLine();
            }
            input="";
            while (!(input = scanner.nextLine()).equals("FIM")) {
                for (int c = 0; c < 404; c++) {
                    if (input.equals(arquivo[c].getName())) {
                        /* if(input.equals("James Potter")){tabela.pesquisar(arquivo[c]);break;}
                    else tabela.pesquisar(arquivo[c]); */
                    if(input.equals("James Potter")){
                        System.out.printf(arquivo[c].getName() + " => ");
                        arvore.pesquisar(arquivo[c]);
                        break;
                    }else{
                        System.out.printf(arquivo[c].getName() + " => ");
                        arvore.pesquisar(arquivo[c]);
                    }
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //CLASSE LISTA
    static class Lista{
        Celula inicio;
        Celula fim;
        int tam;
        void print(){
            Celula pos=this.inicio;
            int i=0;
            while (pos!=null){
                if(pos.personagem!=null){
                    System.out.printf("[%d ",i++);
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
    }
    //CLASSE CELULA
    static class Celula{
        Celula prox;
        Celula ant;
        Personagem personagem;
    }
    //CLASSE PERSONAGEM
    static class Personagem {
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
            System.out.printf("["+id + " ## " + name + " ## " + alternateNames + " ## " + house +" ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
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
    //ARVORE
    public static class Alvinegra {
        private NoAN raiz; // Raiz da arvore.
        class NoAN {
            public boolean cor;
            public Personagem elemento;
            public NoAN esq, dir;
          
            public NoAN() {
                this(null);
              }
            
            public NoAN(Personagem elemento) {
              this(elemento, false, null, null);
            }
          
            public NoAN(Personagem elemento, boolean cor) {
              this(elemento, cor, null, null);
            }
          
            public NoAN(Personagem elemento, boolean cor, NoAN esq, NoAN dir) {
              this.cor = cor;
              this.elemento = elemento;
              this.esq = esq;
              this.dir = dir;
            }
          }
        /**
         * Construtor da classe.
         */
        public Alvinegra() {
           raiz = null;
        }
     public boolean isNoTipo4(NoAN i){
        return (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true);
     }
        /**
         * Metodo publico iterativo para pesquisar elemento.
         * 
         * @param elemento Elemento que sera procurado.
         * @return <code>true</code> se o elemento existir,
         *         <code>false</code> em caso contrario.
         */
        public boolean pesquisar(Personagem elemento) {
        System.out.printf("raiz ");
           return pesquisar(elemento, raiz);
        }
     
        /**
         * Metodo privado recursivo para pesquisar elemento.
         * 
         * @param elemento Elemento que sera procurado.
         * @param i        NoAN em analise.
         * @return <code>true</code> se o elemento existir,
         *         <code>false</code> em caso contrario.
         */
        private boolean pesquisar(Personagem elemento, NoAN i) {
           boolean resp;
           if (i == null) {
            System.out.printf("NAO\n");
              resp = false;
           } else if (elemento.getName().compareTo(i.elemento.getName())==0) {
            System.out.printf("SIM\n");
              resp = true;
           } else if (elemento.getName().compareTo(i.elemento.getName())<0) {
            System.out.printf("esq ");
              resp = pesquisar(elemento, i.esq);
           } else {
            System.out.printf("dir ");
              resp = pesquisar(elemento, i.dir);
           }
           return resp;
        }
     
        /**
         * Metodo publico iterativo para exibir elementos.
         */
        public void caminharCentral() {
           System.out.print("[ ");
           caminharCentral(raiz);
           System.out.println("]");
        }
     
        /**
         * Metodo privado recursivo para exibir elementos.
         * 
         * @param i NoAN em analise.
         */
        private void caminharCentral(NoAN i) {
           if (i != null) {
              caminharCentral(i.esq); // Elementos da esquerda.
              System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
              caminharCentral(i.dir); // Elementos da direita.
           }
        }
     
        /**
         * Metodo publico iterativo para exibir elementos.
         */
        public void caminharPre() {
           System.out.print("[ ");
           caminharPre(raiz);
           System.out.println("]");
        }
     
        /**
         * Metodo privado recursivo para exibir elementos.
         * 
         * @param i NoAN em analise.
         */
        private void caminharPre(NoAN i) {
           if (i != null) {
              System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
              caminharPre(i.esq); // Elementos da esquerda.
              caminharPre(i.dir); // Elementos da direita.
           }
        }
     
        /**
         * Metodo publico iterativo para exibir elementos.
         */
        public void caminharPos() {
           System.out.print("[ ");
           caminharPos(raiz);
           System.out.println("]");
        }
     
        /**
         * Metodo privado recursivo para exibir elementos.
         * 
         * @param i NoAN em analise.
         */
        private void caminharPos(NoAN i) {
           if (i != null) {
              caminharPos(i.esq); // Elementos da esquerda.
              caminharPos(i.dir); // Elementos da direita.
              System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
           }
        }
     
        /**
         * Metodo publico iterativo para inserir elemento.
         * 
         * @param elemento Elemento a ser inserido.
         * @throws Exception Se o elemento existir.
         */
        public void inserir(Personagem elemento) throws Exception {
           // Se a arvore estiver vazia
           if (raiz == null) {
              raiz = new NoAN(elemento);
     
           // Senao, se a arvore tiver um elemento
           } else if (raiz.esq == null && raiz.dir == null) {
              if (elemento.getName().compareTo(raiz.elemento.getName())<0) {
                 raiz.esq = new NoAN(elemento);
              } else {
                 raiz.dir = new NoAN(elemento);
              }
     
           // Senao, se a arvore tiver dois elementos (raiz e dir)
           } else if (raiz.esq == null) {
              if (elemento.getName().compareTo(raiz.elemento.getName())<0) {
                 raiz.esq = new NoAN(elemento);
     
              } else if (elemento.getName().compareTo(raiz.dir.elemento.getName())<0) {
                 raiz.esq = new NoAN(raiz.elemento);
                 raiz.elemento = elemento;
     
              } else {
                 raiz.esq = new NoAN(raiz.elemento);
                 raiz.elemento = raiz.dir.elemento;
                 raiz.dir.elemento = elemento;
              }
              raiz.esq.cor = raiz.dir.cor = false;
     
           // Senao, se a arvore tiver dois elementos (raiz e esq)
           } else if (raiz.dir == null) {
              if (elemento.getName().compareTo(raiz.elemento.getName())>0) {
                 raiz.dir = new NoAN(elemento);
     
              } else if (elemento.getName().compareTo(raiz.esq.elemento.getName())>0) {
                 raiz.dir = new NoAN(raiz.elemento);
                 raiz.elemento = elemento;
     
              } else {
                 raiz.dir = new NoAN(raiz.elemento);
                 raiz.elemento = raiz.esq.elemento;
                 raiz.esq.elemento = elemento;
              }
              raiz.esq.cor = raiz.dir.cor = false;
     
           // Senao, a arvore tem tres ou mais elementos
           } else {
              inserir(elemento, null, null, null, raiz);
           }
           raiz.cor = false;
        }
     
        private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
           // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
           if (pai.cor == true) {
              // 4 tipos de reequilibrios e acoplamento
              if (pai.elemento.getName().compareTo(avo.elemento.getName())>0) { // rotacao a esquerda ou direita-esquerda
                 if (i.elemento.getName().compareTo(pai.elemento.getName())>0) {
                    avo = rotacaoEsq(avo);
                 } else {
                    avo = rotacaoDirEsq(avo);
                 }
              } else { // rotacao a direita ou esquerda-direita
                 if (i.elemento.getName().compareTo(pai.elemento.getName())<0) {
                    avo = rotacaoDir(avo);
                 } else {
                    avo = rotacaoEsqDir(avo);
                 }
              }
              if (bisavo == null) {
                 raiz = avo;
              } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName())<0) {
                 bisavo.esq = avo;
              } else {
                 bisavo.dir = avo;
              }
              // reestabelecer as cores apos a rotacao
              avo.cor = false;
              avo.esq.cor = avo.dir.cor = true;
           } // if(pai.cor == true)
        }
     
        /**
         * Metodo privado recursivo para inserir elemento.
         * 
         * @param elemento Elemento a ser inserido.
         * @param avo      NoAN em analise.
         * @param pai      NoAN em analise.
         * @param i        NoAN em analise.
         * @throws Exception Se o elemento existir.
         */
        private void inserir(Personagem elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
           if (i == null) {
              if (elemento.getName().compareTo(pai.elemento.getName())<0) {
                 i = pai.esq = new NoAN(elemento, true);
              } else {
                 i = pai.dir = new NoAN(elemento, true);
              }
              if (pai.cor == true) {
                 balancear(bisavo, avo, pai, i);
              }
           } else {
              // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
              if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                 i.cor = true;
                 i.esq.cor = i.dir.cor = false;
                 if (i == raiz) {
                    i.cor = false;
                 } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                 }
              }
              if (elemento.getName().compareTo(i.elemento.getName())<0) {
                 inserir(elemento, avo, pai, i, i.esq);
              } else if (elemento.getName().compareTo(i.elemento.getName())>0) {
                 inserir(elemento, avo, pai, i, i.dir);
              } else {
                 throw new Exception("Erro inserir (elemento repetido)!");
              }
           }
        }
     
        private NoAN rotacaoDir(NoAN no) {
           NoAN noEsq = no.esq;
           NoAN noEsqDir = noEsq.dir;
     
           noEsq.dir = no;
           no.esq = noEsqDir;
     
           return noEsq;
        }
     
        private NoAN rotacaoEsq(NoAN no) {
           NoAN noDir = no.dir;
           NoAN noDirEsq = noDir.esq;
     
           noDir.esq = no;
           no.dir = noDirEsq;
           return noDir;
        }
     
        private NoAN rotacaoDirEsq(NoAN no) {
           no.dir = rotacaoDir(no.dir);
           return rotacaoEsq(no);
        }
     
        private NoAN rotacaoEsqDir(NoAN no) {
           no.esq = rotacaoEsq(no.esq);
           return rotacaoDir(no);
        }
     }
}
    
