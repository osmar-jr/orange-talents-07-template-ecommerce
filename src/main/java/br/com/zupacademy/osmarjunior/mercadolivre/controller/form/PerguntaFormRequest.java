package br.com.zupacademy.osmarjunior.mercadolivre.controller.form;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Pergunta;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaFormRequest {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaFormRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toPergunta(@NotNull @Valid Usuario usuario,
                               @NotNull @Valid Produto produto) {
        return new Pergunta(this.titulo, usuario, produto);
    }
}
