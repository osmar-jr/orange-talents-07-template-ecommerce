package br.com.zupacademy.osmarjunior.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @NotBlank @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(@NotBlank String nome,
                          @NotBlank @Size(max = 1000) String descricao,
                          @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;

    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
