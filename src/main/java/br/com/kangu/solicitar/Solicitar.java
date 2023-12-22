package br.com.kangu.solicitar;

import java.util.List;

public class Solicitar {
    private String origem;
    private Boolean gerarPdf;
    private String formatoPdf;
    private Pedido pedido;
    private Remetente remetente;
    private Destinatario destinatario;
    private List<Volume> volumes;

    private Volume volume;

    private List<Produto> produtos;
    private String pontoPostagem;
    private String pontoEntrega;
    private String transportadora;
    private String referencia;
    private List<String> servicos;
    private Boolean forceFury;
    private Boolean forcePortal;

    public Solicitar() {
    }

    public String getOrigem() {
        return origem;
    }

    public Solicitar setOrigem(String origem) {
        this.origem = origem;
        return this;
    }

    public Boolean getGerarPdf() {
        return gerarPdf;
    }

    public Solicitar setGerarPdf(Boolean gerarPdf) {
        this.gerarPdf = gerarPdf;
        return this;
    }

    public String getFormatoPdf() {
        return formatoPdf;
    }

    public Solicitar setFormatoPdf(String formatoPdf) {
        this.formatoPdf = formatoPdf;
        return this;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Solicitar setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public Remetente getRemetente() {
        return remetente;
    }

    public Solicitar setRemetente(Remetente remetente) {
        this.remetente = remetente;
        return this;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public Solicitar setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
        return this;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public Solicitar setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
        return this;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Solicitar setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        return this;
    }

    public String getPontoPostagem() {
        return pontoPostagem;
    }

    public Solicitar setPontoPostagem(String pontoPostagem) {
        this.pontoPostagem = pontoPostagem;
        return this;
    }

    public String getPontoEntrega() {
        return pontoEntrega;
    }

    public Solicitar setPontoEntrega(String pontoEntrega) {
        this.pontoEntrega = pontoEntrega;
        return this;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public Solicitar setTransportadora(String transportadora) {
        this.transportadora = transportadora;
        return this;
    }

    public String getReferencia() {
        return referencia;
    }

    public Solicitar setReferencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public List<String> getServicos() {
        return servicos;
    }

    public Solicitar setServicos(List<String> servicos) {
        this.servicos = servicos;
        return this;
    }

    public Boolean getForceFury() {
        return forceFury;
    }

    public Solicitar setForceFury(Boolean forceFury) {
        this.forceFury = forceFury;
        return this;
    }

    public Boolean getForcePortal() {
        return forcePortal;
    }

    public Solicitar setForcePortal(Boolean forcePortal) {
        this.forcePortal = forcePortal;
        return this;
    }

    public Volume getVolume() {
        return volume;
    }

    public Solicitar setVolume(Volume volume) {
        this.volume = volume;
        return this;
    }
}




