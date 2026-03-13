package com.example.demo.utils;

import com.example.demo.model.Pessoa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaPessoas {
    private List<Pessoa> pessoas;

    public ListaPessoas(){
        this.pessoas = new ArrayList<>();
    }

    public void add(Pessoa pessoa) {this.pessoas.add(pessoa);}

    public List<Pessoa> getPessoas(){
        return Collections.unmodifiableList(pessoas);
    }
}
