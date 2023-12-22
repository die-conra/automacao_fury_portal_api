package br.com.kangu.solicitar;

public class Pedido {

    private String tipo;
    private String numero;
    private String serie;
    private String chave;
    private String chaveCTe;
    private String xml;
    private String numeroCli;
    private Double vlrMerc;
    private Double pesoMerc;

    public Pedido() {
    }

    public Pedido(String tipo, String numero, String serie, String chave, String chaveCTe, String xml, String numeroCli, Double vlrMerc, Double pesoMerc) {
        this.tipo = tipo;
        this.numero = numero;
        this.serie = serie;
        this.chave = chave;
        this.chaveCTe = chaveCTe;
        this.xml = xml;
        this.numeroCli = numeroCli;
        this.vlrMerc = vlrMerc;
        this.pesoMerc = pesoMerc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getChaveCTe() {
        return chaveCTe;
    }

    public void setChaveCTe(String chaveCTe) {
        this.chaveCTe = chaveCTe;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getNumeroCli() {
        return numeroCli;
    }

    public void setNumeroCli(String numeroCli) {
        this.numeroCli = numeroCli;
    }

    public Double getVlrMerc() {
        return vlrMerc;
    }

    public void setVlrMerc(Double vlrMerc) {
        this.vlrMerc = vlrMerc;
    }

    public Double getPesoMerc() {
        return pesoMerc;
    }

    public void setPesoMerc(Double pesoMerc) {
        this.pesoMerc = pesoMerc;
    }
}
