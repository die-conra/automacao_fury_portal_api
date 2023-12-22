package br.com.kangu.solicitar;

public class Volume {

    private Double peso;
    private Double altura;
    private Double largura;
    private Double comprimento;
    private String tipo;
    private String produto;
    private String ean;
    private Double valor;
    private Integer quantidade;
    private String numeroCli;

    public Volume() {
    }

    public Volume(Double peso, Double altura, Double largura, Double comprimento, String tipo, String produto, String ean, Double valor, Integer quantidade, String numeroCli) {
        this.peso = peso;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.tipo = tipo;
        this.produto = produto;
        this.ean = ean;
        this.valor = valor;
        this.quantidade = quantidade;
        this.numeroCli = numeroCli;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getNumeroCli() {
        return numeroCli;
    }

    public void setNumeroCli(String numeroCli) {
        this.numeroCli = numeroCli;
    }
}
