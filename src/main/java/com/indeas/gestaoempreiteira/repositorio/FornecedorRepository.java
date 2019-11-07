package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	public Fornecedor findByCpfcnpj(String cpfnpj);
	
	public List<Fornecedor> findByRazaosocialStartingWithIgnoreCase(String nome);
	
}
