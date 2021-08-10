package br.com.zupacademy.osmarjunior.mercadolivre.model.enums;

public enum StatusPagSeguroTransacao {
    ERRO,
    SUCESSO;

    public StatusTransacao normalize() {
        if(this.equals(StatusPagSeguroTransacao.SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
