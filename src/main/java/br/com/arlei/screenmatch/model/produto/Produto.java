package br.com.arlei.screenmatch.model.produto;

import br.com.arlei.screenmatch.model.produto.CategoriaProduto;
import br.com.arlei.screenmatch.model.produto.Fornecedor;
import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_produto_id")
    private CategoriaProduto categoriaProduto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private String nome;
    private Double preco;

    public Produto() {
    }

    public Produto(String nome, Double preco, CategoriaProduto categoria, Fornecedor fornecedor){
        this.nome = nome;
        this.preco = preco;
        this.categoriaProduto = categoria;
        this.fornecedor = fornecedor;
    }

    public String getNome() {
        return this.nome;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }
}
