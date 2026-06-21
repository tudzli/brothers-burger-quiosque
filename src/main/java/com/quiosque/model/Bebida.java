package com.quiosque.model;


//Bebiba é do tipo item, logo extends item.
public class Bebida extends Item {
    

    // Construtor
    public Bebida(String nome, double preco, int tamanhoMl) {
        super(nome, preco);
        
    }

    //método para retornar a descrição completa do item
    @Override
    public String getDescricaoCompleta() {
        return getNome() + " R$ " + String.format("%.2f", getPreco());
    }

    // Getters e Setters
    public int getTamanhoMl() { return 0; }
    public void setTamanhoMl(int tamanhoMl) {  }
}