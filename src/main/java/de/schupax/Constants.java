package de.schupax;

public class Constants {
	public static final String cResourves = "src/main/resources";
	public static final String cUe = "ü";
	public static final String cTemplate = cResourves + "/Arbeitsblatttemplate.docx";
	public static final String cDefaultDot = ".";
	public static final String cDefaultFileName = "tmp";
	public static final String cXLSX = "xlsx";
	public static final String cDOCX = "docx";
	public static final String cDefaultDoc = cDefaultFileName + cDefaultDot + cDOCX;
	public static final String cDefaultCalc = cDefaultFileName + cDefaultDot + cXLSX;
	public static final String cDefaultFileNameWuerfelexperiment = "W" + cUe + "rfelexperiment" + Constants.cDefaultDot
			+ Constants.cXLSX;
	public static final String cLowY = "y";
	public static final String cEquals = "=";
	public static final String cHilfeZumExperiment = "Hilfe zum Experiment: ";
	public static final String cComma = ",";

	// Parameter
	public static final String cParameterHelp = "help";
	public static final String cParameterExperiment = "experiment";

	// Parameter Hinweise
	public static final String cParameterHinweisHelp = "Hilfe:";
	public static final String cParameterHinweisExperiment = "Experimente:";

}
