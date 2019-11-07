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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="veiculo")
public class Veiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(length=20, unique=true)
	private String codigo;
	
	@Size(min=0, max=8, message="A placa deve conter 8 caracteres.")
	@Column(length=20)
	private String placa;
	
	@Size(min=0, max=17, message="O Chassis deve conter 17 caracteres.")
	@Column(length=30)
	private String chassis;
	
	private Long quilometragem;
	
	@NotNull(message="O Modelo do veículo é obrigatório")
	@ManyToOne
	@JoinColumn(name="tipo", referencedColumnName="codigo")
	private VeiculoTipo tipo;
	
	@Column(length=30)
	private String cor;
	
	private int quantidadeeixos;
	private int ano;
	private int anomodelo;
	
	private boolean disponivel;
	private boolean manutencao;
	private boolean ativo;
	
	@OneToOne
	@JoinColumn(name="documento", referencedColumnName="codigoidentificacao")
	private Documentos documento;
	
	@OneToMany(targetEntity = VeiculoQuilometragem.class ,cascade = {CascadeType.REMOVE},  mappedBy = "veiculo")
	private List<VeiculoHistorico> historico;
	
	@NotNull(message="Data de compra é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date datacompra;
	
	@Enumerated(EnumType.STRING)
	private TipoEquipamento tipoequipamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimarevisao;	

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public VeiculoTipo getTipo() {
		return tipo;
	}

	public void setTipo(VeiculoTipo tipo) {
		this.tipo = tipo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getQuantidadeeixos() {
		return quantidadeeixos;
	}

	public void setQuantidadeeixos(int quantidadeeixos) {
		this.quantidadeeixos = quantidadeeixos;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getAnomodelo() {
		return anomodelo;
	}

	public void setAnomodelo(int anomodelo) {
		this.anomodelo = anomodelo;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public boolean isManutencao() {
		return manutencao;
	}

	public void setManutencao(boolean manutencao) {
		this.manutencao = manutencao;
	}

	public List<VeiculoHistorico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<VeiculoHistorico> historico) {
		this.historico = historico;
	}

	public Date getUltimarevisao() {
		return ultimarevisao;
	}

	public void setUltimarevisao(Date ultimarevisao) {
		this.ultimarevisao = ultimarevisao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Documentos getDocumento() {
		return documento;
	}

	public void setDocumento(Documentos documento) {
		this.documento = documento;
	}

	public Date getDatacompra() {
		return datacompra;
	}

	public void setDatacompra(Date datacompra) {
		this.datacompra = datacompra;
	}

	public TipoEquipamento getTipoequipamento() {
		return tipoequipamento;
	}

	public void setTipoequipamento(TipoEquipamento tipoequipamento) {
		this.tipoequipamento = tipoequipamento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isNovo() {
		return id == null;
	}
	
	public String toString() {
		return codigo + " - " + tipo.getDescricao();
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}