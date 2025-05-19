package br.com.arlei.screenmatch.repository.produto;

import br.com.arlei.screenmatch.model.produto.Pedido;
import br.com.arlei.screenmatch.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Custom query methods can be defined here if needed

//    List<Pedido> findByDataEntregaIsNull();
//    List<Pedido> findByDataEntregaIsNotNull();
//    List<Pedido> findByDataPedidoAfter(LocalDate data);
//    List<Pedido> findByDataPedidoBefore(LocalDate data);
//    List<Pedido> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim);

//    @Query("SELECT p FROM Pedido p WHERE p.dataPedido BETWEEN :inicio AND :fim")
//    List<Pedido> buscarPedidosPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
//
//    @Query("SELECT AVG(p.preco) FROM Produto p")
//    Double calcularMediaPrecoProdutos();
//
//    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :categoria")
//    Double buscarPrecoMaximoPorCategoria(@Param("categoria") String categoria);
//
//    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome")
//    List<Object[]> contarProdutosPorCategoria();
//
//    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > :quantidade")
//    List<Object[]> categoriasComMaisDe(@Param("quantidade") long quantidade);
//
//    @Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) AND (:categoria IS NULL OR p.categoria.nome = :categoria)")
//    List<Produto> buscarProdutosFiltrados(@Param("nome") String nome, @Param("categoria") String categoria);
//
//    @Query(value = "SELECT * FROM produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
//    List<Produto> buscarTop5ProdutosMaisCaros();


}
