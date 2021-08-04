package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Categoria;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaFormRequest {

    @NotBlank
    @UniqueValue(classDomain = Categoria.class, attributeName = "nome")
    private String nome;

    private Long categoriaMaeId;

    @Deprecated
    public CategoriaFormRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaFormRequest(@NotBlank String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    /*
    * Getter usado pela classe de validação NaoPermiteCadastrarCategoriaComCategoriaMaeInexistente
    * */
    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }


    public Categoria toCategoria(EntityManager entityManager) {
        if (categoriaMaeId == null){
            return new Categoria(this.nome);
        }

        Categoria categoriaMae = entityManager.find(Categoria.class, this.categoriaMaeId);
        Assert.notNull(categoriaMae, "Categoria Mãe informada não existe.");

        return new Categoria(this.nome, categoriaMae);
    }
}
