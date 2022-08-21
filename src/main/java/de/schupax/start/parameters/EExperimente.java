package de.schupax.start.parameters;

import java.util.Arrays;
import java.util.List;

import de.schupax.experimente.Wuerfelzerfallsexperiment;

@SuppressWarnings("rawtypes")
public enum EExperimente {
	ZERFALL_MIT_WUERFELN("Würfelexperiment - Zerfallsgesetzt", Arrays.asList(EParameterExperimente.GRUPPENANZAHL,
			EParameterExperimente.EIN_SHEET_PRO_GRUPPE),Wuerfelzerfallsexperiment.class);

	private final String _beschreibung;
	private final List<EParameterExperimente> _parameters;
	
	private final Class _experiment;

	private EExperimente(String pBeschreibung, List<EParameterExperimente> pParameters, Class pExperiment) {
		_beschreibung = pBeschreibung;
		_parameters = pParameters;
		_experiment = pExperiment;
	}

	public String getBeschreibung() {
		return _beschreibung;
	}

	public List<EParameterExperimente> getParameters() {
		return _parameters;
	}

	public Class getExperiment() {
		return _experiment;
	}
}