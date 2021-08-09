package br.com.zupacademy.osmarjunior.mercadolivre.controller.dto;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Caracteristica;

public class DetalheProdutoCaracteristicaDto {


    private String nome;
    private String descricao;

    public DetalheProdutoCaracteristicaDto(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
