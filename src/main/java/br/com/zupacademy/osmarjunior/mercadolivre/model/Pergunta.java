package br.com.zupacademy.osmarjunior.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull @Valid
    @ManyToOne
    private Usuario autor;

    @NotNull @Valid
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo,
                    @NotNull @Valid Usuario autor,
                    @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.autor = autor;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmailAutorPergunta() {
        return this.autor.getUsername();
    }

    public String getEmailVendedorProduto() {
        return this.produto.getDono().getUsername();
    }
}

