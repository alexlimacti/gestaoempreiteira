package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Marca;
import com.indeas.gestaoempreiteira.modelo.TipoEquipamento;
import com.indeas.gestaoempreiteira.modelo.VeiculoTipo;

public interface VeiculoTipoRepository extends JpaRepository<VeiculoTipo, Long> {
	
	public VeiculoTipo findByCodigo(String codigo);
	
	public List<VeiculoTipo> findByMarca(Marca marca);
	
	public List<VeiculoTipo> findByDescricaoContaining(String descricao);
	
	public List<VeiculoTipo> findByTipoequipamento(TipoEquipamento tipoequipamento);

}
