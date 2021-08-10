package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Todos os eventos relacionados uma compra realizada com falha
 * devem implementar esta interface
 */
@Component
public interface EventosCompraRealizadaComFalha {

    void processa(@NotNull @Valid Compra compra);
}
