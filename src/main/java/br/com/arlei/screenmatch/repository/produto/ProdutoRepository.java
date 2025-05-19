package br.com.arlei.screenmatch.repository.produto;

import br.com.arlei.screenmatch.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Custom query methods can be defined here if needed

    List<Produto> findByNome(String nome);
    //List<Produto> findByCategoriaNome(String categoriaNome);
    List<Produto> findByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoLessThan(Double preco);
    List<Produto> findByNomeContaining(String termo);
//    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);
//    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);
//    long countByCategoriaNome(String categoriaNome);
    long countByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoLessThanOrNomeContaining(Double preco, String termo);
    //List<Produto> findTop3OrderByPrecoDesc();
    //List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

//    @Query("SELECT p FROM Produto p WHERE p.preco > :preco")
//    List<Produto> buscarPorPrecoMaior(@Param("preco") Double preco);
//
//    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
//    List<Produto> buscarOrdenadoPorPrecoAsc();
//
//    @Query("SELECT p FROM Produto p ORDER BY p.nome DESC")
//    List<Produto> buscarOrdenadoPorNomeDesc();
//
//    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :letra%")
//    List<Produto> buscarProdutosPorLetraInicial(@Param("letra") String letra);



}
