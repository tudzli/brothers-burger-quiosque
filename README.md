# Brother's Burger - Sistema de Autoatendimento

Um sistema de quiosque de autoatendimento para restaurante de burgers desenvolvido em Java com JavaFX, incluindo painel de gerência em tempo real.

## 📋 Requisitos do Sistema

### Software Necessário:
- **Java Development Kit (JDK)**: Versão 11 ou superior
- **Maven**: Versão 3.6 ou superior
- **Git**: Para controle de versão

### Dependências do Projeto:
- JavaFX (para interface gráfica)
- JUnit (para testes)

## 🚀 Como Executar o Programa

### 1. Clonar o Repositório
```bash
git clone <url-do-repositorio>
cd quiosque
```

### 2. Compilar o Projeto
```bash
mvn clean compile
```

### 3. Executar a Aplicação
```bash
mvn javafx:run
```

Ou, se preferir:
```bash
mvn clean javafx:run
```

## 💻 Como Usar o Sistema

### Tela de Autoatendimento (Cliente)
A tela principal permite ao cliente:
1. **Selecionar produtos**: Clique nos botões "+" para adicionar produtos ao carrinho
2. **Remover produtos**: Clique nos botões "-" para remover produtos
3. **Visualizar total**: O total é atualizado automaticamente
4. **Finalizar pedido**: Clique em "Finalizar Pedido" para enviar para a cozinha
5. **Receber pedido**: Clique em "Receber pedido" quando o pedido estiver pronto
6. **Cancelar pedido**: Clique em "Cancelar Pedido" a qualquer momento

### Tela de Gerência
A tela de gerência permite ao gerente:
1. **Acompanhar pedidos**: Veja a lista de pedidos com seus itens detalhados
2. **Marcar como pronto**: Clique em "Marcar como PRONTO" quando o pedido estiver pronto
3. **Visualizar métricas**: Acompanhe a quantidade de pedidos atendidos, cancelados e total recebido

## 📦 Menu de Produtos

### Lanches
- Hambúrguer Simples - R$ 20,00
- Hambúrguer Médio - R$ 28,00
- Hambúrguer Grande - R$ 36,00
- Batata Frita - R$ 12,00

### Bebidas
- Coca-Cola Lata - R$ 6,00
- Guaraná Lata - R$ 6,00

## 🏗️ Arquitetura do Projeto

```
src/main/java/com/quiosque/
├── App.java                    # Classe principal da aplicação
├── model/
│   ├── Item.java              # Classe abstrata para itens
│   ├── Lanche.java            # Classe de lanches
│   ├── Bebida.java            # Classe de bebidas
│   ├── Pedido.java            # Classe que gerencia pedidos
│   └── StatusPedido.java      # Enum de status
├── controller/
│   ├── PrimaryController.java      # Controlador da tela de cliente
│   └── SecondaryController.java    # Controlador da tela de gerência
└── resources/
    └── primary.fxml           # Interface do cliente
    └── secondary.fxml         # Interface da gerência
```

## 🔧 Tecnologias Utilizadas

- **Java 11+**: Linguagem principal
- **JavaFX**: Framework para interface gráfica
- **Maven**: Gerenciador de dependências e build
- **FXML**: Markup language para UI do JavaFX

## 📚 Conceitos de Programação Implementados

- **Herança**: Classes Lanche e Bebida herdam de Item
- **Polimorfismo**: Método getDescricaoCompleta() implementado diferente em cada classe
- **Encapsulamento**: Atributos privados com getters e setters
- **Coleções**: Uso de ArrayList e LinkedHashMap
- **Padrão MVC**: Separação entre Model, View e Controller
- **Interface (FXML)**: Criação de telas via FXML

## 📝 Estrutura de Dados

### Classe Item (Abstrata)
- `nome`: String
- `preco`: double
- Métodos: getters, setters, getDescricaoCompleta()

### Classe Lanche (Estende Item)
- Implementa getDescricaoCompleta() com formatação "[Lanche]"

### Classe Bebida (Estende Item)
- Armazena tamanho em ml (não implementado)
- Implementa getDescricaoCompleta() com formatação simples

### Classe Pedido
- `numeroAtendimento`: int (auto-incrementado)
- `itens`: List<Item>
- `status`: StatusPedido
- Métodos: adicionarItem(), removerItem(), calcularTotal(), getListaPedidoFormatada()

## 🐛 Possíveis Problemas e Soluções

### Problema: "Module not found: javafx"
**Solução**: Certifique-se de que o pom.xml contém as dependências do JavaFX corretamente

### Problema: "Cannot find file primary.fxml"
**Solução**: Verifique se os arquivos FXML estão em `src/main/resources/com/quiosque/`

### Problema: Porta já em uso
**Solução**: Feche outras instâncias da aplicação antes de executar

---

## 📤 Como Enviar para o Git

### 1. Verificar Status
```bash
git status
```

### 2. Adicionar Arquivos ao Staging
Adicionar todos os arquivos:
```bash
git add .
```

Ou adicionar arquivos específicos:
```bash
git add README.md
git add src/
git add pom.xml
```

### 3. Criar um Commit
```bash
git commit -m "Descrição das alterações"
```

Exemplo:
```bash
git commit -m "Adiciona README com instruções de uso"
git commit -m "Padroniza comentários e adiciona método de formatação de pedidos"
```

### 4. Verificar Commits (Opcional)
```bash
git log
```

### 5. Enviar para o Repositório Remoto
```bash
git push origin main
```

Se for a primeira vez:
```bash
git push -u origin main
```

### Fluxo Completo de Exemplo:
```bash
# 1. Verificar alterações
git status

# 2. Adicionar arquivos
git add .

# 3. Criar commit
git commit -m "Atualiza comentários e adiciona formatação de pedidos"

# 4. Enviar para repositório
git push origin main

# 5. Verificar se foi enviado (opcional)
git log --oneline -n 5
```

### 🔑 Dicas Importantes para Git

- **Commits frequentes**: Faça commits após cada funcionalidade implementada
- **Mensagens descritivas**: Use mensagens claras em português ou inglês
- **Não commitare arquivos gerados**: `.class`, `.jar`, `target/` devem estar no `.gitignore`
- **Verificar remotes**: 
  ```bash
  git remote -v
  ```
- **Criar branches**: Para trabalhar em features:
  ```bash
  git checkout -b feature/nova-funcionalidade
  git push -u origin feature/nova-funcionalidade
  ```

---

## 👥 Autor
Desenvolvido como trabalho final de Programação 1

## 📄 Licença
[Definir licença se aplicável]

