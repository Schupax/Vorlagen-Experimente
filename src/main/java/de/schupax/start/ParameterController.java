package de.schupax.start;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import de.schupax.Constants;
import de.schupax.experimente.IExperiment;
import de.schupax.start.parameters.EExperimente;
import de.schupax.start.parameters.EOutputParameters;
import de.schupax.start.parameters.EParameterExperimente;

public class ParameterController {

	private final String[] _arguments;

	public ParameterController(String[] pArguments) {
		_arguments = pArguments;
	}

	public FileOutputStream getOutputStream() throws FileNotFoundException {
		String filename = Constants.cDefaultFileNameWuerfelexperiment;
		String dir = Constants.cDefaultDot;
		String dirFilename = null;
		Boolean dirCreate = false;
		for (int i = 0; i < _arguments.length; i++) {
			// Dateiname
			if (_arguments[i].startsWith(EOutputParameters.DIR_FILENAME.name())) {
				filename = getArgumentValue(_arguments[i]);
			}
			// Verzeichnis
			if (_arguments[i].startsWith(EOutputParameters.DIRECTORY.name())) {
				dir = getArgumentValue(_arguments[i]);
			}
			// Verzeichnis mit Dateiname
			if (_arguments[i].startsWith(EOutputParameters.DIR_FILENAME.name())) {
				dirFilename = getArgumentValue(_arguments[i]);
			}
			// Verzeichnis erzeugen, wenn nicht vorhanden
			if (_arguments[i].startsWith(EOutputParameters.CREATE_DIR.name())) {
				dirCreate = getArgumentValue(_arguments[i]).toLowerCase().equals(Constants.cLowY);

			}
		}
		if (dirFilename != null) {
			dirFilename = (dirFilename.endsWith(Constants.cDefaultDot + Constants.cXLSX)) ? dirFilename
					: dirFilename + Constants.cDefaultDot + Constants.cXLSX;
		} else {
			File f = new File(dir);
			String path = f.getAbsolutePath();
			dirFilename = path.substring(0, path.length() - 1) + filename;
		}
		return setFileOutputStream(dirFilename, dirCreate);
	}

	public String getArgumentValue(String pArgument) {
		if (pArgument.contains(Constants.cEquals)) {
			return pArgument.substring(pArgument.indexOf(Constants.cEquals) + 1, pArgument.length());
		} else {
			return null;
		}
	}

	public Boolean gotArgument(String pArgument) {
		for (int i = 0; i < _arguments.length; i++) {
			if (_arguments[i].startsWith(pArgument)) {
				return true;
			}
		}
		return false;
	}
	
	private String getArgument(String pArgument) {
		for (int i = 0; i < _arguments.length; i++) {
			if (_arguments[i].startsWith(pArgument)) {
				return _arguments[i];
			}
		}
		return null;
	}

	private FileOutputStream setFileOutputStream(String pFilelocation, Boolean pCreateDir)
			throws FileNotFoundException {
		String filename = pFilelocation;
		filename = (filename.endsWith(Constants.cDefaultDot + Constants.cXLSX)) ? filename
				: filename + Constants.cDefaultDot + Constants.cXLSX;
		if (pCreateDir) {
			new File(filename).mkdirs();
		}
		return new FileOutputStream(filename);
	}

	public void printHelp() {
		System.out.println(Constants.cParameterHinweisHelp);
		String helpValue = getArgumentValue(getArgument(Constants.cParameterHelp));
		for (EOutputParameters outputParameter : EOutputParameters.values()) {
			System.out.println(outputParameter.name().toLowerCase() + " - " + outputParameter.getHinweis());
		}
		for (EExperimente experimente : EExperimente.values()) {
			System.out.println(Constants.cParameterHelp + Constants.cEquals + experimente.name().toLowerCase() + " - "
					+ Constants.cHilfeZumExperiment + experimente.getBeschreibung());
		}
		if (helpValue != null) {
			try {
			EExperimente experiment = EExperimente.valueOf(helpValue.toUpperCase());
			if (experiment != null) {
				System.out.println(Constants.cHilfeZumExperiment + experiment.getBeschreibung());
				for (EParameterExperimente parameterExperimente : experiment.getParameters()) {
					System.out.println(parameterExperimente.name().toLowerCase() + " - " + parameterExperimente.getBeschreibung());
				}
			}
			}catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	public IExperiment getExperiment() {
		return null;
	}
}
