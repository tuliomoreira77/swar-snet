package br.com.swar.snet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.swar.snet.model.domain.Item;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{

	List<Item> findAllByIdRebelde(Long idRebelde);
	
	@Query("select count(i) from Item i where i.nome = ?1")
	Long countItemPorTipo(String tipo);
}
