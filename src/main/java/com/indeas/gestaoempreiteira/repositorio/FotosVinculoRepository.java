package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.FotosVinculo;
import com.indeas.gestaoempreiteira.modelo.Veiculo;

public interface FotosVinculoRepository extends JpaRepository<FotosVinculo, Long> {

	public List<FotosVinculo> findByVeiculo(Veiculo veiculo);

}
