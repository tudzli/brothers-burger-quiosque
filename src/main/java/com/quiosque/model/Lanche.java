package com.quiosque.model;

//Lanche é do tipo item, logo extends item
public class Lanche extends Item {

    // Construtor
    public Lanche(String nome, double preco) {
        super(nome, preco);
    }

    //método para retornar a descrição completa do item
    @Override
    public String getDescricaoCompleta() {
        return "[Lanche] " + getNome() + " - R$ " + String.format("%.2f", getPreco());
    }
}