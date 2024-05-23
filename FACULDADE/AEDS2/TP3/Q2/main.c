#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
// /tmp/characters.csv
// C:/Users/Felipe/Desktop/TP's AEDS2/TP3/tmp/characters.csv
#define FILE_PATH "/tmp/characters.csv"

#define INITIAL_STRING_CAPACITY 32
#define INITIAL_ARRAY_CAPACITY 5

typedef struct {
    char *id;
    char *name;
    char **alternateNames;
    int alternateNamesCount;
    char *house;
    char *ancestry;
    char *species;
    char *patronus;
    bool isHogwartsStaff;
    bool isHogwartsStudent;
    char *actorName;
    bool isAlive;
    char **alternateActors;
    int alternateActorsCount;
    char *dateOfBirth;
    int yearOfBirth;
    char *eyeColour;
    char *gender;
    char *hairColour;
    bool isWizard;
} Character;

typedef struct {
    Character *array;    
    int size;      
    int capacity;  
} SequentialList;

// Função para inserir um elemento em qualquer posição da lista
void insert_element_at(SequentialList *list, Character inserir, int position) {
    if (position < 0 || position > list->size) {
        printf("Posição inválida\n");
        return;
    }

    // Deslocar todos os elementos uma posição para a direita a partir da posição especificada
    for (int i = list->size; i > position; i--) {
        list->array[i] = list->array[i - 1];
    }

    // Inserir o novo elemento na posição especificada
    list->array[position] = inserir;
    list->size++;
}

// Função para remover o primeiro elemento da lista
void remove_first_element(SequentialList *list) {
    if (list->size == 0) {
        printf("A lista está vazia\n");
        return;
    }
    for (int i = 0; i < list->size - 1; i++) {
        list->array[i] = list->array[i + 1];
    }
    list->size--;
}

//insere primeira posição
void insert_first_element(SequentialList *list,Character inserir) {
    if (list->size == 0) {
        printf("A lista está vazia\n");
        return;
    }
    for (int i = list->size; i > 0; i--) {
        list->array[i] = list->array[i - 1];
    }
    // Inserir o novo elemento na primeira posição
    list->array[0] = inserir;
    list->size++;
}

//insere ultima posição
void insert_last_element(SequentialList *list,Character inserir) {
    if (list->size == 0) {
        printf("A lista está vazia\n");
        return;
    }
   
    // Inserir o novo elemento na ultima posição
    list->array[list->size] = inserir;
    list->size++;
}

//remove em index especifico
Character delete_element(SequentialList *list, int index) {
    Character retorno;
    if (index < 0 || index >= list->size) {
        printf("Index out of bounds\n");
        return retorno;
    }
    retorno =list->array[index];
    for (int i = index; i < list->size - 1; i++) {
        list->array[i] = list->array[i + 1];
    }
    list->size--;
    return retorno;
}

void free_string_array(char **array, int length);
void free_character(Character *character);

char *read_string(int *i, const char *csvLine) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize) 
        return NULL; 

    int capacity = INITIAL_STRING_CAPACITY;
    char *str = (char *)malloc(capacity * sizeof(char));
    if (str == NULL) {
        perror("Memory allocation error in string");
        return NULL;
    }

    int pos = 0; 
    while (*i < maxSize && csvLine[*i] != ';') 
    {
        if (pos >= capacity-1) {
            capacity *= 2;
            char *temp = (char *)realloc(str, capacity * sizeof(char));
            if (temp == NULL) {
                perror("Error when resizing string");
                free(str); 
                return NULL;
            }
            str = temp;
        }
        str[pos++] = csvLine[(*i)++];
    }

    str[pos] = '\0'; 
    (*i)++;
    return str;
}

