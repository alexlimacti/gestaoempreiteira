var Indeas = Indeas || {};

Indeas.MarcaCadastroRapido = (function() {
	
	function MarcaCadastroRapido() {
		this.modal = $('#modalCadastroRapidoMarca');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-marca-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeMarca = $('#descricaoMarca');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-marca');
	}
	
	MarcaCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeMarca.focus();
	}
	
	function onModalClose() {
		this.inputNomeMarca.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var descricaoMarca = this.inputNomeMarca.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ descricao: descricaoMarca }),
			error: onErroSalvandoMarca.bind(this),
			success: onMarcaSalvo.bind(this)
		});
	}
	
	function onErroSalvandoMarca(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onMarcaSalvo(marca) {
		var comboMarca = $('#marca');
		comboMarca.append('<option value=' + marca.id + '>' + marca.descricao + '</option>');
		comboMarca.val(marca.id);
		this.modal.modal('hide');
	}
	
	return MarcaCadastroRapido;
	
}());

$(function() {
	var estiloCadastroRapido = new Indeas.MarcaCadastroRapido();
	estiloCadastroRapido.iniciar();
});
