package de.schupax.start.parameters;

public enum EParameterExperimente {
	GRUPPENANZAHL("Anzahl der SuS-Gruppen.", EParameterTypes.GANZZAHL), EIN_SHEET_PRO_GRUPPE("noch nicht implementiert!", EParameterTypes.YN),
	REIHEN_PRO_GRUPPE("Die Anzahl der Würfel pro Reihe mit Komma getrennt.", EParameterTypes.REIHEN_WUERFEL_ANZAHL), WUERFE_PRO_REIHE("Anzahl der Würfe die maximal mit den Würfeln durch geführt werden sollen", EParameterTypes.GANZZAHL);

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
