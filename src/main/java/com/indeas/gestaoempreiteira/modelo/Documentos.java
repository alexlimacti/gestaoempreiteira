package com.indeas.gestaoempreiteira.modelo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name="documentos")
public class Documentos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@NotNull
	@Column(length = 40,unique=true)
	private String codigoidentificacao;
		
	@Column(length=500)
	private String detalhesdodocumento;
	
	@Column(length=40)
	private String numerodocumento;
	
	@Column(length=30)
	private String serie;
	
	@ManyToOne
	@JoinColumn(name="fornecedor", referencedColumnName="cpfcnpj")
	private Fornecedor fornecedor;
	
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipodocumento;
	
	@Enumerated(EnumType.STRING)
	private TipoDocumentoFiscal tipodocumentofiscal;
	
	@ManyToOne
	@JoinColumn(name="centrodecusto", referencedColumnName="centrodecusto")
	private CentroDeCusto centrodecusto;
	
	@ManyToOne
	@JoinColumn(name="usuariocriador", referencedColumnName="id")
	private Usuario usuariocriador;
	
	@Lob
	private byte[] documento;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date datacriacao;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataemissao;
	
	@Temporal(TemporalType.DATE)
	private Date datavencimento;
	
	@DecimalMin(value="0.01", message="Valor não pode ser menor que R$ 0,01")
	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valordocumento;

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

	public String getCodigoidentificacao() {
		return codigoidentificacao;
	}

	public void setCodigoidentificacao(String codigoidentificacao) {
		this.codigoidentificacao = codigoidentificacao;
	}

	public String getDetalhesdodocumento() {
		return detalhesdodocumento;
	}

	public void setDetalhesdodocumento(String detalhesdodocumento) {
		this.detalhesdodocumento = detalhesdodocumento;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TipoDocumentoFiscal getTipodocumentofiscal() {
		return tipodocumentofiscal;
	}

	public void setTipodocumentofiscal(TipoDocumentoFiscal tipodocumentofiscal) {
		this.tipodocumentofiscal = tipodocumentofiscal;
	}

	public CentroDeCusto getCentrodecusto() {
		return centrodecusto;
	}

	public void setCentrodecusto(CentroDeCusto centrodecusto) {
		this.centrodecusto = centrodecusto;
	}

	public Usuario getUsuariocriador() {
		return usuariocriador;
	}

	public void setUsuariocriador(Usuario usuariocriador) {
		this.usuariocriador = usuariocriador;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public Date getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}

	public Date getDataemissao() {
		return dataemissao;
	}

	public void setDataemissao(Date dataemissao) {
		this.dataemissao = dataemissao;
	}

	public Date getDatavencimento() {
		return datavencimento;
	}

	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}

	public BigDecimal getValordocumento() {
		return valordocumento;
	}

	public void setValordocumento(BigDecimal valordocumento) {
		this.valordocumento = valordocumento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoDocumento getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(TipoDocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
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
		Documentos other = (Documentos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
