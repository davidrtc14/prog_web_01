package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pessoa {
    private String nome;
    private String cpf;
    private float peso;
    private float altura;
    private float imc;

    public Pessoa(String nome, String cpf, float peso, float altura) {
        this.nome = nome;
        this.cpf = cpf;
        this.peso = peso;
        this.altura = altura;
        calcularIMC();
    }

    public void calcularIMC(){
        this.imc = (float) (this.peso / (Math.pow(this.altura, 2)));
    }
}
