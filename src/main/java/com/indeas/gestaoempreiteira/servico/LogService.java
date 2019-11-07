package com.indeas.gestaoempreiteira.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.Log;
import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.repositorio.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private InstanciaUsuario iu;

	public List<Log> findByUsuariologin(String usuariologin) {
		return logRepository.findByUsuariologin(usuariologin);
	}

	public List<Log> findAll() {
		return logRepository.findAll();
	}

	public long count() {
		return logRepository.count();
	}

	public void delete(Log entity) {
		logRepository.delete(entity);
	}
	
	public void salvar(Object obj, MenuSistema menuSistema, LogAcaoUsuarioSistema logAcaoUsuarioSistema) {
		Log log = new Log();
		log.setData(new Date());
		log.setMenusistema(menuSistema);
		log.setDomaindame(obj.getClass().getSimpleName());
		log.setIdobject(log.getIdObject(obj));
		log.setAcao(logAcaoUsuarioSistema);
		log.setValoratributos(log.getValorAtributos(obj));
		log.setUsuariologin(iu.getUsuario().getEmail());
		logRepository.save(log);
	}	

}
