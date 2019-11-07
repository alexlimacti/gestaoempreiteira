package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "log")
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Enumerated(EnumType.STRING)
	private MenuSistema menusistema;

	@Column(length = 180)
	private String domaindame;
	
	private Long idobject;

	@Enumerated(EnumType.STRING)
	private LogAcaoUsuarioSistema acao;

	@Type(type = "text")
	private String valoratributos;

	@Column(length = 100)
	private String usuariologin;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public MenuSistema getMenusistema() {
		return menusistema;
	}

	public void setMenusistema(MenuSistema menusistema) {
		this.menusistema = menusistema;
	}

	public String getDomaindame() {
		return domaindame;
	}

	public void setDomaindame(String domaindame) {
		this.domaindame = domaindame;
	}

	public Long getIdobject() {
		return idobject;
	}

	public void setIdobject(Long idobject) {
		this.idobject = idobject;
	}

	public LogAcaoUsuarioSistema getAcao() {
		return acao;
	}

	public void setAcao(LogAcaoUsuarioSistema acao) {
		this.acao = acao;
	}

	public String getValoratributos() {
		return valoratributos;
	}

	public void setValoratributos(String valoratributos) {
		this.valoratributos = valoratributos;
	}

	public String getUsuariologin() {
		return usuariologin;
	}

	public void setUsuariologin(String usuariologin) {
		this.usuariologin = usuariologin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getValorAtributos(Object obj) {
		String result = "";
		try {
			Class cls = obj.getClass();
			Field[] fieldlist = cls.getDeclaredFields();
			for (int i = 1; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				fld.setAccessible(true);
				if (fld.get(obj) != null) {
					result = result + fld.getName() + " : ";
					result = result + fld.get(obj) + ";\n";
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}

	public Long getIdObject(Object obj) {
		Long id = Long.valueOf(0L);
		try {
			Class cls = obj.getClass();
			Field[] fieldlist = cls.getDeclaredFields();
			Field fld = fieldlist[1];
			fld.setAccessible(true);
			id = (Long) fld.get(obj);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return id;
	}
}