char **read_multivalued(int *i, const char *csvLine, int *arrayCount) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize-1 || csvLine[*i] != '[') 
        return NULL;

    (*i)++; // jump '['

    int arrayCapacity = INITIAL_ARRAY_CAPACITY;
    char **array = (char **)malloc(arrayCapacity * sizeof(char *)); 
    if (array == NULL) {
        perror("Memory allocation error in string array");
        return NULL;
    }

    int pos = 0;
    while (*i < maxSize && csvLine[*i] != ']')
    {
        if (pos >= arrayCapacity) {
            arrayCapacity *= 2; 
            char **temp = (char **)realloc(array, arrayCapacity * sizeof(char *)); 
            if (temp == NULL) {
                perror("Error when resizing string array");
                free_string_array(array, pos);
                return NULL;
            }
            array = temp; 
        }

        int strCapacity = INITIAL_STRING_CAPACITY;
        char *tempStr = (char *)malloc(strCapacity * sizeof(char));
        if (tempStr == NULL) {
            perror("Memory allocation error in string");
            free_string_array(array, pos);
            return NULL;
        }

        int j = 0;
        while (*i < maxSize && csvLine[*i] != ',' && csvLine[*i] != ']')
        {
            if (j >= strCapacity - 1) { 
                strCapacity *= 2;
                char *tempStr2 = (char *)realloc(tempStr, strCapacity * sizeof(char));
                if (tempStr2 == NULL) {
                    perror("Memory reallocation error in string");
                    free_string_array(array, pos);
                    free(tempStr);
                    return NULL;
                }
                tempStr = tempStr2; 
            }
            if (csvLine[*i] != '\'') 
                tempStr[j++] = csvLine[*i];

            (*i)++;
        }

        tempStr[j] = '\0'; 
        array[pos++] = tempStr;

        if (csvLine[*i] == ',') 
            (*i)++; 
    }
    (*i) += 2; // jump ']' and ';'
    *arrayCount = pos; 
    return array;
}

bool read_boolean(int *i, const char *csvLine) {
    bool predicate = false;
    char* wordRead = read_string(i, csvLine);

    if (wordRead != NULL) {
        if (strcmp(wordRead, "VERDADEIRO") == 0||strcmp(wordRead, "VERDADEIRO ") == 0||strcmp(wordRead, "VERDADEIRO\r\n") == 0)
            predicate = true;
        free(wordRead);
    }

    return predicate; 
}

int read_integer(int *i, const char *csvLine){
    char* wordRead = read_string(i, csvLine);
    if(wordRead == NULL)
        return -1;
    return atoi(wordRead);
}

Character* csvLine_mapper(char *csvLine) {
    Character* character = (Character*)malloc(sizeof(Character));
    if (character == NULL) {
        perror("Memory allocation error in Character struct");
        return NULL;
    }

    int i = 0;

    character->id = read_string(&i, csvLine);
    character->name = read_string(&i, csvLine);

    int alternateNamesCount = 0;
    character->alternateNames = read_multivalued(&i, csvLine, &alternateNamesCount);
    character->alternateNamesCount = alternateNamesCount;

    character->house = read_string(&i, csvLine);
    character->ancestry = read_string(&i, csvLine);
    character->species = read_string(&i, csvLine);
    character->patronus = read_string(&i, csvLine);
    character->isHogwartsStaff = read_boolean(&i, csvLine);
    character->isHogwartsStudent = read_boolean(&i, csvLine);
    character->actorName = read_string(&i, csvLine);
    character->isAlive = read_boolean(&i, csvLine);

    int alternateActorsCount = 0;
    character->alternateActors = read_multivalued(&i, csvLine, &alternateActorsCount);
    character->alternateActorsCount = alternateActorsCount;

    character->dateOfBirth = read_string(&i, csvLine);
    character->yearOfBirth = read_integer(&i, csvLine);
    character->eyeColour = read_string(&i, csvLine);
    character->gender = read_string(&i, csvLine);
    character->hairColour = read_string(&i, csvLine);
    character->isWizard = read_boolean(&i, csvLine);

    return character; 
}

void print_string_array(char **array, int count) {
    printf("{");
    for (int i = 0; i < count; i++) {
        printf("%s", array[i]);
        if (i < count - 1) {
            printf(", ");
        }
    }
    printf("}");
}

