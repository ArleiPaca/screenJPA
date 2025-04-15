package br.com.arlei.screenmatch.model.produto;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {
    @Id
    private Long id;

    // na relação muitos para muitos , precisamos de uma tabela intermediária
    // que vai guardar o id do pedido e o id do produto
    // o join table vai guardar o id do pedido e o id do produto
    // o join column vai guardar o id do pedido
    // o inverse join column vai guardar o id do produto
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    private LocalDate data;

    public Pedido() {
    }

    public Pedido(Long id, LocalDate data) {
        this.id = id;
        this.data = data;
    }

    public void setProdutos(List<Produto> produto1) {
        this.produtos = produto1;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }


    public Long getId() {
        return this.id;
    }
}