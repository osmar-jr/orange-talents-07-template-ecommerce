package br.com.zupacademy.osmarjunior.mercadolivre.model;

import br.com.zupacademy.osmarjunior.mercadolivre.model.enums.StatusCompra;

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
    private String pagamentoId;

    @NotNull @Valid
    @Enumerated
    private StatusCompra statusCompra;

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

    public Transacao(@NotBlank String pagamentoId,
                     @NotNull @Valid StatusCompra statusCompra,
                     @NotNull @Valid Compra compra,
                     @NotNull @Valid Usuario comprador) {

        this.pagamentoId = pagamentoId;
        this.statusCompra = statusCompra;
        this.compra = compra;
        this.comprador = comprador;
        this.realizadoEm = LocalDateTime.now();
        this.compra.adicionarTransacao(this);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", pagamentoId='" + pagamentoId + '\'' +
                ", statusCompra=" + statusCompra +
                ", compra=" + compra +
                ", realizadoEm=" + realizadoEm.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id) && Objects.equals(pagamentoId, transacao.pagamentoId) && statusCompra == transacao.statusCompra && Objects.equals(compra, transacao.compra) && Objects.equals(comprador, transacao.comprador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pagamentoId, statusCompra, compra, comprador);
    }

    public StatusCompra getStatusCompra() {
        return this.statusCompra;
    }
}
