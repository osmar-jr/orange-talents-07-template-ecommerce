package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.ImagensProdutoFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.utils.UploaderImage;
import br.com.zupacademy.osmarjunior.mercadolivre.validator.NaoPermiteObjetosDeImagemNulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos/{id}/imagens")
public class ImagemProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UploaderImage uploaderImage;

    @Autowired
    private NaoPermiteObjetosDeImagemNulo naoPermiteObjetosDeImagemNulo;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(naoPermiteObjetosDeImagemNulo);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionarImagens(@PathVariable("id") Long id,
                                              @Valid ImagensProdutoFormRequest imagensProdutoFormRequest,
                                              @AuthenticationPrincipal Usuario usuario){
        Produto produto = entityManager.find(Produto.class, id);

        if(produto == null){
            return ResponseEntity.notFound().build();
        }

        if(!produto.isOwner(usuario)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Set<String> urls = uploaderImage.send(imagensProdutoFormRequest.getImagens());
        produto.inserirImagens(urls);

        entityManager.merge(produto);

        return ResponseEntity.ok().body(urls);

    }
}
