package br.com.zupacademy.osmarjunior.mercadolivre.model;

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

    @NotBlank @Size(min = 6)
    @Column(nullable = false)
    private String senha;

    @NotNull @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @Valid SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.encode();
        this.dataCriacao = LocalDateTime.now();
    }
}
