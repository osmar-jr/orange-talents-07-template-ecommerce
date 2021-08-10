package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Todos os eventos relacionados uma compra realizada com sucesso
 * devem implementar esta interface
 */
@Component
public interface EventosCompraRealizadaComSucesso {

    void processa(@NotNull @Valid Compra compra);
}
