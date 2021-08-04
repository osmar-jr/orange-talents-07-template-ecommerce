package br.com.zupacademy.osmarjunior.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank @Email @Column(unique = true, nullable = false)
    private String login;

    @NotBlank @Length(min = 6)
    @Column(nullable = false)
    private String senha;

    @NotNull @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotNull @Valid SenhaLimpa senhaLimpa) {
        Assert.hasLength(login, "Valor de login não pode ser vazio.");
        Assert.notNull(senhaLimpa, "Atributo de senha limpa não pode ser nulo");

        this.login = login;
        this.senha = senhaLimpa.encode();
        this.dataCriacao = LocalDateTime.now();
    }
}
