package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.CategoriaFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Categoria;
import br.com.zupacademy.osmarjunior.mercadolivre.validator.NaoPermiteCadastrarCategoriaComCategoriaMaeInexistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    NaoPermiteCadastrarCategoriaComCategoriaMaeInexistente naoPermiteCadastrarCategoriaComCategoriaMaeInexistente;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(naoPermiteCadastrarCategoriaComCategoriaMaeInexistente);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid CategoriaFormRequest categoriaFormRequest){
        Categoria categoria = categoriaFormRequest.toCategoria(entityManager);
        entityManager.persist(categoria);

        return ResponseEntity.ok(categoria.toString());
    }
}
