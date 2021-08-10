package br.com.zupacademy.osmarjunior.mercadolivre.model.enums;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Compra;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

public enum GatewayPagamento {
    paypal("paypal.com", "retorno-paypal"),
    pagseguro("pagseguro.com", "retorno-pagseguro");

    @NotBlank
    private String urlPagamento;

    @NotBlank
    private String sufixoRedirectUrl;

    @NotBlank
    private String urlPrefixoRetorno = "http://localhost:8080/transacoes";

    GatewayPagamento(@NotBlank String url, @NotBlank String sufixoRedirectUrl) {
        this.urlPagamento = url;
        this.sufixoRedirectUrl = sufixoRedirectUrl;
    }

    public String gerarLinkPagamento(Compra compra) {

        Map<String, String> mapVariables = new HashMap<>();

        mapVariables.put("urlPagamento", this.urlPagamento);
        mapVariables.put("codigoCompra", compra.getCodigoDaCompra());
        mapVariables.put("urlRetorno", this.urlPrefixoRetorno);
        mapVariables.put("sufixoRetorno", this.sufixoRedirectUrl);
        mapVariables.put("compraId", compra.getId().toString());

        String urlDePagamento = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("{urlPagamento}")
                .queryParam("buyerId={compraId}")
                .queryParam("serialId={codigoCompra}")
                .queryParam("redirectUrl={urlRetorno}/{sufixoRetorno}/{compraId}")
                .buildAndExpand(mapVariables).toString();
        mapVariables.clear();

        return urlDePagamento;
    }
}
