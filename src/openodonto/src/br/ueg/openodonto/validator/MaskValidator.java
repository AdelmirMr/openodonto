package br.ueg.openodonto.validator;

import br.ueg.openodonto.validator.tipo.StringValidatorType;

public class MaskValidator extends AbstractValidator implements StringValidatorType{

	private String mask;
	
	
	public MaskValidator(Object value,String mask) {
		super(null,value);
		this.mask = mask;
	}
	
	public MaskValidator(Validator next,String mask) {
		super(next,next.getValue());
		this.mask = mask;
	}


	@Override
	public String getValue() {
		return super.getValue().toString();
	}

	@Override
	protected boolean validate() {
		if(!getValue().matches(mask)){
			setErrorMsg("O valor n�o tem o padr�o esperado");
			return false;
		}
		return true;
	}

}
