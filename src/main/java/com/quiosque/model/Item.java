package com.quiosque.model;

//Classe abstrata para os itens do cardápio
public abstract class Item {
    // atributos privados
    private String nome;
    private double preco;

    // Construtor
    public Item(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Método abstrato que toda classe filha deve implementar
    public abstract String getDescricaoCompleta();

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}