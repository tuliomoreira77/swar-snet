package br.com.swar.snet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.swar.snet.model.domain.Localizacao;

@Repository
public interface LocalizacaoRepository extends CrudRepository<Localizacao, Long>{
	
	Localizacao findByIdRebelde(Long idRebelde);

	
}
