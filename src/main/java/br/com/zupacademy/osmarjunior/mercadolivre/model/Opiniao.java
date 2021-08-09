package br.com.zupacademy.osmarjunior.mercadolivre.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank @Length(max = 500)
    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotNull @Min(1) @Max(5)
    private Integer nota;

    @NotNull @Valid
    @ManyToOne
    private Usuario consumidor;

    @NotNull @Valid
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@NotBlank String titulo,
                   @NotBlank @Length(max = 500) String descricao,
                   @NotNull @Min(1) @Max(5) Integer nota,
                   @NotNull @Valid Usuario consumidor,
                   @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.consumidor = consumidor;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                '}';
    }
}
