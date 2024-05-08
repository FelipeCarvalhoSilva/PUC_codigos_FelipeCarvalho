/** 
 * //FELIPE--> TIVE QUE USAR O PROGRAMA DO MONITOR PARA LER OS ARQUIVOS EM C PORQUE TUDO QUE EU TENTEI DAVA ERRADO NA HORA DE LER O ULTIMO ATRIBUTO, EU TENTEI POR 3 DIAS zz
 * Q02 - TP02 - AEDS II
 * 
 * @author Thomas Neuenschwander
 * @since 26/04/2024
 * 
 * [GitHub](https://github.com/thomneuenschwander)
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

//C:/Users/Felipe/Desktop/C/.vscode/TP2Q2/tmp/characters.csv
#define FILE_PATH "C:/Users/Felipe/Desktop/C/.vscode/TP2Q2/tmp/characters.csv"

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

void free_string_array(char **array, int length);
void free_character(Character *character);
int getMax(int *array, int n) {
    int maior = array[0];

    for (int i = 1; i < n; i++) {
        if(maior < array[i]){
            maior = array[i];
        }
    }
    return maior;
}
void radixsort(int *array, int n) {
    //Array para contar o numero de ocorrencias de cada elemento
    int max = getMax(array, n);
    for (int exp = 1; max/exp > 0; exp *= 10) {
            radcountingSort(array, n, exp);
    }
}

  void radcountingSort(int *array, int n, int exp) {
        int count[10];
        int output[n];

        //Inicializar cada posicao do array de contagem 
        for (int i = 0; i < 10; count[i] = 0, i++);

        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; i++) {
            count[(array[i]/exp) % 10]++;
        }

        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for (int i = 1; i < 10; i++) {
            count[i] += count[i-1];
        }

        //Ordenando
        for (int i = n-1; i >= 0; i--) {
            output[count[(array[i]/exp) % 10] - 1] = array[i];
            count[(array[i]/exp) % 10]--;
        }

        //Copiando para o array original
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }
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
        if (strcmp(wordRead, "VERDADEIRO") == 0)
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

    printf("[");
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
    

    fclose(file);
    char* inputa = (char*)malloc(256 * sizeof(char));
    
    while (strcmp(inputa, "FIM") != 0) {
        scanf(" %[^\n]", inputa); 
    if (strcmp(inputa, "FIM") == 0) break;
    
    bool found = false;
    for (int i = 0; i < inseridosIndex; i++) {
        if (strcmp(inputa, inseridos[i].name) == 0) {
            found = true;
            break; 
        }
    }
    int c;
        while ((c = getchar()) != '\n' && c != EOF);
}

    free(inputa);

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