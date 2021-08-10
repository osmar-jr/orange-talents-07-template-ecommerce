package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Transacao;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusPagSeguroTransacao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoPagSeguroFormRequest implements TransacaorRetornoFormRequest {

    @NotBlank
    private String transacaoId;

    @NotNull
    @Valid
    @Enumerated(EnumType.STRING)
    private StatusPagSeguroTransacao statusCompra;

    public TransacaoPagSeguroFormRequest(@NotBlank String transacaoId,
                                         @NotNull @Valid StatusPagSeguroTransacao statusCompra) {
        this.transacaoId = transacaoId;
        this.statusCompra = statusCompra;
    }

    public Transacao toTransacao(@NotNull @Valid Compra compra, @NotNull @Valid Usuario comprador) {

        return new Transacao(this.transacaoId, this.statusCompra.normalize(), compra, comprador);
    }
}
