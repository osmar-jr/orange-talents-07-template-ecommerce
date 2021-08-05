package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank
    @Email
    @ExistsId(classDomain = Usuario.class, attributeName = "login")
    private String login;

    @NotBlank @Length(min = 6)
    private String senha;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LoginForm(@NotBlank @Email String login,
                              @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Deprecated
    public LoginForm() {
    }

    public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {

        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }
}
