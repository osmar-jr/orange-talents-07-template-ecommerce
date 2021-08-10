package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.NotaFiscalFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.RankingFormRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criarNotaFiscal(@RequestBody @Valid NotaFiscalFormRequest form) throws InterruptedException {
        System.out.println(form.toString());
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@RequestBody @Valid RankingFormRequest form) throws InterruptedException {
        System.out.println(form.toString());
        Thread.sleep(150);
    }
}
