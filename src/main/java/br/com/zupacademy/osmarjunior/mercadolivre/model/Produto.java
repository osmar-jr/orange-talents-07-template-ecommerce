package br.com.zupacademy.osmarjunior.mercadolivre.model;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.CaracteristicaFormRequest;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank @Size(max = 1000)
    @Column(columnDefinition = "TEXT")
    @Size(max = 1000)
    private String descricao;

    @NotNull @PositiveOrZero
    private Integer quantidade;

    @NotNull @PositiveOrZero
    private BigDecimal valor;

    @NotNull
    private LocalDateTime criadoEm;

    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario dono;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull @PositiveOrZero Integer quantidade,
                   @NotNull @PositiveOrZero BigDecimal valor,
                   @NotNull @Valid Usuario usuario,
                   @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Set<CaracteristicaFormRequest> caracteristicaFormRequests) {

        Assert.isTrue(caracteristicaFormRequests.size() >= 3,
                "Produto deve ter no mínimo três características.");

        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = usuario;
        this.criadoEm = LocalDateTime.now();
        this.caracteristicas.addAll(caracteristicaFormRequests.stream()
                .map(caracteristicaFormRequest -> caracteristicaFormRequest.toCaracteristica(this))
                .collect(Collectors.toSet()));;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", criadoEm=" + criadoEm.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                ", categoria=" + categoria +
                ", caracteristicas=" + caracteristicas +
                '}';
    }
}
