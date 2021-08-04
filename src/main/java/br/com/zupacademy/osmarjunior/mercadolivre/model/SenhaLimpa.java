package br.com.zupacademy.osmarjunior.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class SenhaLimpa {

    @NotBlank @Length(min = 6)
    private String senha;

    public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha, "Campo senha não pode ser nulo.");
        Assert.isTrue(senha.length() >= 6, "Campo senha não pode ter tamanho menor que 6.");
        this.senha = senha;
    }

    public String encode() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
