package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.mercadolivre.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Categoria;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoFormRequest {

    @NotBlank
    @UniqueValue(classDomain = Produto.class, attributeName = "nome")
    private String nome;

    @NotBlank @Size(max = 1000)
    private String descricao;

    @NotNull @PositiveOrZero
    private Integer quantidade;

    @NotNull @PositiveOrZero
    private BigDecimal valor;

    @NotNull @Positive
    @ExistsId(classDomain = Categoria.class, attributeName = "id")
    private Long categoriaId;

    @NotNull
    @Size(min=3)
    @Valid
    private Set<CaracteristicaFormRequest> caracteristicas = new HashSet<>();

    @Deprecated
    public ProdutoFormRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProdutoFormRequest(@NotBlank String nome,
                              @NotBlank @Size(max = 1000) String descricao,
                              @NotNull @PositiveOrZero Integer quantidade,
                              @NotNull @PositiveOrZero BigDecimal valor,
                              @NotNull @Positive Long categoriaId,
                              @NotNull @Size(min = 2) @Valid Set<CaracteristicaFormRequest> caracteristicas) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoriaId = categoriaId;
        this.caracteristicas = caracteristicas;
    }

    public Produto toProduto(EntityManager entityManager, @NotNull @Valid Usuario usuario) {
        Categoria categoria = entityManager.find(Categoria.class, this.categoriaId);
        Assert.notNull(categoria, "Categoria informada não existe.");

        return new Produto(this.nome, this.descricao,
                this.quantidade, this.valor,
                usuario, categoria, this.caracteristicas);
    }

}
