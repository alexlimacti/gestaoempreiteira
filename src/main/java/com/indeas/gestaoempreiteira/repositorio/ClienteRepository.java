package com.indeas.gestaoempreiteira.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{
	
	public Cliente findByCodigo(String codigo);
	
	public Cliente findByCpfcnpj(String cpfcnpj);
	
	public Cliente findByHabilitado(boolean habilitado);
	
	public Cliente findByInadimplente(boolean inadimplente);

}
