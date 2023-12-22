package br.com.kangu.etiqueta;

import java.util.ArrayList;
import java.util.List;

public class Etiqueta {
    private String modelo;
    List<String> codigo = new ArrayList<>();

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<String> getCodigo() {
        return codigo;
    }

    public void setCodigo(List<String> codigo) {
        this.codigo = codigo;
    }
}

