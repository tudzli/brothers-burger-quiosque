package com.quiosque.model;

import java.util.ArrayList;
import java.util.List;

//Classe que representa um pedido do cliente
public class Pedido {
    // Gerador de números únicos para cada pedido
    private static int geradorAtendimento = 1; 

    private int numeroAtendimento;
    private List<Item> itens;
    private StatusPedido status;

    // Construtor
    public Pedido() {
        this.numeroAtendimento = geradorAtendimento++;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.EM_PREPARO;
    }

    // Métodos para adicionar e remover itens
    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }

    // Calcula o total do pedido
    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    // Gera lista formatada do pedido com quantidades
    public String getListaPedidoFormatada() {
        StringBuilder sb = new StringBuilder();
        java.util.Map<String, Integer> contagem = new java.util.LinkedHashMap<>();
        
        // Contar ocorrências de cada item
        for (Item item : itens) {
            String nome = item.getNome();
            contagem.put(nome, contagem.getOrDefault(nome, 0) + 1);
        }
        
        // Montar string formatada
       for (java.util.Map.Entry<String, Integer> entry : contagem.entrySet()) {
            // entry.getValue() é a quantidade que contamos, entry.getKey() é o nome do lanche
            sb.append(entry.getValue()).append(" x ").append(entry.getKey()).append("\n");
        }
        
        return sb.toString().trim();
    }

    // Getters e Setters
    public int getNumeroAtendimento() { return numeroAtendimento; }
    public List<Item> getItens() { return itens; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
}