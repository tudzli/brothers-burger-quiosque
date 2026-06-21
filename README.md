# Brother's Burger - Sistema de Autoatendimento

Este é um projeto acadêmico de um sistema de autoatendimento para uma lanchonete, desenvolvido em JavaFX. O sistema simula o fluxo completo de um pedido, integrando a interface do cliente (Totem) com o painel de controle da cozinha (Gerência) em tempo real.

## Tecnologias Utilizadas
* **Java 21** (ou superior)
* **JavaFX** (Interface Gráfica)
* **Maven** (Gerenciamento de Dependências e Build)
* **Padrão Arquitetural:** MVC (Model-View-Controller)

## Arquitetura do Sistema
O projeto foi estruturado utilizando o padrão **MVC** e conceitos fundamentais de **Programação Orientada a Objetos** (Herança, Polimorfismo e Encapsulamento):
* **Model** (`com.quiosque.model`): Contém as regras de negócio, herança (`Item`, `Lanche`, `Bebida`), agregação de pedidos e uma lógica matemática via `HashMap` para agrupar quantidades de itens repetidos.
* **View** (`src/main/resources/...`): Telas `.fxml` construídas no Scene Builder e os recursos visuais (imagens).
* **Controller** (`com.quiosque.controller`): Classes `PrimaryController` (Totem do Cliente) e `SecondaryController` (Painel da Cozinha) que gerenciam os eventos de tela e se comunicam de forma síncrona.

## Como Executar o Projeto

O projeto já contém todos os arquivos estáticos (FXML e Imagens) no repositório, não necessitando de configurações adicionais de diretório. 

### Opção 1: Via VSCode (Recomendado)
Para executar diretamente pelo Visual Studio Code sem necessidade de instalação global do Maven no Windows:
1. Faça o clone do repositório rodando no terminal: `git clone https://github.com/tudzli/brothers-burger-quiosque.git`
2. Abra a pasta do projeto no VSCode.
3. Certifique-se de ter a extensão **Extension Pack for Java** instalada.
4. Navegue até o explorador de arquivos: `src/main/java/com/quiosque/App.java`.
5. Clique no atalho **Run** (Executar) que aparece logo acima do método `public static void main(String[] args)`.

### Opção 2: Via Terminal (Com Maven Instalado)
Se o ambiente possuir o Apache Maven configurado nas variáveis do sistema:
1. Abra o terminal na raiz do projeto.
2. Compile o projeto com o comando: `mvn clean install`
3. Inicie a aplicação com o comando: `mvn javafx:run`

*(Nota: Ao iniciar o programa, as duas janelas — Cliente e Gerência — serão abertas simultaneamente. O fechamento de qualquer uma das janelas encerra os processos em segundo plano).*

## 🧪 Roteiro de Testes e Funcionalidades

Para validar todas as regras de negócio e a comunicação entre as telas, sugerimos os seguintes cenários:

### 1. Fluxo Feliz e Agrupamento Inteligente
* Na tela **Cliente**, adicione múltiplos lanches iguais (ex: 3x Hambúrguer Médio, 2x Coca-Cola).
* Clique em **Finalizar Pedido**.
* *Validação:* A tela da **Gerência** receberá o pedido. Os itens não aparecerão soltos, mas sim matematicamente agrupados com a tag `[NOVO]`. O botão "Marcar como PRONTO" será habilitado.
* Clique em **Marcar como PRONTO** na Gerência. O botão **Receber Pedido** será liberado para o cliente.
* Ao concluir a retirada, as métricas da gerência ("Atendidos" e "Total Recebido") são incrementadas automaticamente.

### 2. Cancelamento de Pedido
* Inicie um pedido na tela do **Cliente** e clique em **Finalizar Pedido**.
* Com o pedido aguardando preparo, clique em **Cancelar Pedido**.
* *Validação:* A tela do cliente é resetada. Na **Gerência**, a bancada é limpa, a tela exibe a tag `[CANCELADO]`, e a métrica de "Cancelados" sobe +1.

### 3. Validação de Carrinho Vazio
* Com a tela do **Cliente** sem itens selecionados, clique diretamente em **Finalizar Pedido**.
* *Validação:* O sistema bloqueia a ação, evitando que pedidos zerados ("fantasmas") cheguem à cozinha.
