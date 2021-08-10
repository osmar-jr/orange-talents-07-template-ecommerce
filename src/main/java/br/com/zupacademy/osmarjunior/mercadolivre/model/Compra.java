package br.com.zupacademy.osmarjunior.mercadolivre.model;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.TransacaorRetornoFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.GatewayPagamento;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusCompra;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String codigoDaCompra;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario comprador;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Valid Produto produto,
                  @NotNull @Valid Usuario comprador,
                  @NotNull @Positive Integer quantidade,
                  @NotNull GatewayPagamento gatewayPagamento) {
        this.produto = produto;
        this.comprador = comprador;
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
        this.statusCompra = StatusCompra.INICIADA;
        this.codigoDaCompra = UUID.randomUUID().toString();
    }

    public String gerarLinkDePagamento() {

        return this.gatewayPagamento.gerarLinkPagamento(this);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", codigoDaCompra='" + codigoDaCompra + '\'' +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", gatewayPagamento=" + gatewayPagamento +
                ", statusCompra=" + statusCompra +
                ", transacoes=" + transacoes +
                '}';
    }

    public String getCodigoDaCompra() {
        return this.codigoDaCompra;
    }

    public Long getId() {
        return this.id;
    }

    public void adicionarTransacao(@Valid TransacaorRetornoFormRequest form) {
        Transacao transacao = form.toTransacao(this, this.comprador);

        Assert.isTrue(!this.transacoes.contains(transacao),
                "Já existe uma transção igual a essa.");

        Assert.isTrue(getTransacoesConcluidasComSucesso().isEmpty(), "Já existe uma transação realizada com sucesso para esta compra.");
        this.transacoes.add(transacao);
    }

    private Set<Transacao> getTransacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes
                .stream()
                .filter(Transacao::isConcluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
                "FATAL ERROR: Existem mais de uma transação finalizadas com sucesso para esta compra.");

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !getTransacoesConcluidasComSucesso().isEmpty();
    }

    public Usuario getComprador() {
        return this.comprador;
    }

    public Usuario getVendedor() {
        return this.produto.getDono();
    }

    public void atualizaStatusCompra() {
        Assert.isTrue(processadaComSucesso(), "Não foi possível atualizar o status, compra não foi processada com sucesso.");
        Assert.isTrue(!this.statusCompra.equals(StatusCompra.FINALIZADA), "Erro, a compra já foi finalizada.");
        this.statusCompra = StatusCompra.FINALIZADA;
    }
}