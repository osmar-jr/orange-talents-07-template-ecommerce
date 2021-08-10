package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Transacao;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusTransacao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoPayPalFormRequest implements TransacaorRetornoFormRequest{

    @NotBlank
    private String transacaoId;

    @NotNull @Min(0) @Max(1)
    private Integer statusCompra;

    public TransacaoPayPalFormRequest(@NotBlank String transacaoId,
                                      @NotNull @Min(0) @Max(1) Integer statusCompra) {
        this.transacaoId = transacaoId;
        this.statusCompra = statusCompra;
    }

    public Transacao toTransacao(@NotNull @Valid Compra compra,
                                 @NotNull @Valid Usuario comprador) {
        StatusTransacao statusTransacao = this.statusCompra == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        return new Transacao(this.transacaoId, statusTransacao, compra, comprador);
    }
}
