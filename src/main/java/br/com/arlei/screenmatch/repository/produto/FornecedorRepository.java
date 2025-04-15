package br.com.arlei.screenmatch.repository.produto;

import br.com.arlei.screenmatch.model.produto.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
