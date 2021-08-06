package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Opiniao;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoFormRequest {

    @NotBlank
    private String titulo;

    @NotBlank @Length(max = 500)
    private String descricao;

    @NotNull @Min(1) @Max(5)
    private Integer nota;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OpiniaoFormRequest(@NotBlank String titulo,
                              @NotBlank @Length(max = 500) String descricao,
                              @NotNull @Min(1) @Max(5) Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public Opiniao toOpiniao(@NotNull @Valid Usuario usuario,
                             @NotNull @Valid Produto produto) {

        return new Opiniao(this.titulo,
                this.descricao,
                this.nota,
                usuario,
                produto);
    }
}
