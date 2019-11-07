var Indeas = Indeas || {};
Indeas.AutoCompleteServico = (function() {

	function AutoCompleteServico() {
		this.input = $('.js-autocomplete-servico');
		this.tabela = $('.js-tabela-servico');
	};

	AutoCompleteServico.prototype.enable = function() {

		this.input.on('change', onlimpar.bind(this));

		var options = {
			url : function(descricao) {
				return "/servicos/localiza?descricao=" + descricao;
			},
			getValue : "descricao",
			// minCharNumber: 3,
			ajaxSettings : {
				contentType : 'application/json'
			},
			list : {
				onChooseEvent : onItemSelecionado.bind(this)
			}
		};
		this.input.easyAutocomplete(options);

	};

	function onItemSelecionado() {
		var resposta = $.ajax({
			data : {
				id : this.input.getSelectedItemData().id
			},
			async : false,
			'type' : 'GET',
			'url' : '/os/adicionarservico',
		});
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}

	function onlimpar() {
		this.input.val('');
	}

	function onItemAtualizadoNoServidor(html) {
		this.tabela.html(html);
	}

	return AutoCompleteServico;

}());

Indeas.AutoCompleteEquipamento = (function() {

	function AutoCompleteEquipamento() {
		this.input = $('.js-autocomplete-equipamento');
		var htmlTemplateAutoComplete = $('#template-autocomplete-equipamento').html();
		this.template = Handlebars.compile(htmlTemplateAutoComplete);
	};

	AutoCompleteEquipamento.prototype.enable = function() {

		var options = {
			url : function(codigo) {
				return "/veiculo/localiza?codigo=" + codigo;
			},
			getValue : "codigo",
			// minCharNumber: 3,
			ajaxSettings : {
				contentType : 'application/json'
			},
			template: {
				type: 'custom',
				method: function(codigo,veiculo){
					return this.template(veiculo);
				}.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};
		this.input.easyAutocomplete(options);
	};
	
	function onItemSelecionado() {
		var inputEquipamentoId = $('#codigoEquipamento');
		inputEquipamentoId.val(this.input.getSelectedItemData().id);
		console.log('Equipamento: ' + inputEquipamentoID);
	}
	
	return AutoCompleteEquipamento;
}());




$(function() {
	var autoCompleteServico = new Indeas.AutoCompleteServico();
	autoCompleteServico.enable();
	
	var autoCompleteEquipamento = new Indeas.AutoCompleteEquipamento;
	autoCompleteEquipamento.enable();
});

function getServId(inputID){
	this.tabela = $('.js-tabela-servico');	
	var resposta = $.ajax({
		data : {
			id : inputID
		},
		async : false,
		'type' : 'GET',
		'url' : '/os/excluirservico',
	});
	resposta.done(onItemAtualizadoNoServidor.bind(this));	
	function onItemAtualizadoNoServidor(html) {
		this.tabela.html(html);
	}
};