package br.com.swar.snet.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.swar.snet.model.domain.Rebelde;
import java.util.List;

public interface RebeldeRepository extends CrudRepository<Rebelde, Long> {
	
	@Query("select r from Rebelde r left join fetch r.inventario i order by r.id")
	List<Rebelde> findAll(Pageable page);

	@Query("select r from Rebelde r join fetch r.inventario i where r.id = ?1")
	Rebelde findByIdFetchIventario(Long idRebelde);
	
	@Query("select count(r) from Rebelde r where r.advertencias > ?1")
	Long contarTraidores(Integer limiarTraicao);
	
	@Query("select count(r) from Rebelde r")
	Long contarRebeldes();
	
	@Query("select sum(i.valor) from Rebelde r left join r.inventario i where r.advertencias > ?1")
	Long buscarTotalDePontoEmTraidores(Integer limiarTraicao);
	
}
