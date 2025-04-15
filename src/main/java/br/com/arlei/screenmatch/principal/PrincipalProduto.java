package br.com.arlei.screenmatch.principal;

import br.com.arlei.screenmatch.model.produto.CategoriaProduto;
import br.com.arlei.screenmatch.model.produto.Fornecedor;
import br.com.arlei.screenmatch.model.produto.Pedido;
import br.com.arlei.screenmatch.model.produto.Produto;
import br.com.arlei.screenmatch.repository.produto.CategoriaProdutoRepository;
import br.com.arlei.screenmatch.repository.produto.FornecedorRepository;
import br.com.arlei.screenmatch.repository.produto.PedidoRepository;
import br.com.arlei.screenmatch.repository.produto.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe responsável por demonstrar o funcionamento do relacionamento entre Produto e CategoriaProduto.
 * Ela cria categorias e produtos, associa os produtos às categorias e salva no banco de dados.
 */
public class PrincipalProduto {


    private CategoriaProdutoRepository categoriaProdutoRepository;
    private FornecedorRepository fornecedorRepository;
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    public PrincipalProduto(CategoriaProdutoRepository categoriaProdutoRepository,
                            FornecedorRepository fornecedorRepository,
                            PedidoRepository pedidoRepository,
                            ProdutoRepository produtoRepository) {
        this.categoriaProdutoRepository
                    = categoriaProdutoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;

    }

    public void principal() {


        // Criando fornecedores
        Fornecedor fornecedorTech
                        = new Fornecedor("Tech Supplier");
        Fornecedor fornecedorLivros
                        = new Fornecedor("Livraria Global");
        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

        // Criando categorias
        CategoriaProduto categoriaEletronicos
                = new CategoriaProduto( "Eletrônicos");
        CategoriaProduto categoriaLivros
                = new CategoriaProduto( "Livros");


        // Criando produtos e associando às categorias
        Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos,fornecedorTech);
        Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos,fornecedorTech);
        Produto produto3 = new Produto("Livro de Java", 100.0, categoriaLivros,fornecedorLivros);
        Produto produto4 = new Produto("Livro de Spring Boot", 150.0, categoriaLivros,fornecedorLivros);



        // Associando produtos às categorias
        categoriaEletronicos.setProdutos(List.of(produto1, produto2));
        categoriaLivros.setProdutos(List.of(produto3, produto4));

        // Salvando categorias (cascateia produtos automaticamente, se configurado)
        categoriaProdutoRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Criando pedidos e associando produtos
        Pedido pedido1 = new Pedido(1L, LocalDate.now());
        pedido1.setProdutos(List.of(produto1, produto3));
        Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1));
        pedido2.setProdutos(List.of(produto2));
        pedidoRepository.saveAll(List.of(pedido1, pedido2));

        // Testando a persistência e o relacionamento
        System.out.println("Categorias e seus produtos:");
        categoriaProdutoRepository.findAll().forEach(categoria -> {
            System.out.println("Categoria: " + categoria.getNome());
            categoria.getProdutos().forEach(produto ->
                    System.out.println(" - Produto: " + produto.getNome())
            );
        });


        System.out.println("\nPedidos e seus produtos:");
        pedidoRepository.findAll().forEach(pedido -> {
            System.out.println("Pedido " + pedido.getId() + ":");
            pedido.getProdutos().forEach(produto ->
                    System.out.println(" - " + produto.getNome())
            );
        });

        System.out.println("\nProdutos e seus fornecedores:");
        produtoRepository.findAll().forEach(produto ->
                System.out.println("Produto: " + produto.getNome() +
                        ", Fornecedor: " + produto.getFornecedor().getNome())
        );
    }
}
