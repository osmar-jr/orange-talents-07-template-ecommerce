package br.com.zupacademy.osmarjunior.mercadolivre.validator;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.ImagensProdutoFormRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class NaoPermiteObjetosDeImagemNulo implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ImagensProdutoFormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        ImagensProdutoFormRequest imagensProdutoFormRequest = (ImagensProdutoFormRequest) target;
        List<MultipartFile> imagens = imagensProdutoFormRequest.getImagens();

        for (MultipartFile imagem :
                imagens) {
            if (imagem.isEmpty()){
                errors.rejectValue("imagens", null, "Conteúdo da imagem está nulo ou inválido.");
            }
        }

    }
}
