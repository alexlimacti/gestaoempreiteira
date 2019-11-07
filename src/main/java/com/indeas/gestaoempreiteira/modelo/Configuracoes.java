package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="configuracoes")
public class Configuracoes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(length=100)
	private String email;
	
	@Column(length=60)
	private String emailhostname;
	
	@Column(length=80)
	private String emailsenha;
	
	@Column(length=100)
	private String emailnome;	

	private int smtpport;  
    
	private boolean emailsegurancassl;	
	private boolean emaildebug;
	
	private int notificacaodiasantecedentes;
	private int notificacaodiasemaberto;
	private int notificacaodiasematraso;
	
	private Long quilometragemrevisao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailhostname() {
		return emailhostname;
	}

	public void setEmailhostname(String emailhostname) {
		this.emailhostname = emailhostname;
	}

	public String getEmailsenha() {
		return emailsenha;
	}

	public void setEmailsenha(String emailsenha) {
		this.emailsenha = emailsenha;
	}

	public String getEmailnome() {
		return emailnome;
	}

	public void setEmailnome(String emailnome) {
		this.emailnome = emailnome;
	}

	public int getSmtpport() {
		return smtpport;
	}

	public void setSmtpport(int smtpport) {
		this.smtpport = smtpport;
	}

	public boolean isEmailsegurancassl() {
		return emailsegurancassl;
	}

	public void setEmailsegurancassl(boolean emailsegurancassl) {
		this.emailsegurancassl = emailsegurancassl;
	}

	public boolean isEmaildebug() {
		return emaildebug;
	}

	public void setEmaildebug(boolean emaildebug) {
		this.emaildebug = emaildebug;
	}

	public int getNotificacaodiasantecedentes() {
		return notificacaodiasantecedentes;
	}

	public void setNotificacaodiasantecedentes(int notificacaodiasantecedentes) {
		this.notificacaodiasantecedentes = notificacaodiasantecedentes;
	}

	public int getNotificacaodiasemaberto() {
		return notificacaodiasemaberto;
	}

	public void setNotificacaodiasemaberto(int notificacaodiasemaberto) {
		this.notificacaodiasemaberto = notificacaodiasemaberto;
	}

	public int getNotificacaodiasematraso() {
		return notificacaodiasematraso;
	}

	public void setNotificacaodiasematraso(int notificacaodiasematraso) {
		this.notificacaodiasematraso = notificacaodiasematraso;
	}

	public Long getQuilometragemrevisao() {
		return quilometragemrevisao;
	}

	public void setQuilometragemrevisao(Long quilometragemrevisao) {
		this.quilometragemrevisao = quilometragemrevisao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuracoes other = (Configuracoes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
