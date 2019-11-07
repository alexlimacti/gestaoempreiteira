Indeas = Indeas || {};

Indeas.PesquisaRapidaEquipamento = (function() {
	
	function PesquisaRapidaEquipamento() {
		this.pesquisaRapidaEquipamentosModal = $('#pesquisaRapidaEquipamentos');
		this.nomeInput = $('#nomeEquipamentoModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-equipamentos-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaEquipamentos');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-equipamento').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaEquipamento.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaEquipamentosModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		this.mensagemErro.addClass('hidden');
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaEquipamento;
	
}());

$(function() {
	var pesquisaRapidaEquipamento = new Indeas.PesquisaRapidaEquipamento();
	pesquisaRapidaEquipamento.iniciar();
});