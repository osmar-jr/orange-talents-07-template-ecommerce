package br.com.zupacademy.osmarjunior.mercadolivre.model.enums;

import javax.validation.constraints.NotBlank;

public enum GatewayPagamento {
    paypal("http://paypal.com?buyerId=", "/retorno-paypal"),
    pagseguro("http://pagseguro.com?returnId=", "/retorno-pagseguro");

    @NotBlank
    private String urlPagamento;

    @NotBlank
    private String sufixoRedirectUrl;

    @NotBlank
    private String urlPrefixoRetorno = "http://localhost:8080/compras";

    GatewayPagamento(@NotBlank String url, @NotBlank String sufixoRedirectUrl) {
        this.urlPagamento = url;
        this.sufixoRedirectUrl = sufixoRedirectUrl;
    }

    public String gerarLinkPagamento(String codigoDaCompra) {

        String urlDePagamento = this.urlPagamento
                .concat(codigoDaCompra)
                .concat("&redirectUrl=")
                .concat(this.urlPrefixoRetorno)
                .concat(this.sufixoRedirectUrl);
        return urlDePagamento;
    }
}
