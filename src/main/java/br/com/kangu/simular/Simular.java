package br.com.kangu.simular;

import br.com.kangu.solicitar.Produto;
import br.com.kangu.solicitar.Volume;

import java.util.ArrayList;
import java.util.List;

public class Simular {
    private String cepOrigem;
    private String cepDestino;
    private Double vlrMerc;
    private Double pesoMerc;

    private List<Volume> volumes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<String> servicos = new ArrayList<>();
    private String ordenar;

    public Simular(String cepOrigem, String cepDestino, Double vlrMerc, Double pesoMerc, List<Volume> volumes, List<Produto> produtos, List<String> servicos, String ordenar) {
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
        this.vlrMerc = vlrMerc;
        this.pesoMerc = pesoMerc;
        this.volumes = volumes;
        this.produtos = produtos;
        this.servicos = servicos;
        this.ordenar = ordenar;
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
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

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<String> getServicos() {
        return servicos;
    }

    public void setServicos(List<String> servicos) {
        this.servicos = servicos;
    }

    public String getOrdenar() {
        return ordenar;
    }

    public void setOrdenar(String ordenar) {
        this.ordenar = ordenar;
    }
}
