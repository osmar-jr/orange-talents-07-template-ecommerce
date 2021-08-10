package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class EmailCompraFalha implements EventosCompraRealizadaComFalha{

    @Autowired
    private Emails emails;

    @Override
    public void processa(@NotNull @Valid Compra compra) {
        Assert.isTrue(!compra.processadaComSucesso(), "FATAL ERROR: Email de falha não pode ser enviado, pois compra foi processada com sucesso.");
        emails.falhaAoProcessarCompra(compra);
    }
}
