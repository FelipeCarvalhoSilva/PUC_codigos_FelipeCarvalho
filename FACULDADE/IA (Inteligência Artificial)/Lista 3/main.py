print("inicio")
import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import plotly.express as px
from IPython.display import display

print("inicio")
base = pd.read_csv(r"C:\Users\Felipe\Desktop\Faculdade\Inteligencia artifical\Lista 3\Iris.csv", sep=',')


display(base)
# índices das linhas que contém valores NaN/
missing_indices = base[base.isnull().any(axis=1)].index

# imprime apenas as linhas com valoes ausentes
display(base.iloc[missing_indices])


def trataFaltantes(df_dataset, colunas_faltantes, coluna_classe):
    '''
    Substitui os valores faltantes pela média dos outros valores do mesmo atributo
    de amostras que sejam da mesma classe.

    Parâmetros:
    - df_dataset: DataFrame com os dados
    - colunas_faltantes: Lista das colunas que contêm valores faltantes a serem tratados
    - coluna_classe: Coluna que contém as classes
    '''

    # Iterar sobre cada coluna com valores faltantes
    for coluna in colunas_faltantes:
        # Para cada classe na coluna de classe
        for classe in df_dataset[coluna_classe].unique():
            # Seleciona os dados não nulos da mesma classe
            not_null = df_dataset[~pd.isnull(df_dataset[coluna]) & (df_dataset[coluna_classe] == classe)]

            # Calcula a média dos valores da coluna para essa classe
            media_classe = not_null[coluna].mean()

            # Substitui os valores nulos pela média da classe
            df_dataset.loc[pd.isnull(df_dataset[coluna]) & (df_dataset[coluna_classe] == classe), coluna] = media_classe

    return df_dataset

# Colunas que possuem valores faltantes
colunas_com_faltantes = ['sepallength', 'sepalwidth']
nome_ultima_coluna  =base.columns[-1]

# Aplicando a função ao DataFrame base
base = trataFaltantes(base, colunas_com_faltantes,nome_ultima_coluna)

# Imprime apenas as linhas que antes possuíam valores NaN
print('\nAmostras que possuíam valores faltantes:')
display(base.iloc[missing_indices])

Nome_das_Colunas = base.columns[:-1]


df_duplicates = base[base.duplicated(subset=Nome_das_Colunas,keep=False)]
# se houver valores redundantes ou inconsistentes, imprima
if len(df_duplicates)>0:
    print('\nAmostras redundantes ou inconsistentes:')
    display(df_duplicates)
else:
    print('Não existem valores duplicados')

def delDuplicatas( df_dataset ):
    '''
    Para cada grupo de amostras duplicadas, mantém uma e apaga as demais
    '''

    # remove as amostras duplicadas, mantendo apenas a primeira ocorrencia
    df_dataset = df_dataset.drop_duplicates(keep = 'first')

    return df_dataset

base = delDuplicatas( base )

# para detectar inconsistências, a rotina abaixo obtém as amostras onde os valores
# dos atributos continuam duplicados. Neste caso, os atributos serão iguais, mas as classes serão distintas
df_duplicates = base[base.duplicated(subset=Nome_das_Colunas,keep=False)]

# se tiver valores inconsistentes, imprime
if len(df_duplicates)>0:
    print('\nAmostras inconsistentes:')
    display(df_duplicates)
else:
    print('Não existem mostras inconsistentes')


def delInconsistencias(df_dataset):
    '''
    Remove todas as amostras inconsistentes da base de dados
    '''

    df_dataset = df_dataset.drop_duplicates(subset=Nome_das_Colunas,keep=False)

    return df_dataset

base = delInconsistencias(base)

# obtém apenas as amostras onde os valores dos atributos estão duplicados
df_duplicates = base[base.duplicated(subset=Nome_das_Colunas,keep=False)]

# se tiver valores redundantes ou inconsistentes, imprime
if len(df_duplicates)>0:
    display(df_duplicates)
else:
    print('Não existem amostras redundantes ou inconsistentes')


CompiladoBase= base.describe()
display(CompiladoBase)

from sklearn.preprocessing import MinMaxScaler
# Inicializa o MinMaxScaler
scaler = MinMaxScaler()

colunas_para_normalizar = base.iloc[:, :-1]

# Ajusta e transforma os dados com o MinMaxScaler
base_normalizada = pd.DataFrame(scaler.fit_transform(colunas_para_normalizar), columns=colunas_para_normalizar.columns)

# Adiciona a última coluna não normalizada ao DataFrame normalizado
base_normalizada[base.columns[-1]] = base[base.columns[-1]]

# Exibe o DataFrame normalizado com a última coluna inalterada
print(base_normalizada)

base_normalizada.describe()

# gera um bloxplot para cada atributo
base.boxplot(figsize=(15,7))
plt.show()

# matriz de gráficos scatter
sns.pairplot(base, hue='class', height=3.5);

# mostra o gráfico usando a função show() da matplotlib
plt.show()

Classificação = base.columns[-1]
np.unique(base[Classificação], return_counts=True)

sns.countplot(x = base[Classificação]);

X_prev = base.iloc[:, 0:4]

display(X_prev)

y_classe = base.iloc[:, 4]

display(y_classe)

from sklearn.model_selection import train_test_split

display(X_prev)

display(y_classe)

display(y_classe.shape)

X_treino, X_teste, y_treino, y_teste = train_test_split(X_prev, y_classe, test_size = 0.20, random_state = 42)
display(X_treino.shape)
display(y_treino)
display(y_teste)

import pickle

with open(r'Lista 3\iris.pkl', mode = 'wb') as f:
  pickle.dump([X_treino, X_teste, y_treino, y_teste], f)