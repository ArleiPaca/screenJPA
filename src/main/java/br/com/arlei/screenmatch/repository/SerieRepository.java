package br.com.arlei.screenmatch.repository;

import br.com.arlei.screenmatch.model.Categoria;
import br.com.arlei.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);
    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThan(String nomeAtor, double nota);
    List<Serie> findTop5ByOrderByAvaliacaoDesc();
    List<Serie> findByGenero(Categoria categoria);

    // Usando Named Query
    List<Serie> findByAvaliacaoGreaterThanAndTotalTemporadasLessThanEqual(double nota, int temporadas);
    // Usando JPGL mas tem natives querys
    @Query("SELECT s FROM Serie s WHERE s.avaliacao > :nota AND s.totalTemporadas = :temporadas")
    List<Serie> BuscarPorAvaliacaoETotalTemporadas(double nota, int temporadas);
    // Exemplo com Native Query
    @Query(value = "SELECT * FROM series s WHERE s.avaliacao > :nota AND s.total_temporadas = :temporadas"
            , nativeQuery = true)
    List<Serie> BuscarPorAvaliacaoETotalTemporadasNative(double nota, int temporadas);



}
