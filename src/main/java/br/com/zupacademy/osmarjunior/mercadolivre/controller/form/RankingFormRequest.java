package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class RankingFormRequest {

    @NotNull @Positive
    @ExistsId(classDomain = Compra.class, attributeName = "id")
    private Long compraId;

    @NotNull @Positive
    @ExistsId(classDomain = Usuario.class, attributeName = "id")
    private Long vendedorId;

    public RankingFormRequest(@NotNull @Positive Long compraId,
                              @NotNull @Positive Long vendedorId) {
        this.compraId = compraId;
        this.vendedorId = vendedorId;
    }

    @Override
    public String toString() {
        return "RankingFormRequest{" +
                "compraId=" + compraId +
                ", vendedorId=" + vendedorId +
                '}';
    }
}
