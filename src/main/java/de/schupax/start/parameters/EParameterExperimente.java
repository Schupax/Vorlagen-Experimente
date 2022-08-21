package de.schupax.start.parameters;

public enum EParameterExperimente {
	GRUPPENANZAHL("", EParameterTypes.GANZZAHL), EIN_SHEET_PRO_GRUPPE("", EParameterTypes.YN),
	REIHEN_PRO_GRUPPE("", EParameterTypes.REIHEN_WUERFEL_ANZAHL), WUERFE_PRO_REIHE("", EParameterTypes.GANZZAHL);

	private final String _beschreibung;

	private final EParameterTypes _type;

	private EParameterExperimente(String pBeschreibung, EParameterTypes pType) {
		_beschreibung = pBeschreibung;
		_type = pType;
	}

	public String getBeschreibung() {
		return _beschreibung;
	}

	public EParameterTypes getType() {
		return _type;
	}
}
