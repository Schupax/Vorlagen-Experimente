package de.schupax.aufgabenblatt;

public enum EParameter {
	HINWEIS("Lies dir alle Aufgaben und die Hinweise gründlich durch.", false), TITEL("", false), NAME("Name:", true),
	DATUM("Datum:", true), HEADERTITEL("TITEL", true);

	private final String _defaultValue;
	private final boolean _isHeaderParameter;

	private EParameter(String pDeafultvalue, boolean pIsHeaderParameter) {
		_defaultValue = pDeafultvalue;
		_isHeaderParameter = pIsHeaderParameter;
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	public boolean isHeaderParameter() {
		return _isHeaderParameter;
	}
}
