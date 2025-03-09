import pandas as pd
from sklearn.model_selection import train_test_split, GridSearchCV, RandomizedSearchCV
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from skopt import BayesSearchCV
from skopt.space import Integer, Categorical

# Carregar os dados
train_data = pd.read_csv(r'Lista 3\train.csv')
test_data = pd.read_csv(r'Lista 3\test.csv')
gender_submission = pd.read_csv(r'Lista 3\gender_submission.csv')

# Pré-processamento básico
def preprocess_data(df):
    df = df.drop(['PassengerId', 'Name', 'Ticket', 'Cabin'], axis=1)  # Remover colunas irrelevantes
    df['Age'].fillna(df['Age'].median(), inplace=True)  # Preencher valores faltantes em 'Age'
    df['Embarked'].fillna(df['Embarked'].mode()[0], inplace=True)  # Preencher valores faltantes em 'Embarked'
    df['Fare'].fillna(df['Fare'].median(), inplace=True)  # Preencher valores faltantes em 'Fare'
    df = pd.get_dummies(df, columns=['Sex', 'Embarked'], drop_first=True)  # Codificar variáveis categóricas
    return df

train_data = preprocess_data(train_data)
test_data = preprocess_data(test_data)

# Definir features e target
X = train_data.drop('Survived', axis=1)
y = train_data['Survived']

# Dividir os dados em treino e teste
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# Questão 01: Árvore de decisão com critério Entropy e Gini
def questao_01():
    # Árvore com critério Entropy
    tree_entropy = DecisionTreeClassifier(criterion='entropy', random_state=42)
    tree_entropy.fit(X_train, y_train)
    y_pred_entropy = tree_entropy.predict(X_test)
    print("Acurácia (Entropy):", accuracy_score(y_test, y_pred_entropy))

    # Árvore com critério Gini
    tree_gini = DecisionTreeClassifier(criterion='gini', random_state=42)
    tree_gini.fit(X_train, y_train)
    y_pred_gini = tree_gini.predict(X_test)
    print("Acurácia (Gini):", accuracy_score(y_test, y_pred_gini))


# Questão 02: Impacto de outros hiperparâmetros
def questao_02():
    # Testar diferentes valores para max_depth, max_features e min_samples_leaf
    params = {
        'max_depth': [3, 5, 7, None],
        'max_features': ['sqrt', 'log2', None],
        'min_samples_leaf': [1, 2, 4]
    }

    tree = DecisionTreeClassifier(random_state=42)
    grid_search = GridSearchCV(tree, params, cv=5, scoring='accuracy')
    grid_search.fit(X_train, y_train)

    print("\nMelhores hiperparâmetros encontrados:")
    print(grid_search.best_params_)
    print("Acurácia com melhores hiperparâmetros:", grid_search.best_score_)


# Questão 03: Otimizadores de hiperparâmetros
def questao_03():
    # GridSearchCV
    param_grid = {
        'max_depth': [3, 5, 7, None],
        'min_samples_leaf': [1, 2, 4],
        'criterion': ['gini', 'entropy']
    }
    grid_search = GridSearchCV(DecisionTreeClassifier(), param_grid, cv=5, scoring='accuracy')
    grid_search.fit(X_train, y_train)
    print("\nMelhores parâmetros (GridSearchCV):", grid_search.best_params_)
    print("Acurácia (GridSearchCV):", grid_search.best_score_)

    # RandomizedSearchCV
    randomized_search = RandomizedSearchCV(DecisionTreeClassifier(), param_grid, cv=5, n_iter=10, scoring='accuracy', random_state=42)
    randomized_search.fit(X_train, y_train)
    print("\nMelhores parâmetros (RandomizedSearchCV):", randomized_search.best_params_)
    print("Acurácia (RandomizedSearchCV):", randomized_search.best_score_)

    # BayesSearchCV
    bayes_search = BayesSearchCV(
        DecisionTreeClassifier(),
        {
            'max_depth': Integer(3, 10),
            'min_samples_leaf': Integer(1, 5),
            'criterion': Categorical(['gini', 'entropy'])
        },
        cv=5,
        n_iter=10,
        scoring='accuracy',
        random_state=42
    )
    bayes_search.fit(X_train, y_train)
    print("\nMelhores parâmetros (BayesSearchCV):", bayes_search.best_params_)
    print("Acurácia (BayesSearchCV):", bayes_search.best_score_)

# Executar as questões
print("=== Questão 01 ===")
questao_01()

print("\n=== Questão 02 ===")
questao_02()

print("\n=== Questão 03 ===")
questao_03()