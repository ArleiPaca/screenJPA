package br.com.arlei.screenmatch.repository;

import br.com.arlei.screenmatch.model.Categoria;
import br.com.arlei.screenmatch.model.Episodio;
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

    // Usando JPQL mas tem natives querys
    @Query("SELECT s FROM Serie s WHERE s.avaliacao > :nota AND s.totalTemporadas = :temporadas")
    List<Serie> BuscarPorAvaliacaoETotalTemporadas(double nota, int temporadas);

    // Exemplo com Native Query
    @Query(value = "SELECT * FROM series s WHERE s.avaliacao > :nota AND s.total_temporadas = :temporadas"
            , nativeQuery = true)
    List<Serie> BuscarPorAvaliacaoETotalTemporadasNative(double nota, int temporadas);

    //Código JPQL

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    // criar consulta acima native query
    @Query(value = "SELECT e.* FROM episodios e JOIN series s ON e.serie_id = s.id WHERE e.titulo ILIKE %:trechoEpisodio%"
            , nativeQuery = true)
    List<Episodio> episodiosPorTrechoNative(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int anoLancamento);

    // Usar anotação @Param

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND e.avaliacao >= :avaliacao")
    List<Episodio> episodiosPorSerieEAvaliacao(Serie serie, double avaliacao);

}
