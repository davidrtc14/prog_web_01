package com.example.demo.controller;

import com.example.demo.model.Pessoa;
import com.example.demo.utils.ListaPessoas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PessoaController {

    ListaPessoas listaPessoas = new ListaPessoas();

    @GetMapping("/pessoas")
    public String pessoas(Model model){

        if(listaPessoas.getPessoas().isEmpty()){
            listaPessoas.add(new Pessoa("Fulano de Tal", "432.321.987-66", 67.5F, 1.75F));
            listaPessoas.add(new Pessoa("Cilano de Tal", "765.115.935-12", 80.2F, 1.93F));
        }

        model.addAttribute("pessoas", listaPessoas.getPessoas());

        return "pessoas";
    }
}
