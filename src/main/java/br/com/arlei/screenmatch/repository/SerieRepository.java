package br.com.arlei.screenmatch.repository;

import br.com.arlei.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
}
