package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Transacao;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface TransacaorRetornoFormRequest {

    Transacao toTransacao(@NotNull @Valid Compra compra,
                          @NotNull @Valid Usuario comprador);
}
