package com.indeas.gestaoempreiteira.servico.validacao;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrdemServicoValidacao implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return OrdemServicoValidacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "veiculo.id", "");
		ValidationUtils.rejectIfEmpty(errors, "descricaoproblema", "");
		
	}

}
