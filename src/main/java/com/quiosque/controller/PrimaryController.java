package com.quiosque.controller;

import com.quiosque.model.Bebida;
import com.quiosque.model.Lanche;
import com.quiosque.model.Pedido;
import com.quiosque.model.StatusPedido;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//Controlador da tela de autoatendimento do cliente
public class PrimaryController {

    // quantidade
    @FXML private Label lblQtdSimples;
    @FXML private Label lblQtdMedio;
    @FXML private Label lblQtdGrande;
    @FXML private Label lblQtdBatata;
    @FXML private Label lblQtdCoca;
    @FXML private Label lblQtdGuarana;
    @FXML private Label lblTotal; 
    
    // status do pedido
    @FXML private Label lblNumeroPedido;
    @FXML private Label lblStatusPedido;
    @FXML private Button btnReceber;
    @FXML private Button btnFinalizar;
    @FXML private javafx.scene.layout.GridPane gridProdutos;

    // comunicação com a gerência
    public static PrimaryController instancia;

    // Dados do pedido atual
    private Pedido pedidoAtual;
    private Lanche hamburguerSimples = new Lanche("Hambúrguer Simples", 20.00);
    private Lanche hamburguerMedio = new Lanche("Hambúrguer Médio", 28.00);
    private Lanche hamburguerGrande = new Lanche("Hambúrguer Grande", 36.00);
    private Lanche batataFrita = new Lanche("Batata Frita", 12.00);
    private Bebida cocaCola = new Bebida("Coca-Cola Lata", 6.00, 350);
    private Bebida guarana = new Bebida("Guaraná Lata", 6.00, 350);

    private int contSimples = 0;
    private int contMedio = 0;
    private int contGrande = 0;
    private int contBatata = 0;
    private int contCoca = 0;
    private int contGuarana = 0;

    // Ações dos botões de adicionar e remover itens
    @FXML
    void adicionarSimples(ActionEvent event) { contSimples++; lblQtdSimples.setText(String.valueOf(contSimples)); pedidoAtual.adicionarItem(hamburguerSimples); atualizarTotalNaTela(); }
    @FXML
    void removerSimples(ActionEvent event) { if (contSimples > 0) { contSimples--; lblQtdSimples.setText(String.valueOf(contSimples)); pedidoAtual.removerItem(hamburguerSimples); atualizarTotalNaTela(); } }

    @FXML
    void adicionarMedio(ActionEvent event) { contMedio++; lblQtdMedio.setText(String.valueOf(contMedio)); pedidoAtual.adicionarItem(hamburguerMedio); atualizarTotalNaTela(); }
    @FXML
    void removerMedio(ActionEvent event) { if (contMedio > 0) { contMedio--; lblQtdMedio.setText(String.valueOf(contMedio)); pedidoAtual.removerItem(hamburguerMedio); atualizarTotalNaTela(); } }

    @FXML
    void adicionarGrande(ActionEvent event) { contGrande++; lblQtdGrande.setText(String.valueOf(contGrande)); pedidoAtual.adicionarItem(hamburguerGrande); atualizarTotalNaTela(); }
    @FXML
    void removerGrande(ActionEvent event) { if (contGrande > 0) { contGrande--; lblQtdGrande.setText(String.valueOf(contGrande)); pedidoAtual.removerItem(hamburguerGrande); atualizarTotalNaTela(); } }

    @FXML
    void adicionarBatata(ActionEvent event) { contBatata++; lblQtdBatata.setText(String.valueOf(contBatata)); pedidoAtual.adicionarItem(batataFrita); atualizarTotalNaTela(); }
    @FXML
    void removerBatata(ActionEvent event) { if (contBatata > 0) { contBatata--; lblQtdBatata.setText(String.valueOf(contBatata)); pedidoAtual.removerItem(batataFrita); atualizarTotalNaTela(); } }

