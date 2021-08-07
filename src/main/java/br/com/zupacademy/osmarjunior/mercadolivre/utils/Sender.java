package br.com.zupacademy.osmarjunior.mercadolivre.utils;

import org.springframework.stereotype.Component;

@Component
public interface Sender {

    public void sendEmail(Object pergunta);
}
