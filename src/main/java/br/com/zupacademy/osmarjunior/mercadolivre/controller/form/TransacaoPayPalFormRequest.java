package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Transacao;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusCompra;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoPayPalFormRequest {

    @NotNull @ExistsId(classDomain = Compra.class, attributeName = "id")
    private Long compraId;

    @NotBlank
    private String pagamentoId;

    @NotNull
    @Valid
    @Enumerated(EnumType.ORDINAL)
    private StatusCompra statusCompra;

    public TransacaoPayPalFormRequest(@NotNull Long compraId,
                                      @NotBlank String pagamentoId,
                                      @NotNull @Valid StatusCompra statusCompra) {
        this.compraId = compraId;
        this.pagamentoId = pagamentoId;
        this.statusCompra = statusCompra;
    }

    public Long getCompraId() {
        return compraId;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    @Override
    public String toString() {
        return "TransacaoPagSeguroFormRequest{" +
                "compraId=" + compraId +
                ", pagamentoId='" + pagamentoId + '\'' +
                ", statusPagamento=" + statusCompra +
                '}';
    }

    public Transacao toTransacao(@NotNull @Valid Usuario comprador,
                                 @NotNull @Valid Compra compra) {

        return new Transacao(this.pagamentoId, this.statusCompra, compra, comprador);
    }
}
