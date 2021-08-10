package br.com.zupacademy.osmarjunior.mercadolivre.model.enums;

public enum StatusCompra {
    ERRO(0L, "ERRO"),
    SUCESSO(1L, "SUCESSO"),
    INICIADA(2L, "INICIADA");

    private final Long id;
    private final String status;

    StatusCompra(Long id, String status) {
        this.id = id;
        this.status = status;
    }
}
