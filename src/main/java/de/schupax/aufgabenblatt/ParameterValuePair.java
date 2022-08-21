package de.schupax.aufgabenblatt;

public class ParameterValuePair {
	private final EParameter _parameter;
	private final String _value;

	public ParameterValuePair(EParameter pParamter, String pValue) {
		_parameter = pParamter;
		_value = pValue;
	}

	public EParameter getParameter() {
		return _parameter;
	}

	public String getValue() {
		return _value;
	}
}
