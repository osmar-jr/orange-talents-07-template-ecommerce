package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NotaFiscalFormRequest {

    @NotNull @Positive
    @ExistsId(classDomain = Compra.class, attributeName = "id")
    private Long compraId;

    @NotNull @Positive
    @ExistsId(classDomain = Usuario.class, attributeName = "id")
    private Long compradorId;

    public NotaFiscalFormRequest(@NotNull @Positive Long compraId,
                                 @NotNull @Positive Long compradorId) {
        this.compraId = compraId;
        this.compradorId = compradorId;
    }

    @Override
    public String toString() {
        return "NotaFiscalFormRequest{" +
                "compraId=" + compraId +
                ", compradorId=" + compradorId +
                '}';
    }
}
