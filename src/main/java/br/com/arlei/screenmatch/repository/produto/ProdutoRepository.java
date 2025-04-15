package br.com.arlei.screenmatch.repository.produto;

import br.com.arlei.screenmatch.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Custom query methods can be defined here if needed
}
