package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.Protocolo;
import com.indeas.gestaoempreiteira.modelo.Status;
import com.indeas.gestaoempreiteira.modelo.Usuario;
import com.indeas.gestaoempreiteira.repositorio.ProtocoloRepository;

@Service
public class ProtocoloService {

	@Autowired
	ProtocoloRepository protocoloRepository;
	
	@Autowired
	LogService logService;
	
	public void salvar(Protocolo protocolo) {
		protocoloRepository.save(protocolo);
		logService.salvar(protocolo,MenuSistema.protocolo,LogAcaoUsuarioSistema.SALVAR);
	}

	public long count() {
		return protocoloRepository.count();
	}

	public void deletar(Protocolo protocolo) {
		protocoloRepository.delete(protocolo);
		logService.salvar(protocolo,MenuSistema.usuario,LogAcaoUsuarioSistema.REMOVER);
	}

	public List<Protocolo> findAll() {
		return protocoloRepository.findAll();
	}

	public Protocolo findByProtocolo(String protocolo) {
		return protocoloRepository.findByProtocolo(protocolo);
	}

	public List<Protocolo> findByDestinatario(int mes, int ano, Usuario autor) {
		return protocoloRepository.findByDestinatario(mes, ano, autor);
	}

	public List<Protocolo> findByAutor(int mes, int ano, Usuario autor) {
		return protocoloRepository.findByAutor(mes, ano, autor);
	}

	public List<Protocolo> findByAutorAndStatus(Usuario autor, Status status) {
		return protocoloRepository.findByAutorAndStatus(autor, status);
	}

	public int countByDestinatarioAndStatus(Usuario autor, Status status) {
		return protocoloRepository.countByDestinatarioAndStatus(autor, status);
	}

	public int countByAutorAndStatus(Usuario autor, Status status) {
		return protocoloRepository.countByAutorAndStatus(autor, status);
	}
	
	
}
