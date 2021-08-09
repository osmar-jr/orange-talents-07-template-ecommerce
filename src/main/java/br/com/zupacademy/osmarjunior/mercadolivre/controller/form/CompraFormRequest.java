package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.GatewayPagamento;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraFormRequest {

    @NotNull @Positive
    @ExistsId(classDomain = Produto.class, attributeName = "id")
    private Long produtoId;

    @NotNull @Positive
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento formaPagamento;

    public CompraFormRequest(@NotNull @Positive Long produtoId,
                             @NotNull @Positive Integer quantidade,
                             @NotNull @Valid GatewayPagamento formaPagamento) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getFormaPagamento() {
        return formaPagamento;
    }
}
