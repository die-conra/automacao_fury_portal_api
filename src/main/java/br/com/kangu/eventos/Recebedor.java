package br.com.kangu.eventos;

public class Recebedor {
    private String nome;
    private String documento;
    private String assinatura;

    public Recebedor() {
    }

    public Recebedor(String nome, String documento, String assinatura) {
        this.nome = nome;
        this.documento = documento;
        this.assinatura = assinatura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }
}
