package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventosCompraRealizadaComSucesso{

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),
                "FATAL ERROR: Compra não foi concluída com sucesso.");
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> request = Map.of(
                "compraId", compra.getId(),
                "vendedorId", compra.getVendedor().getId()
        );


        restTemplate.postForEntity("http://localhost:8080/ranking",
                request,
                ResponseEntity.class);
    }
}
