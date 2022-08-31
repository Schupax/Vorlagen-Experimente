package de.schupax.experimente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import de.schupax.Constants;
import de.schupax.experimentede.models.MReihe;
import de.schupax.start.parameters.EExperimente;
import de.schupax.start.parameters.EParameterExperimente;

public class ExperimentFactory {
	public static IExperiment createExperiment(EExperimente pExperiment,
			List<Pair<EParameterExperimente, String>> pParametersAndValues) {
		IExperiment result = null;
		int gruppenAnzahl = 8;
		int wuerfe = 30;
		List<MReihe> reihen = Arrays.asList(new MReihe(50, 1), new MReihe(30, 2));
		EExperimente exp = (pExperiment != null) ? pExperiment : EExperimente.ZERFALL_MIT_WUERFELN;

		switch (exp) {
		case ZERFALL_MIT_WUERFELN:
			for (Pair<EParameterExperimente, String> pair : pParametersAndValues) {
				if (pair.getKey().equals(EParameterExperimente.GRUPPENANZAHL)) {
					gruppenAnzahl = Integer.parseInt(pair.getValue());
				}
				if (pair.getKey().equals(EParameterExperimente.WUERFE_PRO_REIHE)) {
					wuerfe = Integer.parseInt(pair.getValue());
				}
				if (pair.getKey().equals(EParameterExperimente.REIHEN_PRO_GRUPPE)) {
					String value = pair.getValue();
					int reihenNummer = 1;
					reihen = new ArrayList<MReihe>();
					do {
						if (value.contains(Constants.cComma)) {
							String anzahl = value.substring(0, value.indexOf(Constants.cComma));
							reihen.add(new MReihe(Integer.parseInt(anzahl), reihenNummer));
							value = value.substring(value.indexOf(Constants.cComma) + 1);
						} else {
							reihen.add(new MReihe(Integer.parseInt(value), reihenNummer));
							value = "";
						}
						reihenNummer++;

					} while (value.length() > 0);
				}
			}
			result = new Wuerfelzerfallsexperiment(gruppenAnzahl, reihen, wuerfe);
			break;
		default:
			result = new Wuerfelzerfallsexperiment(gruppenAnzahl, reihen, wuerfe);
			break;
		}
		return result;
	}
}
