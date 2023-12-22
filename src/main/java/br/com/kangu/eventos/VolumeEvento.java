package br.com.kangu.eventos;

import br.com.kangu.commons.Endereco;

public class VolumeEvento {
    private String numero;
    private String manifesto;
    private String acao;
    private String data;
    private Ocorrencia ocorrencia;
    private Recebedor recebedor;
    private String observacao;
    private Parceiro parceiro;
    public VolumeEvento() {
    }

    public VolumeEvento(String numero, String manifesto, String acao, String data, Ocorrencia ocorrencia, Recebedor recebedor, String observacao, Parceiro parceiro) {
        this.numero = numero;
        this.manifesto = manifesto;
        this.acao = acao;
        this.data = data;
        this.ocorrencia = ocorrencia;
        this.recebedor = recebedor;
        this.observacao = observacao;
        this.parceiro = parceiro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getManifesto() {
        return manifesto;
    }

    public void setManifesto(String manifesto) {
        this.manifesto = manifesto;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Recebedor getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Recebedor recebedor) {
        this.recebedor = recebedor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }
}
