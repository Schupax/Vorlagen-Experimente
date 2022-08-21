package de.schupax.start.parameters;

public enum EOutputParameters {
	FILENAME("Name der Ausgegebenen Datei.", EParameterTypes.TEXT),
	DIRECTORY("Verzeichnis in dem die Datei ausgegeben werden soll.", EParameterTypes.TEXT),
	CREATE_DIR("y = Verzeichnis erzeugen wenn nicht vorhanden, default ist false.", EParameterTypes.YN),
	DIR_FILENAME("Verzeichnis und Name der ausgegebenen Datei.", EParameterTypes.TEXT);

	private final String _hinweis;
	private final EParameterTypes _type;

	private EOutputParameters(String pHinweis, EParameterTypes pType) {
		_hinweis = pHinweis;
		_type = pType;
	}

	public String getHinweis() {
		return _hinweis;
	}

	public EParameterTypes getType() {
		return _type;
	}
}
