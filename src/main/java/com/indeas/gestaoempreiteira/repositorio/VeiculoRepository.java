package com.indeas.gestaoempreiteira.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.TipoEquipamento;
import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.modelo.VeiculoTipo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	public Veiculo findByChassis(String chassis);
	
	public Veiculo findByCodigo(String codigo);
	
	public Veiculo findByPlaca(String placa);
	
	@Query("from Veiculo v where v.quilometragem between :quilometrageminicial and :quilometragemfinal")
	public List<Veiculo> findByQuilometragem(@Param("quilometrageminicial")Long quilometrageminicial,@Param("quilometragemfinal")Long quilometragemfinal);
	
	public List<Veiculo> findByUltimarevisao(Date ultimarevisao);
	
	@Query("from Veiculo v where v.ultimarevisao between :datainicial and :datafinal")
	public List<Veiculo> findByUltimarevisao(@Param("datainicial")Date datainicial, @Param("datafinal")Date datafinal);
	
	public List<Veiculo> findByDisponivel(boolean disponivel);
	
	public List<Veiculo> findByManutencao(boolean manutencao);
	
	public List<Veiculo> findByAtivo(boolean ativo);
	
	@Query("select count(p) from Veiculo p where p.tipo = :tipo")
	public int countByVeiculotipo(@Param("tipo")VeiculoTipo veiculotipo);
	
	@Query("select count(p) from Veiculo p where p.tipoequipamento = :tipo")
	public int countByTipoequipamento(@Param("tipo")TipoEquipamento tipoequipamento);
	
	public List<Veiculo> findByCodigoStartingWithIgnoreCase(String codigo);

}
