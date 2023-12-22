package br.com.kangu.eventos;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    private List<VolumeEvento> volumes = new ArrayList<>();
    private Ocorrencia ocorrencia;
    private Recebedor recebedor;
    private Parceiro parceiro;
    private String contato;
    private String email;
    private String telefone;
    private String celular;
    private String avatar;

    public Evento() {
    }

    public Evento(List<VolumeEvento> volumes, Ocorrencia ocorrencia, Recebedor recebedor, Parceiro parceiro, String contato, String email, String telefone, String celular, String avatar) {
        this.volumes = volumes;
        this.ocorrencia = ocorrencia;
        this.recebedor = recebedor;
        this.parceiro = parceiro;
        this.contato = contato;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.avatar = avatar;
    }

    public List<VolumeEvento> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<VolumeEvento> volumes) {
        this.volumes = volumes;
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

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
