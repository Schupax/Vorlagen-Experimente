package de.schupax.start;

import java.io.IOException;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.schupax.Constants;
import de.schupax.experimente.ExperimentFactory;
import de.schupax.experimente.IExperiment;
import de.schupax.start.parameters.EExperimente;
import de.schupax.start.parameters.EParameterExperimente;

public class ConsoleStart {
	private static ParameterController cParameterController;

	public static void main(String[] args) {
		cParameterController = new ParameterController(args);
		if (cParameterController.gotArgument(Constants.cParameterHelp)) {
			cParameterController.printHelp();
		} else {
			try {
				System.err.print("Starte Experimenterstellung.");
				EExperimente eExperiment = cParameterController.getExperiment();
				List<Pair<EParameterExperimente, String>> parametersAndValues = cParameterController.getExperimentParametersAndValues();
				IExperiment experiment = ExperimentFactory.createExperiment(eExperiment,parametersAndValues );
				System.err.print("Erstellung abgeschlossen.");
				printExperiment(experiment);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void printExperiment(IExperiment pExperiment) throws IOException {
		XSSFWorkbook workbook = pExperiment.create();
		workbook.write(cParameterController.getOutputStream());
		workbook.close();
	}

}
