package br.com.zupacademy.osmarjunior.mercadolivre.validator;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.CategoriaFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Categoria;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class NaoPermiteCadastrarCategoriaComCategoriaMaeInexistente implements Validator {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriaFormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        CategoriaFormRequest categoriaFormRequest = (CategoriaFormRequest) target;
        if(categoriaFormRequest.getCategoriaMaeId() == null){
            return;
        }

        Categoria categoriaMae = entityManager.find(Categoria.class,
                categoriaFormRequest.getCategoriaMaeId());

        if(categoriaMae == null){
            errors.rejectValue("categoriaMaeId",
                    null,
                    "Categoria mãe informada não existe no sistema.");
        }
    }
}
