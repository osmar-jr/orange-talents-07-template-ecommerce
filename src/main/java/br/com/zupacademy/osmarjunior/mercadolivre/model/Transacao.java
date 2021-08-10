package br.com.zupacademy.osmarjunior.mercadolivre.model;

import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusTransacao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String transacaoIdGateway;

    @NotNull @Valid
    @Enumerated
    private StatusTransacao statusTransacao;

    @NotNull @Valid
    @ManyToOne
    private Compra compra;

    @NotNull @Valid
    @ManyToOne
    private Usuario comprador;

    @NotNull
    private LocalDateTime realizadoEm;

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotBlank String transacaoIdGateway,
                     @NotNull @Valid StatusTransacao statusTransacao,
                     @NotNull @Valid Compra compra,
                     @NotNull @Valid Usuario comprador) {

        this.transacaoIdGateway = transacaoIdGateway;
        this.statusTransacao = statusTransacao;
        this.compra = compra;
        this.comprador = comprador;
        this.realizadoEm = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", pagamentoId='" + transacaoIdGateway + '\'' +
                ", statusCompra=" + statusTransacao +
                ", realizadoEm=" + realizadoEm.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id) && Objects.equals(transacaoIdGateway, transacao.transacaoIdGateway) && statusTransacao == transacao.statusTransacao && Objects.equals(compra, transacao.compra) && Objects.equals(comprador, transacao.comprador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transacaoIdGateway, statusTransacao, compra, comprador);
    }

    public boolean isConcluidaComSucesso(){
        return this.statusTransacao.equals(StatusTransacao.sucesso);
    }
}
