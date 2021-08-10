package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.TransacaoPagSeguroFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.TransacaoPayPalFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.TransacaorRetornoFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.service.Emails;
import br.com.zupacademy.osmarjunior.mercadolivre.service.EventosNovaCompra;
import br.com.zupacademy.osmarjunior.mercadolivre.service.NotaFiscal;
import br.com.zupacademy.osmarjunior.mercadolivre.service.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Emails emails;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> processarTransacaoPagSeguro(@Valid TransacaoPagSeguroFormRequest form,
                                                         @PathVariable("id") Long compraId,
                                                         @AuthenticationPrincipal Usuario comprador){
        return processarTransacao(form, compraId, comprador);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> processarTransacaoPayPal(@Valid TransacaoPayPalFormRequest form,
                                                         @PathVariable("id") Long compraId,
                                                         @AuthenticationPrincipal Usuario comprador){
        return processarTransacao(form, compraId, comprador);
    }

    private ResponseEntity<?> processarTransacao(TransacaorRetornoFormRequest form, Long compraId, Usuario comprador) {
        Compra compra = entityManager.find(Compra.class, compraId);
        compra.adicionarTransacao(form);
        entityManager.merge(compra);

        eventosNovaCompra.processa(compra);

        return ResponseEntity.ok(compra.toString());
    }


}
