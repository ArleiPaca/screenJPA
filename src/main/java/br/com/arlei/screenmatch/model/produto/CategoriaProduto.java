package br.com.arlei.screenmatch.model.produto;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "categoriaProduto", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public CategoriaProduto() {
    }

    public CategoriaProduto(String nome) {

        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produto1) {
        this.produtos = produto1;
    }
}
