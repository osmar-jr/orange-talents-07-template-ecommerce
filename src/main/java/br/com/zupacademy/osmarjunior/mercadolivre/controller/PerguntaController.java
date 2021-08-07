package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.PerguntaFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Pergunta;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Produto;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.service.Emails;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/produtos/{id}/perguntas")
public class PerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Emails emailSender;

    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionarPergunta(@PathVariable("id") Long id,
                                               @RequestBody @Valid PerguntaFormRequest perguntaFormRequest,
                                               @AuthenticationPrincipal Usuario autor){
        Produto produto = entityManager.find(Produto.class, id);
        if (produto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Produto informado não encontrado.");
        }

        if(produto.isOwner(autor)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Operação não permitida ao vendedor do produto.");
        }

        Pergunta pergunta = perguntaFormRequest.toPergunta(autor, produto);
        entityManager.persist(pergunta);

        emailSender.novaPergunta(pergunta);

        return ResponseEntity.ok().body(pergunta.toString());
    }
}
