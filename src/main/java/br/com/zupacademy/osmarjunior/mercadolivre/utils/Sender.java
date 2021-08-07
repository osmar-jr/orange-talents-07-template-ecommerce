package br.com.zupacademy.osmarjunior.mercadolivre.utils;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Component
public interface Sender {

    /*
    * @param @NotBlank String body content of the email
    * @param @NotBlank String subject of the email
    * @param @NotBlank String name of the user sender
    * @param @NotBlank @Email User Sender's email
    * @param @NotBlank @Email Email Address destiny
    * */
    public void sendEmail(@NotBlank String body,
                          @NotBlank String subject,
                          @NotBlank @Email String from,
                          @NotBlank @Email String to);
}
