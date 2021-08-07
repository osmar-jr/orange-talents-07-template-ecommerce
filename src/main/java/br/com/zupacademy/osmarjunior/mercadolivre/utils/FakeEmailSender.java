package br.com.zupacademy.osmarjunior.mercadolivre.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeEmailSender implements Sender{

    @Override
    public void sendEmail(String body, String subject, String from, String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(from);
        System.out.println(to);
    }
}
