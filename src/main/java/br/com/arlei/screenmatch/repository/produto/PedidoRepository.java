package br.com.arlei.screenmatch.repository.produto;

import br.com.arlei.screenmatch.model.produto.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Custom query methods can be defined here if needed

    List<Pedido> findByDataEntregaIsNull();
    List<Pedido> findByDataEntregaIsNotNull();
    List<Pedido> findByDataPedidoAfter(LocalDate data);
    List<Pedido> findByDataPedidoBefore(LocalDate data);
    List<Pedido> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim);


}
