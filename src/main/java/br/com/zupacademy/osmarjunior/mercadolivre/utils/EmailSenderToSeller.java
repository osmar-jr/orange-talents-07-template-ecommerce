package br.com.zupacademy.osmarjunior.mercadolivre.utils;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Pergunta;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailSenderToSeller implements Sender{

    @Override
    public void sendEmail(Object object) {
        Pergunta pergunta = (Pergunta) object;
        System.out.println("Email gerado com pergunta: " + pergunta.toString());
    }
}
