package com.quiosque.controller;

import com.quiosque.model.Pedido;
import com.quiosque.model.StatusPedido;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

//Controlador da tela de gerência
public class SecondaryController {

    @FXML private ListView<String> listaPedidos;
    @FXML private Button btnPronto;
    
    // Componentes das métricas
    @FXML private Label lblAtendidos;
    @FXML private Label lblCancelados;
    @FXML private Label lblRecebido;

    // Instância global para comunicação com o cliente
    public static SecondaryController instancia;
    private Pedido pedidoNaCozinha;

    // Contadores de estatísticas
    private int qtdAtendidos = 0;
    private int qtdCancelados = 0;
    private double totalRecebido = 0.0;

    @FXML
    public void initialize() {
        instancia = this;
        btnPronto.setDisable(true);
        atualizarPainelMetricas();
    }

    // Recebe um novo pedido da tela de autoatendimento
    public void receberNovoPedido(Pedido pedido) {
        this.pedidoNaCozinha = pedido;
        listaPedidos.getItems().add("[NOVO] PEDIDO Nº " + pedido.getNumeroAtendimento() + " - Valor: R$ " + pedido.calcularTotal());
        
        // Adiciona a lista formatada dos itens do pedido
        String listaFormatada = pedido.getListaPedidoFormatada();
        for (String linha : listaFormatada.split("\n")) {
            listaPedidos.getItems().add("   " + linha);
        }
        
        btnPronto.setDisable(false);
    }

    // Registra quando um pedido foi entregue ao cliente
    public void registrarEntrega(Pedido pedido) {
        qtdAtendidos++;
        totalRecebido += pedido.calcularTotal();
        atualizarPainelMetricas();
        listaPedidos.getItems().add("[RETIRADO] PEDIDO Nº " + pedido.getNumeroAtendimento() + " ENTREGUE AO CLIENTE! Total: R$ " + pedido.calcularTotal());
        listaPedidos.scrollTo(listaPedidos.getItems().size() - 1);
    }

    // Registra quando um pedido é cancelado pelo cliente
    public void registrarCancelamento(Pedido pedido) {
        qtdCancelados++;
        listaPedidos.getItems().add("[CANCELADO] PEDIDO Nº " + pedido.getNumeroAtendimento() + " CANCELADO PELO CLIENTE!");
        atualizarPainelMetricas();
        listaPedidos.scrollTo(listaPedidos.getItems().size() - 1);
        // Limpa a bancada se o pedido cancelado estava sendo preparado
        if (pedidoNaCozinha != null && pedidoNaCozinha.getNumeroAtendimento() == pedido.getNumeroAtendimento()) {
            pedidoNaCozinha = null;
            btnPronto.setDisable(true);
        }
    }

    @FXML
    void marcarComoPronto(ActionEvent event) {
        if (pedidoNaCozinha != null) {
            pedidoNaCozinha.setStatus(StatusPedido.PRONTO);
            listaPedidos.getItems().add("[PRONTO] PEDIDO Nº " + pedidoNaCozinha.getNumeroAtendimento() + " pronto para retirada!");
            listaPedidos.scrollTo(listaPedidos.getItems().size() - 1);
            PrimaryController.instancia.liberarBotaoReceber();
            btnPronto.setDisable(true);
        }
    }

    private void atualizarPainelMetricas() {
        if (lblAtendidos != null) lblAtendidos.setText("Atendidos: " + qtdAtendidos);
        if (lblCancelados != null) lblCancelados.setText("Cancelados: " + qtdCancelados);
        if (lblRecebido != null) lblRecebido.setText(String.format("Total Recebido: R$ %.2f", totalRecebido));
    }
}