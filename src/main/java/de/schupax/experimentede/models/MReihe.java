package de.schupax.experimentede.models;

public class MReihe {
	private final int _wurfanzahl;
	private final int _nummer;

	public MReihe(int pWurfanzahl, int pNummer) {
		_nummer = pNummer;
		_wurfanzahl = pWurfanzahl;
	}

	public int getWurfanzahl() {
		return _wurfanzahl;
	}

	public int getNummer() {
		return _nummer;
	}
}
