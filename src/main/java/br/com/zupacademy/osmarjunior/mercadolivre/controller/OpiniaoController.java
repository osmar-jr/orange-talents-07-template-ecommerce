package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.OpiniaoFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Opiniao;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/{id}/opinioes")
public class OpiniaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionarOpiniao(@PathVariable("id") Long id,
                                              @RequestBody @Valid OpiniaoFormRequest opiniaoFormRequest,
                                              @AuthenticationPrincipal Usuario consumidor){
        Produto produto = entityManager.find(Produto.class, id);
        if(produto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto informado não encontrado.");
        }

        Opiniao opiniao = opiniaoFormRequest.toOpiniao(consumidor, produto);
        entityManager.persist(opiniao);
        return ResponseEntity.ok().body(opiniao.toString());
    }
}
