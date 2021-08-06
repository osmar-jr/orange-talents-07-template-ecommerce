package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.ProdutoFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Caracteristica;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoFormRequest produtoFormRequest){

        Produto produto = produtoFormRequest.toProduto(entityManager);
        entityManager.persist(produto);

        return ResponseEntity.ok(produto.toString());
    }
}
