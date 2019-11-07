package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="protocolo")
public class Protocolo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@ManyToOne
	@JoinColumn(name="licenca", referencedColumnName="cpfcnpj")
	private Licenca licenca;
	
	@NotNull
	@Column(length = 40,unique=true)
	private String protocolo;
	
	@ManyToOne
	@JoinColumn(name="autor", referencedColumnName="id")
	private Usuario autor;
	
	@ManyToOne
	@JoinColumn(name="destinatario", referencedColumnName="id")
	private Usuario destinatario;
	
	@OneToOne
	@JoinColumn(name="gestorautorizacao", referencedColumnName="id")
	private Usuario gestorautorizacao;
	
	@OneToOne
	@JoinColumn(name="diretorautorizacao", referencedColumnName="id")
	private Usuario diretorautorizacao;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="centrodecusto", referencedColumnName="centrodecusto")
	private CentroDeCusto centrodecusto;
	
	@ManyToOne
	@JoinColumn(name="centrodecustodestinatario", referencedColumnName="centrodecusto")
	private CentroDeCusto centrodecustodestinatario;
		
	@Column(length=30)
	private String assunto;
	
	@Column(length=500)
	private String resenha;
	
	@Column(length=500)
	private String motivorecusa;
		
	/*
	 * Protocolo Externo
	 */
	@Column(length=60)
	private String destinatarioexterno;
	
	@Column(length=60)
	private String email;
	
	/*
	 * Protocolo Impresso
	 */	
	@Column(length=120)
	private String endereco;
	
	
	@Temporal(TemporalType.DATE)
	private Date datatramite;
	
	@Temporal(TemporalType.DATE)
	private Date datafechamento;
	
	@Temporal(TemporalType.TIME)
	private Date horatramite;
	
	@Temporal(TemporalType.TIME)
	private Date horafechamento;
	
	//Caso necessite de autorização do gestor
	private boolean necessitaautorizacaogestor;
	
	//Caso necessite de autorização da diretoria
	private boolean necessitaautorizacaodiretoria;
	
	private boolean impresso;
	
	private boolean edi;
	
	@OneToMany(targetEntity = ProtocoloItens.class ,cascade = {CascadeType.REMOVE},  mappedBy = "protocolo")
	private List<ProtocoloItens>itensdoprotocolo;

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
		Protocolo other = (Protocolo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

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

	public Licenca getLicenca() {
		return licenca;
	}

	public void setLicenca(Licenca licenca) {
		this.licenca = licenca;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public Usuario getGestorautorizacao() {
		return gestorautorizacao;
	}

	public void setGestorautorizacao(Usuario gestorautorizacao) {
		this.gestorautorizacao = gestorautorizacao;
	}

	public Usuario getDiretorautorizacao() {
		return diretorautorizacao;
	}

	public void setDiretorautorizacao(Usuario diretorautorizacao) {
		this.diretorautorizacao = diretorautorizacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CentroDeCusto getCentrodecusto() {
		return centrodecusto;
	}

	public void setCentrodecusto(CentroDeCusto centrodecusto) {
		this.centrodecusto = centrodecusto;
	}

	public CentroDeCusto getCentrodecustodestinatario() {
		return centrodecustodestinatario;
	}

	public void setCentrodecustodestinatario(CentroDeCusto centrodecustodestinatario) {
		this.centrodecustodestinatario = centrodecustodestinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getResenha() {
		return resenha;
	}

	public void setResenha(String resenha) {
		this.resenha = resenha;
	}

	public String getMotivorecusa() {
		return motivorecusa;
	}

	public void setMotivorecusa(String motivorecusa) {
		this.motivorecusa = motivorecusa;
	}

	public Date getDatatramite() {
		return datatramite;
	}

	public void setDatatramite(Date datatramite) {
		this.datatramite = datatramite;
	}

	public Date getDatafechamento() {
		return datafechamento;
	}

	public void setDatafechamento(Date datafechamento) {
		this.datafechamento = datafechamento;
	}

	public Date getHoratramite() {
		return horatramite;
	}

	public void setHoratramite(Date horatramite) {
		this.horatramite = horatramite;
	}

	public Date getHorafechamento() {
		return horafechamento;
	}

	public void setHorafechamento(Date horafechamento) {
		this.horafechamento = horafechamento;
	}

	public boolean isNecessitaautorizacaogestor() {
		return necessitaautorizacaogestor;
	}

	public void setNecessitaautorizacaogestor(boolean necessitaautorizacaogestor) {
		this.necessitaautorizacaogestor = necessitaautorizacaogestor;
	}

	public boolean isNecessitaautorizacaodiretoria() {
		return necessitaautorizacaodiretoria;
	}

	public void setNecessitaautorizacaodiretoria(boolean necessitaautorizacaodiretoria) {
		this.necessitaautorizacaodiretoria = necessitaautorizacaodiretoria;
	}

	public boolean isImpresso() {
		return impresso;
	}

	public void setImpresso(boolean impresso) {
		this.impresso = impresso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDestinatarioexterno() {
		return destinatarioexterno;
	}

	public void setDestinatarioexterno(String destinatarioexterno) {
		this.destinatarioexterno = destinatarioexterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean isEdi() {
		return edi;
	}

	public void setEdi(boolean edi) {
		this.edi = edi;
	}

	public List<ProtocoloItens> getItensdoprotocolo() {
		return itensdoprotocolo;
	}

	public void setItensdoprotocolo(List<ProtocoloItens> itensdoprotocolo) {
		this.itensdoprotocolo = itensdoprotocolo;
	}

}
