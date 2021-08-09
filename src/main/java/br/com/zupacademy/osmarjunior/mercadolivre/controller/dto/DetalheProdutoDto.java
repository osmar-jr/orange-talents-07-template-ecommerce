package br.com.zupacademy.osmarjunior.mercadolivre.controller.dto;

import br.com.zupacademy.osmarjunior.mercadolivre.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetalheProdutoDto {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Double mediaDeNota;
    private Integer quantidadeDeNotas;
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    private Set<ImagemProduto> linksImagens = new HashSet<>();
    private List<Pergunta> perguntas = new ArrayList<>();
    private List<Opiniao> opinioes = new ArrayList<>();

    public DetalheProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.quantidadeDeNotas = produto.getOpinioes().size();
        this.caracteristicas = produto.getCaracteristicas();
        this.linksImagens = produto.getImagensProduto();
        this.perguntas = produto.getPerguntas();
        this.opinioes = produto.getOpinioes();
        this.mediaDeNota = produto.getOpinioes()
                .stream()
                .mapToDouble(Opiniao::getNota)
                .average().orElse(0.0);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Double getMediaDeNota() {
        return mediaDeNota;
    }

    public Integer getQuantidadeDeNotas() {
        return quantidadeDeNotas;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getLinksImagens() {
        return linksImagens;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }
}