void print_character(Character *character) {
    if (character == NULL) {
        printf("Character struct is NULL.\n");
        return;
    }

    printf(" ## ");
    printf("%s", character->id ? character->id : " "); 
    printf(" ## %s", character->name ? character->name : " ");

    printf(" ## ");
    if (character->alternateNames) 
        print_string_array(character->alternateNames, character->alternateNamesCount);
    else 
        printf(" {}");

    printf(" ## %s", character->house ? character->house : " "); 
    printf(" ## %s", character->ancestry ? character->ancestry : " "); 
    printf(" ## %s", character->species ? character->species : " "); 
    printf(" ## %s", character->patronus ? character->patronus : " "); 
    printf(" ## %s", character->isHogwartsStaff ? "true" : "false"); 
    printf(" ## %s", character->isHogwartsStudent ? "true" : "false"); 
    printf(" ## %s", character->actorName ? character->actorName : " ");
    printf(" ## %s", character->isAlive ? "true" : "false"); 
    printf(" ## %s", character->dateOfBirth ? character->dateOfBirth : " "); 
    printf(" ## %d", character->yearOfBirth); 
    printf(" ## %s", character->eyeColour ? character->eyeColour : " ");
    printf(" ## %s", character->gender ? character->gender : " ");
    printf(" ## %s", character->hairColour ? character->hairColour : " "); 
    printf(" ## %s", character->isWizard ? "true" : "false");

    printf("]\n");
}
Character vetor(char *csvLine){
    Character* character = (Character*)malloc(sizeof(Character));
   

    int i = 0;

    character->id = read_string(&i, csvLine);
    character->name = read_string(&i, csvLine);

    int alternateNamesCount = 0;
    character->alternateNames = read_multivalued(&i, csvLine, &alternateNamesCount);
    character->alternateNamesCount = alternateNamesCount;

    character->house = read_string(&i, csvLine);
    character->ancestry = read_string(&i, csvLine);
    character->species = read_string(&i, csvLine);
    character->patronus = read_string(&i, csvLine);
    character->isHogwartsStaff = read_boolean(&i, csvLine);
    character->isHogwartsStudent = read_boolean(&i, csvLine);
    character->actorName = read_string(&i, csvLine);
    character->isAlive = read_boolean(&i, csvLine);

    int alternateActorsCount = 0;
    character->alternateActors = read_multivalued(&i, csvLine, &alternateActorsCount);
    character->alternateActorsCount = alternateActorsCount;

    character->dateOfBirth = read_string(&i, csvLine);
    character->yearOfBirth = read_integer(&i, csvLine);
    character->eyeColour = read_string(&i, csvLine);
    character->gender = read_string(&i, csvLine);
    character->hairColour = read_string(&i, csvLine);
    character->isWizard = read_boolean(&i, csvLine);
    return *character;
}

void insertionSortByCor(Character *array, int n, int cor, int h) {
    for (int i = h + cor; i < n; i += h) {
        Character tmp;
        strcpy(tmp.name, array[i].name);
        strcpy(tmp.house, array[i].house);

        int j = i - h;
        while ((j >= 0) && ((strcmp(array[j].house, tmp.house) > 0) || (strcmp(array[j].house, tmp.house) == 0 && strcmp(array[j].name, tmp.name) > 0))) {
            strcpy(array[j + h].name, array[j].name);
            strcpy(array[j + h].house, array[j].house);

            j -= h;
        }
        strcpy(array[j + h].name, tmp.name);
        strcpy(array[j + h].house, tmp.house);
    }
}

