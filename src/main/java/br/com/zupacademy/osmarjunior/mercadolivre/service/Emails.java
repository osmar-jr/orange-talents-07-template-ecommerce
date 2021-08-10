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
                compra.getVendedor().getUsername());
    }

    public void falhaAoProcessarCompra(Compra compra) {
        sender.sendEmail("<html>..." + compra.gerarLinkDePagamento() + "...</html>",
                "Falha ao Processar a Compra",
                "compras@mercardolivre.com.br",
                compra.getComprador().getUsername());
    }

    public void compraProcessadaComSucesso(Compra compra) {
        sender.sendEmail("<html>..." + compra.toString() + "...</html>",
                "Compra Realizada com Sucesso.",
                "compras@mercardolivre.com.br",
                compra.getComprador().getUsername());
    }
}
