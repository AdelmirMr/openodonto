package br.ueg.openodonto.validator;

import br.ueg.openodonto.validator.tipo.NumericValidatorType;

public class NumberSizeValidator extends AbstractValidator implements NumericValidatorType{
	
	private int max;
	private int min;
	
	public NumberSizeValidator(Validator validator, int max,int min) {
		super(validator, validator.getValue());
		this.max = max;
		this.min = min;
	}
	
	public NumberSizeValidator(Object value, int max,int min) {
		super(null, value);
		this.max = max;
		this.min = min;
	}
	
	@Override
	public Number getValue() {
		return Double.valueOf(super.getValue().toString());
	}
	
	@Override
	protected boolean validate() {
		if(getValue().intValue() > max){
			setErrorMsg("N�mero muito grande : M�ximo permitido = " + max);
			return false;
		}else if(getValue().intValue() < min){
			setErrorMsg("N�mero muito pequeno : M�nimo exigido = " + min);
			return false;
		}
		return true;
	}
	
}