    @FXML
    void adicionarCoca(ActionEvent event) { contCoca++; lblQtdCoca.setText(String.valueOf(contCoca)); pedidoAtual.adicionarItem(cocaCola); atualizarTotalNaTela(); }
    @FXML
    void removerCoca(ActionEvent event) { if (contCoca > 0) { contCoca--; lblQtdCoca.setText(String.valueOf(contCoca)); pedidoAtual.removerItem(cocaCola); atualizarTotalNaTela(); } }

    @FXML
    void adicionarGuarana(ActionEvent event) { contGuarana++; lblQtdGuarana.setText(String.valueOf(contGuarana)); pedidoAtual.adicionarItem(guarana); atualizarTotalNaTela(); }
    @FXML
    void removerGuarana(ActionEvent event) { if (contGuarana > 0) { contGuarana--; lblQtdGuarana.setText(String.valueOf(contGuarana)); pedidoAtual.removerItem(guarana); atualizarTotalNaTela(); } }

    // Métodos de conclusão e status do pedido
    @FXML
    void finalizarPedido(ActionEvent event) {
        if(pedidoAtual.getItens().isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }else {
                SecondaryController.instancia.receberNovoPedido(pedidoAtual);
                System.out.println("Pedido " + pedidoAtual.getNumeroAtendimento() + " enviado para a cozinha.");
            }
        
        pedidoAtual.setStatus(StatusPedido.EM_PREPARO);
        lblStatusPedido.setText("EM PREPARO (COZINHA)");
        
        btnFinalizar.setVisible(false);
        
        gridProdutos.setDisable(true);

        btnReceber.setVisible(true);
        btnReceber.setDisable(true);

        
    }

    @FXML
    void receberPedido(ActionEvent event) {
        pedidoAtual.setStatus(StatusPedido.ENTREGUE);
        System.out.println("Pedido " + pedidoAtual.getNumeroAtendimento() + " Entregue! Total: R$ " + pedidoAtual.calcularTotal());
        SecondaryController.instancia.registrarEntrega(pedidoAtual);
        limparTela(); // Prepara para o próximo cliente
    }

    @FXML
    void cancelarPedido(ActionEvent event) {
        System.out.println("O cliente CANCELOU o pedido número " + pedidoAtual.getNumeroAtendimento());
        
        if (pedidoAtual.getStatus() == StatusPedido.EM_PREPARO || pedidoAtual.getStatus() == StatusPedido.PRONTO) {
            SecondaryController.instancia.registrarCancelamento(pedidoAtual);
        }
        limparTela();
    }

    private void atualizarTotalNaTela() {
        double valor = pedidoAtual.calcularTotal();
        lblTotal.setText(String.format("Total: R$ %.2f", valor));
    }

    private void limparTela() {
        pedidoAtual = new Pedido(); 
        
        lblNumeroPedido.setText(String.valueOf(pedidoAtual.getNumeroAtendimento()));
        lblStatusPedido.setText("FAZENDO PEDIDO");
        
       if (btnFinalizar != null) {
            btnFinalizar.setVisible(true);
            btnFinalizar.setDisable(false);
        }
        if (btnReceber != null) {
            btnReceber.setVisible(false);
        }
        if (gridProdutos != null) {
            gridProdutos.setDisable(false); // Libera os lanches novamente
        }

        contSimples = 0; contMedio = 0; contGrande = 0;
        contBatata = 0; contCoca = 0; contGuarana = 0;

        lblQtdSimples.setText("0"); lblQtdMedio.setText("0"); lblQtdGrande.setText("0");
        lblQtdBatata.setText("0"); lblQtdCoca.setText("0"); lblQtdGuarana.setText("0");
        
        atualizarTotalNaTela();
    }

    @FXML
    public void initialize() {
        instancia = this; 
        limparTela();
    }

    public void liberarBotaoReceber() {
        btnReceber.setDisable(false);
        lblStatusPedido.setText("PRONTO PARA RETIRADA");
    }
}