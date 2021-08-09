package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.dto.DetalheProdutoDto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/produtos/{id}/detalhes")
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<DetalheProdutoDto> detalharProduto(@PathVariable("id") Long id){
        Produto produto = entityManager.find(Produto.class, id);

        if(produto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto informado não encontrado.");
        }

        return ResponseEntity.ok().body(new DetalheProdutoDto(produto));
    }
}
