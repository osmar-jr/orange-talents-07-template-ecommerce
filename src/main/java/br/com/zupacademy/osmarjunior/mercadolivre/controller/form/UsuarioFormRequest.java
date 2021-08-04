package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.mercadolivre.model.SenhaLimpa;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioFormRequest {

    @NotBlank @Email
    @UniqueValue(classDomain = Usuario.class, attributeName = "login")
    private String login;

    @NotBlank @Length(min = 6)
    private String senha;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsuarioFormRequest(@NotBlank @Email String login,
                              @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Deprecated
    public UsuarioFormRequest() {
    }

    public Usuario toUsuario() {
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }

}
