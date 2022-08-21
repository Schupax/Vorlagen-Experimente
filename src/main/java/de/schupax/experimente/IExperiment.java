package de.schupax.experimente;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface IExperiment {
	public XSSFWorkbook create() throws IOException;
}
