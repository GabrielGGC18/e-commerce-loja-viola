package br.unitins.tp1.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Viola extends DefaultEntity {

    private String modelo;
    private String marca;
    private String afinacao;
    private LocalDate fabricacao;
    private Double preco;
    private Integer cordas;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAfinacao() {
        return afinacao;
    }

    public void setAfinacao(String afinacao) {
        this.afinacao = afinacao;
    }

    public LocalDate getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(LocalDate fabricacao) {
        this.fabricacao = fabricacao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getCordas() {
        return cordas;
    }

    public void setCordas(Integer cordas) {
        this.cordas = cordas;
    }

}
