package br.com.zupacademy.osmarjunior.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {
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

    public Long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
