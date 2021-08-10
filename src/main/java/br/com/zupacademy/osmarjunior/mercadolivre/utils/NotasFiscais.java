package br.com.zupacademy.osmarjunior.mercadolivre.utils;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.service.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotasFiscais {

    @Autowired
    private Emails emails;

    public void gerarNotaFiscal(Compra compra, Usuario comprador) {

        UUID uuid = new UUID(UUID.randomUUID().getLeastSignificantBits(),
                UUID.randomUUID().getMostSignificantBits());

        String notaFiscal = uuid.toString();
        emails.enviarNotaFiscalCompra(compra, comprador, notaFiscal);
    }
}
