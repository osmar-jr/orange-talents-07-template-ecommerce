package br.com.zupacademy.osmarjunior.mercadolivre.controller.dto;

import br.com.zupacademy.osmarjunior.mercadolivre.model.*;
import br.com.zupacademy.osmarjunior.mercadolivre.utils.Opinioes;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class DetalheProdutoDto {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Double mediaNotas;
    private Integer quantidadeDeNotas;
    private Set<DetalheProdutoCaracteristicaDto> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String, String>> opinioes;

    public DetalheProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristicaDto::new);
        this.linksImagens = produto.mapeiaLinksImagens(ImagemProduto::getUrl);
        this.perguntas = produto.mapeiaPerguntas(Pergunta::getTitulo);

        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });

        this.mediaNotas = opinioes.media();
        this.quantidadeDeNotas = opinioes.getTotalDeNotas();
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

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQuantidadeDeNotas() {
        return quantidadeDeNotas;
    }

    public Set<DetalheProdutoCaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }
}
