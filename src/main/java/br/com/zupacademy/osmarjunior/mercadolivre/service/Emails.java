package br.com.zupacademy.osmarjunior.mercadolivre.service;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Pergunta;
import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import br.com.zupacademy.osmarjunior.mercadolivre.utils.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Emails {

    @Autowired
    private Sender sender;

    public void novaPergunta(@NotNull @Valid Pergunta pergunta){
        sender.sendEmail("<html>...</htm>",
                "Nova Pergunta Sobre...",
                pergunta.getEmailAutorPergunta(),
                pergunta.getEmailVendedorProduto());
    }

    public void novaCompra(@NotNull @Valid Compra compra) {
        sender.sendEmail("<html>...</htm>",
                "Novo Pedido de Compra",
                "compras@mercadolivre.com.br",
                compra.getEmailVendendor());
    }

    public void notificarFalhaAoProcessarCompra(Compra compra) {
        sender.sendEmail("<html>..." + compra.gerarLinkDePagamento() + "...</html>",
                "Falha ao Processar a Compra",
                "compras@mercardolivre.com.br",
                compra.getEmailComprador());
    }

    public void enviarNotaFiscalCompra(Compra compra, Usuario comprador, String notaFiscal) {
        sender.sendEmail("<html>..."+ notaFiscal + " Codigo: " + compra.getCodigoDaCompra() +"...</html>",
                "Nota Fiscal Gerada",
                "compras@mercadolivre.com.br",
                comprador.getUsername());
    }
}
