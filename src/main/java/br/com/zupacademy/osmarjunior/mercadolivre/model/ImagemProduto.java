package br.com.zupacademy.osmarjunior.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String url;

    @NotNull @Valid
    @ManyToOne
    private Produto produto;


    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotBlank String url, @Valid Produto produto) {

        this.url = url;
        this.produto = produto;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, produto);
    }
}
