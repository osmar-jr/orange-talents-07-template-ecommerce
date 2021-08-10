package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusCompra;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AtualizaStatusCompra implements EventosCompraRealizadaComSucesso{
    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "Não é possível atualizar o status. Compra não foi processada com sucesso.");
        compra.atualizaStatusCompra();
    }
}
