<h1 align="center"> Java Cafe ☕ </h1>
O projeto desenvolvido neste repositório trata-se de um sistema de vendas simples para uma cafeteria, feita a partir da linguagem Java. A aplicação foi construído abordando os conceitos vistos em sala de aula na disciplina SCC0504 - Programação Orientada a Objetos, ministrada pelo professor Dilvan de Abreu Moreira.

---
## 🍵 Funcionalidades
O sistema é dividido em três abas: Sale, Inventory e Manager Area, sendo suas principais responsabilidades, a de venda de produtos, visualização do estoque e visualização das vendas diárias, respectivamente. Com isso, pode-se citar as principais funcionalidades do sistema:
- **Pedido de produtos:** o sistema permite ao usuário a inserção dos itens disponibilizados no menu ao pedido, além de sua remoção. Ademais, é possível visualizar o valor atual da compra.
- **Visualização e Atualização do estoque:** o sistema permite verificar a quantidade de produtos que ainda possui de determinado produto, sendo possível realizar a inserção ou remoção da quantidade de itens, caso seja necessário.
- **Visualização do histórico de vendas:** permite verificar o valor total de vendas e o número de transações de determinado dia.

---

## 💻 Como rodar 
Este guia fornece o passo a passo necessário para configurar o ambiente, compilar e executar o sistema Java Café a partir do código fonte, garantindo que o sistema funcione corretamente de forma independente na máquina desejada.

### 1. Pré-requisitos e Instalações de Softwares
  
Para compilar e rodar a aplicação, é necessário instalar o Java Development Kit (JDK). Como o projeto utiliza componentes nativos do JavaSwing, a versão mínima recomendada é JDK 17 ou superior (o projeto foi desenvolvido na versão JDK 21):
  - **No Windows:**
    -  Acesse o site oficial da Oracle (https://www.oracle.com/br/java/technologies/downloads/) e faça download do JDK 17 ou superior. A seguir, um exemplo de onde se encontra o arquivo para download, escolha o que corresponde a sua máquina.
    - Execute o instalador e siga as instruções na tela.
   <img width="1357" height="524" alt="image" src="https://github.com/user-attachments/assets/bb3a99a8-f2c6-47b7-8d09-2846e7296437" />
    
    
  -**No Linux:**
    - Abra o terminal e execute os seguintes comandos para atualizar os repositórios e instalar o OpenJDK de forma nativa:
```
sudo apt update && sudo apt upgrade
sudo apt install openjdk-21-jdk openjdk-21-jre -y
```
  - Verifique se a instalação ocorreu corretamente, abra o terminal e digite o comando abaixo para garantir que o compilador do Java está acessível. Ambos os comandos devem retornar a versão instalada com sucesso, como mostra a imagem logo abaixo.
```
java -version
javac -version
```

<img width="964" height="134" alt="image" src="https://github.com/user-attachments/assets/b7bf0d87-27b8-4154-802f-38393e1d1747" />

### 2. Download e Estrutura do Projeto
  - Dentro do repositório (https://github.com/karen-kamo/Java-Cafe) clique no botão ‘Code’ e clique em ‘Download ZIP’, como mostra a figura:

<img width="484" height="364" alt="image" src="https://github.com/user-attachments/assets/96397635-7dd2-4535-993e-2ccb7001ad36" />

  - Vá para a pasta que contém o arquivo ZIP instalado.
  - Extraia o arquivo.

A pasta extraída contém a pasta src e dentro dela, o projeto segue a seguinte estrutura de arquivo:

```  
📂 JavaCafe/
├── 📂 src/
│   ├── 📂 model/
│   │   ├── Inventory.java
│   │   ├── Order.java
│   │   ├── Product.java
│   │   └── SaleSummary.java
│   ├── 📂 controller/
│   │   └── POSController.java
│   ├── 📂 persistence/
│   │   └── DataManager.java
│   └── 📂 view/
│       ├── JavaCafeGUI.java
│       └── 📂 imagens/
├── inventory.csv
└── sales_history.csv
```

É estritamente necessário que a estrutura do arquivo siga o apresentado para o correto funcionamento da aplicação.


### 3. Compilação e Execução via Linha de Comando (Terminal)
Para garantir o isolamento e independência de ambientes de desenvolvimento, os procedimentos abaixo descrevem o processo manual de compilação através do terminal:
  - Abra o terminal na pasta raiz do projeto (JavaCafe/).
  - Crie uma pasta chamada bin para organizar os arquivos compilados e execute o comando de compilação:
  ```
  # No Windows
  mkdir bin
  javac -d bin src\model\*.java src\persistence\*.java src\controller\*.java src\view\*.java

  # No Linux
  mkdir bin
  javac -d bin src/model/*.java src/persistence/*.java src/controller/*.java src/view/JavaCafeGUI.java
  ```

  - Copie a pasta de imagens para dentro do diretório bin para que a interface gráfica consiga carregar os ícones dos produtos:
  ```
  # No Windows
  xcopy src\view\imagens bin\view\imagens /E /I /Y

  # No Linux
  mkdir -p bin/view/imagens && cp src/view/imagens/* bin/view/imagens/
  ```
  
  - Copie os arquivos de banco de dados (.csv) existentes para a pasta de binários para garantir a persistência dos dados:
  ```
  # No Windows
  copy src\*.csv bin\

  # No Linux
  cp src/*.csv bin/
  ```

  - Vá para a pasta /bin
  ```
  cd bin
  ```

  - Agora, execute o sistema informando a pasta bin como origem e chamando a classe principal:
  ```
  java view.JavaCafeGUI
  ```

  - Pronto! O programa está rodando.✨
<img width="1092" height="658" alt="image" src="https://github.com/user-attachments/assets/73b4693f-eb10-4819-abf7-5d8020b7ede7" />

---

## 📋 Integrantes do grupo
``` 
Juan Jacomassi
Karen Nanamy Kamo
Rebeca de Oliveira Silva
``` 
