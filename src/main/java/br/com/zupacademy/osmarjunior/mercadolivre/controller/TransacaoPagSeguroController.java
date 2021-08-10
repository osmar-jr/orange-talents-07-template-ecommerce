package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.TransacaoPagSeguroFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.TransacaoPayPalFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Transacao;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusCompra;
import br.com.zupacademy.osmarjunior.mercadolivre.service.Emails;
import br.com.zupacademy.osmarjunior.mercadolivre.utils.NotasFiscais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class TransacaoPagSeguroController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Emails emails;

    @Autowired
    private NotasFiscais notasFiscais;

    @PostMapping("/retorno-pagseguro")
    @Transactional
    public ResponseEntity<?> criarTransacao(@RequestBody @Valid TransacaoPagSeguroFormRequest form,
                                            @AuthenticationPrincipal Usuario comprador){
        Compra compra = entityManager.find(Compra.class, form.getCompraId());

        if(!form.getStatusCompra().equals(StatusCompra.SUCESSO)){
            emails.notificarFalhaAoProcessarCompra(compra);
            return ResponseEntity
                    .unprocessableEntity()
                    .body("Não conseguimos processar sua compra, um novo link de pagamento foi enviado.");
        }
        Transacao novaTransacao = form.toTransacao(comprador, compra);

        entityManager.persist(novaTransacao);

        notasFiscais.gerarNotaFiscal(compra, comprador);
        return ResponseEntity.ok(novaTransacao.getStatusCompra());
    }

    @PostMapping("/retorno-paypal")
    @Transactional
    public ResponseEntity<?> criarTransacao(@RequestBody @Valid TransacaoPayPalFormRequest form,
                                            @AuthenticationPrincipal Usuario comprador){
        Compra compra = entityManager.find(Compra.class, form.getCompraId());

        if(!form.getStatusCompra().equals(StatusCompra.SUCESSO)){
            emails.notificarFalhaAoProcessarCompra(compra);
            return ResponseEntity
                    .unprocessableEntity()
                    .body("Não conseguimos processar sua compra, um novo link de pagamento foi enviado.");
        }
        Transacao novaTransacao = form.toTransacao(comprador, compra);

        entityManager.persist(novaTransacao);

        notasFiscais.gerarNotaFiscal(compra, comprador);
        return ResponseEntity.ok(novaTransacao.getStatusCompra());
    }
}
