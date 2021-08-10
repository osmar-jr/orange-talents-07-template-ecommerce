package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventosCompraRealizadaComSucesso> eventosCompraSucesso;

    @Autowired
    private Set<EventosCompraRealizadaComFalha> eventosCompraFalha;

    public void processa(Compra compra){
        if(compra.processadaComSucesso()){
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        } else {
            eventosCompraFalha.forEach(evento -> evento.processa(compra));
        }
    }
}
