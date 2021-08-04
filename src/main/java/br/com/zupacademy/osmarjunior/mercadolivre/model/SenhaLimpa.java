package br.com.zupacademy.osmarjunior.mercadolivre.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {

    @NotBlank @Size(min = 6)
    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        this.senha = senha;
    }

    public String encode() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
