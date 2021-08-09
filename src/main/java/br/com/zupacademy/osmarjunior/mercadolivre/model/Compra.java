package br.com.zupacademy.osmarjunior.mercadolivre.model;

import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.GatewayPagamento;
import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusCompra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

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
        if (this.gatewayPagamento.equals(GatewayPagamento.paypal)){

            return "paypal.com?buyerId="
                    + this.codigoDaCompra
                    +"&redirectUrl="
                    + "http://localhost:8080/compras/";
        } else {
            return "pagseguro.com?returnId="
                    + this.codigoDaCompra
                    +"&redirectUrl=http://localhost:8080/compras/";
        }
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", codigoDaCompra='" + codigoDaCompra + '\'' +
                ", quantidade=" + quantidade +
                ", gatewayPagamento=" + gatewayPagamento +
                ", statusCompra=" + statusCompra +
                '}';
    }

    public String getEmailComprador() {
        return this.comprador.getUsername();
    }

    public String getEmailVendendor() {
        return this.produto.getDono().getUsername();
    }
}