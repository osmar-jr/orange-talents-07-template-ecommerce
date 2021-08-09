package br.com.zupacademy.osmarjunior.mercadolivre.utils;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Opiniao;

import javax.validation.Valid;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

    private Set<Opiniao> opinioes;

    public Opinioes(@Valid Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> functionMap) {

        return this.opinioes.stream().map(functionMap).collect(Collectors.toSet());
    }

    public Double media() {

        Set<Integer> notas = mapeiaOpinioes(Opiniao::getNota);
        OptionalDouble optionalMedia = notas.stream().mapToInt(nota -> nota).average();

        return optionalMedia.orElse(0.0);
    }

    public Integer getTotalDeNotas() {
        return this.opinioes.size();
    }
}
