package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Caracteristica;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CaracteristicaFormRequest {

    @NotBlank
    @UniqueValue(classDomain = Caracteristica.class, attributeName = "nome")
    private String nome;

    @NotBlank @Size(max = 1000)
    private String descricao;

    @Deprecated
    public CaracteristicaFormRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CaracteristicaFormRequest(@NotBlank String nome,
                                     @NotBlank @Size(max = 1000) String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toCaracteristica(Produto produto){
        return new Caracteristica(this.nome, this.descricao, produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaFormRequest that = (CaracteristicaFormRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
