package br.com.arlei.screenmatch.repository.produto;

import br.com.arlei.screenmatch.model.produto.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Custom query methods can be defined here if needed
}
