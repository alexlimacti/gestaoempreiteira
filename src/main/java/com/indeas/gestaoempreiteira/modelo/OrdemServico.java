package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "ordemservico")
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 20, unique = true)
	private String codigo;

	@Version
	private Long version;

	@ManyToOne
	@JoinColumn(name = "solicitante", referencedColumnName = "codigo")
	private Usuario usuariosolicitante;

	@ManyToOne
	@JoinColumn(name = "responsavelos", referencedColumnName = "codigo")
	private Usuario usuarioresponsavelos;

	@NotNull(message="O equipamento é obrigatório")
	@ManyToOne
	@JoinColumn(name = "veiculo", referencedColumnName = "codigo")
	private Veiculo veiculo;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataos;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataatendimento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datafinalizacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datacancelamento;
	
	@NotNull(message="O tipo de manutenção é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoManutencao tipomanutencao;

	private boolean finalizado;
	private boolean cancelado;

	@Column(length = 500)
	private String motivocancelamento;

	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que R$ 0,01")
	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valortotal;

	/*
	 * Alterar posteriormente para itens
	 */

	@ManyToMany
	@JoinTable(name="ordemservicomateriais",joinColumns = @JoinColumn(name ="ordemservico"),inverseJoinColumns = @JoinColumn(name = "material"))
	List<CatalogoMateriais> materiais = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="ordemservicosservicos",joinColumns = @JoinColumn(name ="ordemservico"),inverseJoinColumns = @JoinColumn(name = "servico"))
	List<Servico> servicos = new ArrayList<>();

	@NotBlank(message="A descrição do problema é obrigatório")
	@Column(length = 500)
	private String descricaoproblema;

	@Column(length = 500)
	private String solucaoproblema;

	@Transient
	Map<Long, Integer> mapQuantidadesProdutos = new HashMap<>();

	public boolean isNovo() {
		return id == null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Usuario getUsuariosolicitante() {
		return usuariosolicitante;
	}

	public void setUsuariosolicitante(Usuario usuariosolicitante) {
		this.usuariosolicitante = usuariosolicitante;
	}

	public Usuario getUsuarioresponsavelos() {
		return usuarioresponsavelos;
	}

	public void setUsuarioresponsavelos(Usuario usuarioresponsavelos) {
		this.usuarioresponsavelos = usuarioresponsavelos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataos() {
		return dataos;
	}

	public void setDataos(Date dataos) {
		this.dataos = dataos;
	}

	public Date getDataatendimento() {
		return dataatendimento;
	}

	public void setDataatendimento(Date dataatendimento) {
		this.dataatendimento = dataatendimento;
	}

	public Date getDatafinalizacao() {
		return datafinalizacao;
	}

	public void setDatafinalizacao(Date datafinalizacao) {
		this.datafinalizacao = datafinalizacao;
	}

	public Date getDatacancelamento() {
		return datacancelamento;
	}

	public void setDatacancelamento(Date datacancelamento) {
		this.datacancelamento = datacancelamento;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public String getMotivocancelamento() {
		return motivocancelamento;
	}

	public void setMotivocancelamento(String motivocancelamento) {
		this.motivocancelamento = motivocancelamento;
	}

	public BigDecimal getValortotal() {
		return valortotal;
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public List<CatalogoMateriais> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<CatalogoMateriais> materiais) {
		this.materiais = materiais;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public String getDescricaoproblema() {
		return descricaoproblema;
	}

	public void setDescricaoproblema(String descricaoproblema) {
		this.descricaoproblema = descricaoproblema;
	}

	public String getSolucaoproblema() {
		return solucaoproblema;
	}

	public void setSolucaoproblema(String solucaoproblema) {
		this.solucaoproblema = solucaoproblema;
	}

	public TipoManutencao getTipomanutencao() {
		return tipomanutencao;
	}

	public void setTipomanutencao(TipoManutencao tipomanutencao) {
		this.tipomanutencao = tipomanutencao;
	}

	@Transient
	public Map<Long, Integer> getMapQuantidadesProdutos() {
		return mapQuantidadesProdutos;
	}

	@Transient
	public void setMapQuantidadesProdutos(Map<Long, Integer> mapQuantidadesProdutos) {
		this.mapQuantidadesProdutos = mapQuantidadesProdutos;
	}
	
	@Transient
	public void adicionarMaterial(CatalogoMateriais material){
		this.materiais.add(material);
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
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
