package br.com.zupacademy.osmarjunior.mercadolivre.model;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.CaracteristicaFormRequest;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<Caracteristica> caracteristicas;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull @PositiveOrZero Integer quantidade,
                   @NotNull @PositiveOrZero BigDecimal valor,
                   @NotNull @Valid Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.categoria = categoria;
        this.criadoEm = LocalDateTime.now();
        this.caracteristicas = new HashSet<>();
    }

    public void addCaracteristica(@Valid Caracteristica caracteristica){
        this.caracteristicas.add(caracteristica);
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