// shell sort 
void shellSort(Character *array, int n) {
    int h = 1;
    do { h = h * 3 + 1; } while (h < n);

    do {
        h /= 3;
        for (int cor = 0; cor < h; cor++) {
            insertionSortByCor(array, n, cor, h);
        }
    } while (h != 1);
}
void swap(Character *i, Character *j) {
   Character temp = *i;
   *i = *j;
   *j = temp;
}
void bolha(Character *array, int n){
    int i, j;
    for (i = 0; i < n - 1; i++) {
        for (j = 0; j < n - 1 - i; j++) {
            if (strcmp(array[j].hairColour, array[j + 1].hairColour) > 0 ||
                (strcmp(array[j].hairColour, array[j + 1].hairColour) == 0 &&
                 strcmp(array[j].name, array[j + 1].name) > 0)) {
                swap(&array[j], &array[j + 1]);
            }
        }
    }
}
void construir(int *array, int tamHeap){
    for(int i = tamHeap; i > 1 && array[i] > array[i/2]; i /= 2){
        swap(array + i, array + i/2);
    }
}
//=============================================================================
int getMaiorFilho(int *array, int i, int tamHeap){
    int filho;
    if (2*i == tamHeap || array[2*i] > array[2*i+1]){
        filho = 2*i;
    } else {
        filho = 2*i + 1;
    }
    return filho;
}
//=============================================================================
void reconstruir(int *array, int tamHeap){
    int i = 1;
    while(i <= (tamHeap/2)){
        int filho = getMaiorFilho(array, i, tamHeap);
        if(array[i] < array[filho]){
            swap(array + i, array + filho);
            i = filho;
        }else{
            i = tamHeap;
        }
    }
}
//=============================================================================
void heapsort(int *array, int n) {
    //Alterar o vetor ignorando a posicao zero
    int arrayTmp[n+1];
    for(int i = 0; i < n; i++){
        arrayTmp[i+1] = array[i];
    }

    //Contrucao do heap
    for(int tamHeap = 2; tamHeap <= n; tamHeap++){
        construir(arrayTmp, tamHeap);
    }

    //Ordenacao propriamente dita
    int tamHeap = n;
    while(tamHeap > 1){
        swap(arrayTmp + 1, arrayTmp + tamHeap--);
        reconstruir(arrayTmp, tamHeap);
    }

    //Alterar o vetor para voltar a posicao zero
    for(int i = 0; i < n; i++){
        array[i] = arrayTmp[i+1];
    }
}
void printLista(SequentialList lista){
    int conta=0;
    for(int i=0;i<lista.size;i++){
        if(&lista.array[i]!=NULL){
            printf("[%d",conta++);
            print_character(&lista.array[i]);}
    }
}
int main(){
    FILE *file = fopen(FILE_PATH, "r");  
    if (file == NULL) { 
        perror("File not found exception.");
        return 1;
    }

    char line[256];
    fgets(line, sizeof(line), file);
    char *input=(char*)malloc(256*sizeof(char));
    Character *characterVec = (Character*)malloc(405*sizeof(Character));
    Character *inseridos = (Character*)malloc(405*sizeof(Character));
    int z=0;
    int inseridosIndex = 0;
    //preenche vetor com characters
    while (fgets(line, sizeof(line), file)) {
        characterVec[z++]=vetor(line);
        }
// scan de id's
    while(strcmp(input, "FIM")!=0){
        scanf("%s",input);
        if(strcmp(input, "FIM")==0)break;
        // se acha id no characterVec, coloca em inseridos[] um vetor com os personagens que o id bateu com o input
       for(int b=0;b<z;b++){ 
        if(strcmp(input, characterVec[b].id)==0)inseridos[inseridosIndex++] = characterVec[b];
    }
    }
    SequentialList *lista=(SequentialList*)malloc(sizeof(SequentialList));
    lista->array=inseridos;
    lista->size=inseridosIndex;
    fclose(file);
    int numOp;
    scanf("%d",&numOp);
    int index=0;
    char *inserir=(char*)malloc(256*sizeof(char));
    for(int i=0;i<numOp;i++){
        scanf("%s",input);

        //remover inicio
        if(strcmp(input,"RI")==0){
         Character removido=inseridos[0];
         
            printf("(R) %s\n",removido.name);
            remove_first_element(lista);
            }

        //remover fim
        if(strcmp(input,"RF")==0){
            Character removido=inseridos[lista->size-1];
         
            printf("(R) %s\n",removido.name);
            lista->size--;
        }

        //remover qualquer pos
        if(strcmp(input,"R*")==0){
         scanf("%d",&index);
         printf("(R) %s\n",delete_element(lista,index).name);
         }
            
            

        //inserir inicio
        if(strcmp(input,"II")==0){
            scanf("%s",inserir);
             for(int b=0;b<405;b++){ 
                if(strcmp(inserir, characterVec[b].id)==0){
                    insert_first_element(lista,characterVec[b]);
                    b=z;
                    }
                    }
        }

        //inserir fim
        if(strcmp(input,"IF")==0){
            scanf("%s",inserir);
             for(int b=0;b<405;b++){ 
                if(strcmp(inserir, characterVec[b].id)==0){
                    insert_last_element(lista,characterVec[b]);
                    b=z;
                    }
                    }
        }

        //inserir qualquer pos
        if(strcmp(input,"I*")==0){
            scanf("%d",&index);
            scanf("%s",inserir);
             for(int b=0;b<405;b++){ 
                if(strcmp(inserir, characterVec[b].id)==0){
                    insert_element_at(lista,characterVec[b],index);
                    b=z;
                    }
                    }
            
        }
    }
    
    printLista(*lista);
    
   

    return 0;
}

void free_string_array(char **array, int length) {
    if (array == NULL)
        return;

    for (int i = 0; i < length; i++)
        free(array[i]);
    free(array);
}

void free_character(Character *character) {
    if (character == NULL)
        return;

    free(character->id);
    free(character->name);

    if (character->alternateNames)
        free_string_array(character->alternateNames, character->alternateNamesCount);

    free(character->house);
    free(character->ancestry);
    free(character->species);
    free(character->patronus);

    free(character->actorName);
    free(character->dateOfBirth);

    if (character->alternateActors)
        free_string_array(character->alternateActors, character->alternateActorsCount);

    free(character->eyeColour);
    free(character->gender);
    free(character->hairColour);

    free(character);
}
