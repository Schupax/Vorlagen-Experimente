package de.schupax.experimente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.schupax.Constants;
import de.schupax.experimentede.models.MDiagramm;
import de.schupax.experimentede.models.MReihe;

public class Wuerfelzerfallsexperiment implements IExperiment {

	private int _gruppenanzahl;
	private int _wuerfe;
	private List<MReihe> _reihenProGruppe;
	private boolean _einSheetProGruppe;
	private XSSFWorkbook _workbook;
	private Row _tableHeader;
	private CellStyle _headerStyle;

	public Wuerfelzerfallsexperiment(int pGruppenanzahl, List<MReihe> pReihenProGruppen, int pWuerfe) {
		this(pGruppenanzahl, pReihenProGruppen, true, pWuerfe);
	}

	public Wuerfelzerfallsexperiment(int pGruppenanzahl, List<MReihe> pReihenProGruppen, boolean pEinSheetProGruppe,
			int pWurfe) {
		_gruppenanzahl = pGruppenanzahl;
		_einSheetProGruppe = pEinSheetProGruppe;
		_reihenProGruppe = pReihenProGruppen;
		_wuerfe = pWurfe;
		_workbook = new XSSFWorkbook();
	}

	public XSSFWorkbook create() throws IOException {
		return createExcelDocument();
	}

	private XSSFWorkbook createExcelDocument() throws IOException {

		if (_einSheetProGruppe) {
			List<XSSFSheet> gruppenSheets = new ArrayList<XSSFSheet>();
			for (int i = 1; i <= _gruppenanzahl; i++) {

				XSSFSheet sheet = _workbook.createSheet("Gruppe" + i);
				gruppenSheets.add(sheet);
				einfuegenVonWuerfenUndHeadern("Protokoll f" + Constants.cUe + "r Gruppe " + i, sheet, _workbook);
				// Header der Reihen anlegen
				for (MReihe reihe : _reihenProGruppe) {
					Cell ReiheHeader = _tableHeader.createCell(reihe.getNummer() + 1);
					ReiheHeader.setCellValue("Reihe " + reihe.getNummer() + ", " + reihe.getWurfanzahl() + " W"
							+ Constants.cUe + "rfel");
					ReiheHeader.setCellStyle(_headerStyle);
					sheet.setColumnWidth(reihe.getNummer() + 1, 4000);
				}

				// Gruppendiagramm
				MDiagramm diagramm = new MDiagramm(sheet, 7, 4, 21, 26);
				diagramm.setTitle("Zerfallsgesetz");
				diagramm.setBottomAxisTitle("W" + Constants.cUe + "rfe");
				diagramm.setLeftAxisTitle(Constants.cUe + "brige W" + Constants.cUe + "rfel");
				diagramm.setLines(3, 3 + _wuerfe, 1, 1);
				for (MReihe reihe : _reihenProGruppe) {
					diagramm.addData(3, +_wuerfe, 1 + reihe.getNummer(), 1 + reihe.getNummer(),
							"Reihe " + reihe.getNummer());
				}
				diagramm.printData();
			}
			erstelleZusammenfassung(gruppenSheets);
		}
		FormulaEvaluator mainWorkbookEvaluator = _workbook.getCreationHelper().createFormulaEvaluator();
		mainWorkbookEvaluator.evaluateAll();
		return _workbook;
	}

