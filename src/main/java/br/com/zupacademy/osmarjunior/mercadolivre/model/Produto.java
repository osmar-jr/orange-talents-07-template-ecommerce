package br.com.zupacademy.osmarjunior.mercadolivre.model;

import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.CaracteristicaFormRequest;
import br.com.zupacademy.osmarjunior.mercadolivre.utils.Opinioes;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 1000)
    @Column(columnDefinition = "TEXT")
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotNull
    @PositiveOrZero
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

    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagensProduto = new HashSet<>();

    @Valid
    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @Valid
    @OneToMany(mappedBy = "produto")
    private Set<Opiniao> opinioes = new HashSet<>();

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
        this.caracteristicas
                .addAll(caracteristicaFormRequests.stream()
                        .map(caracteristicaFormRequest -> caracteristicaFormRequest.toCaracteristica(this))
                        .collect(Collectors.toSet()));
        ;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Usuario getDono() {
        return dono;
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

    public boolean isOwner(Usuario usuario) {
        return this.dono.equals(usuario);
    }

    public void inserirImagens(Set<String> urls) {
        Assert.notEmpty(urls, "Lista de imagens não pode ser vazia.");

        Set<ImagemProduto> imagens = urls.stream()
                .map(url -> new ImagemProduto(url, this))
                .collect(Collectors.toSet());

        this.imagensProduto.addAll(imagens);
    }

    public <T> Set<T> mapeiaCaracteristicas(Function<Caracteristica, T> functionMap) {

        return this.caracteristicas.stream().map(functionMap).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaLinksImagens(Function<ImagemProduto, T> functionMap) {

        return this.imagensProduto.stream().map(functionMap).collect(Collectors.toSet());
    }

    public <T> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> functionMap) {

        return this.perguntas.stream().map(functionMap).collect(Collectors.toCollection(TreeSet::new));
    }

    public Opinioes getOpinioes() {

        return new Opinioes(this.opinioes);
    }

    public boolean abaterEstoque(@Positive Integer quantidade) {
        Assert.isTrue(quantidade > 0, "Quantidade não pode ser menor ou igual a zero.");

        if(quantidade <= this.quantidade){
            this.quantidade -= quantidade;
            return true;
        }

        return false;
    }
}
