package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailCompraSucesso implements EventosCompraRealizadaComSucesso{

    @Autowired
    private Emails emails;

    @Override
    public void processa(Compra compra) {
        emails.compraProcessadaComSucesso(compra);
    }
}