	private void einfuegenVonWuerfenUndHeadern(String pHeaderTitle, XSSFSheet pSheet, XSSFWorkbook pWorkbook) {
		pSheet.setColumnWidth(0, 10000);
		Row header = pSheet.createRow(0);
		_headerStyle = pWorkbook.createCellStyle();
		XSSFFont font = pWorkbook.createFont(); // ((XSSFWorkbook) pWorkbook)
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		_headerStyle.setFont(font);
		_headerStyle.setWrapText(true);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue(pHeaderTitle);
		headerCell.setCellStyle(_headerStyle);
		_tableHeader = pSheet.createRow(2);
		Cell wurfcell = _tableHeader.createCell(1);
		wurfcell.setCellValue("Wurf");
		wurfcell.setCellStyle(_headerStyle);

		// Nummerierung der Würfe
		for (int j = 1; j <= _wuerfe; j++) {
			Row rowWurf = pSheet.createRow(3 + j);
			Cell cellWurfNummer = rowWurf.createCell(1);
			cellWurfNummer.setCellValue(j);
			cellWurfNummer.setCellStyle(_headerStyle);
		}
		// 0ten Wurf Anzahl der W�rfel
		Row rowWurfZero = pSheet.createRow(2 + 1);
		Cell cellWurfNummerZero = rowWurfZero.createCell(1);
		cellWurfNummerZero.setCellValue(0);
		cellWurfNummerZero.setCellStyle(_headerStyle);
		for (MReihe reihe : _reihenProGruppe) {
			Cell cellWurfNummerZeroReihe = rowWurfZero.createCell(1 + reihe.getNummer());
			cellWurfNummerZeroReihe.setCellValue(reihe.getWurfanzahl());
		}
	}

	private void erstelleZusammenfassung(List<XSSFSheet> pGruppenSheets) {
		XSSFSheet sheet = _workbook.createSheet("Zusammenfassung");
		einfuegenVonWuerfenUndHeadern("Zusammenfassung Zerfallsgesetz", sheet, _workbook);

		int x = 7;
		int y = 4;
		int x_offset = 14;
		int y_offset = 22;
		int reihenCounter = 0;
		for (MReihe reihe : _reihenProGruppe) {
			// Header der Reihen anlegen
			Cell ReiheHeader = _tableHeader.createCell(reihe.getNummer() + 1);
			ReiheHeader.setCellValue("Reihe " + reihe.getNummer() + ", " + reihe.getWurfanzahl() + " W" + Constants.cUe
					+ "rfel Mittelwert");
			ReiheHeader.setCellStyle(_headerStyle);
			sheet.setColumnWidth(reihe.getNummer() + 1, 4000);

			// Zusammenfassungsdiagramme pro Reihe
			int startCol = x;
			int startRow = y + reihenCounter * y_offset + reihenCounter;
			int endCol = x + x_offset;
			int endRow = y + (reihenCounter + 1) * y_offset + reihenCounter;
			MDiagramm diagramm = new MDiagramm(sheet, startCol, startRow, endCol, endRow);
			diagramm.setTitle(
					"Zerfallsgesetz f" + Constants.cUe + "r " + reihe.getWurfanzahl() + " W" + Constants.cUe + "rfe");
			diagramm.setBottomAxisTitle("W" + Constants.cUe + "rfe");
			diagramm.setLeftAxisTitle(Constants.cUe + "brige W" + Constants.cUe + "rfel");
			diagramm.setLines(3, 3 + _wuerfe, 1, 1);
			int gruppenNummer = 1;
			for (XSSFSheet gruppenSheet : pGruppenSheets) {
				diagramm.addData(3, +_wuerfe, 1 + reihe.getNummer(), 1 + reihe.getNummer(), "Gruppe " + gruppenNummer,
						gruppenSheet);
				gruppenNummer++;
			}

			for (int j = 1; j <= _wuerfe; j++) {
				String value = "IF(";
				String avg = " AVERAGE(";
				Row rowWurf = sheet.getRow(3 + j);
				Cell cellMittelwert = rowWurf.createCell(reihe.getNummer() + 1);
				boolean firstSheet = true;
				for (XSSFSheet gruppenSheet : pGruppenSheets) {
					if (!firstSheet) {
						avg += ", ";
					} else {
						firstSheet = false;
					}
					avg += gruppenSheet.getSheetName() + "!" + cellMittelwert.getAddress();
				}
				avg += ")";
				value += "ISERROR(" + avg + "), 0," + avg + ")";
				cellMittelwert.setCellFormula(value);
			}

			diagramm.addData(3, +_wuerfe, 1 + reihe.getNummer(), 1 + reihe.getNummer(), "Mittelwert");
			diagramm.printData();
			reihenCounter++;
		}
	}

}
