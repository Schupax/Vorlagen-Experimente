package de.schupax.start;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.schupax.Constants;
import de.schupax.experimente.IExperiment;

public class ConsoleStart {
	private static ParameterController cParameterController;

	public static void main(String[] args) {
		cParameterController = new ParameterController(args);
		if (cParameterController.gotArgument(Constants.cParameterHelp)) {
			cParameterController.printHelp();
		} else {
			try {
				System.out.print("Starte Experimenterstellung.");
				IExperiment experiment = cParameterController.getExperiment();
				printExperiment(experiment);
			} catch (IOException e) {
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
