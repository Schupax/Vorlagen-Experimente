package de.schupax.start.parameters;

import java.util.regex.Pattern;

public enum EParameterTypes {
	GANZZAHL(Pattern.compile("[0-9]*")), TEXT(Pattern.compile(".*",Pattern.DOTALL)), YN(Pattern.compile("[yn]")), REIHEN_WUERFEL_ANZAHL(
			Pattern.compile("1\\.[1-9][0-9](,2\\.[1-9][0-9](,3\\.[1-9][0-9](,4\\.[1-9][0-9](,5\\.[1-9][0-9])?)?)?)?"));

	private final Pattern _pattern;

	private EParameterTypes(Pattern pPattern) {
		_pattern = pPattern;
	}

	public Pattern getPattern() {
		return _pattern;
	}
}
