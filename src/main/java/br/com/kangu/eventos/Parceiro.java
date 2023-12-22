package br.com.kangu.eventos;

import br.com.kangu.commons.Endereco;

public class Parceiro {
    private String nome;
    private String cnpjCpf;
    private Endereco endereco;

    public Parceiro() {
    }

    public Parceiro(String nome, String cnpjCpf, Endereco endereco) {
        this.nome = nome;
        this.cnpjCpf = cnpjCpf;
        this.endereco = endereco;
    }
}
