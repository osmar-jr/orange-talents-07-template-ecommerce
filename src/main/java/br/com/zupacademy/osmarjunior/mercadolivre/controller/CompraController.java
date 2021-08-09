package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.CompraFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.service.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Emails emails;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarCompra(@RequestBody @Valid CompraFormRequest compraFormRequest,
                                         @AuthenticationPrincipal Usuario comprador){
        Produto produto = entityManager.find(Produto.class, compraFormRequest.getProdutoId());

        if(produto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Produto informado não existe no sistema");
        }
        Integer quantidade = compraFormRequest.getQuantidade();
        if(!produto.abaterEstoque(quantidade)){
            return ResponseEntity
                    .unprocessableEntity()
                    .body("Quantidade solicitada não está disponível para este produto.");
        }

        Compra compra = new Compra(produto, comprador, quantidade, compraFormRequest.getFormaPagamento());
        entityManager.persist(compra);

        emails.novaCompra(compra);

        String linkDePagamento = compra.gerarLinkDePagamento();

        return ResponseEntity.ok(linkDePagamento);
    }
}
