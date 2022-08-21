package de.schupax.experimentede.models;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class MDiagramm {

	private final XSSFChart _chart;
	private final XSSFSheet _sheet;
	private XDDFCategoryAxis _bottomAxis;
	private XDDFValueAxis _leftAxis;
	private XDDFLineChartData _data;
	private XDDFDataSource<String> _linesAxis;

	public MDiagramm(XSSFSheet pSheet, int pStartCol, int pStartRow, int pEndCol, int pEndRow) {
		_sheet = pSheet;
		XSSFDrawing drawing = pSheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, pStartCol, pStartRow, pEndCol, pEndRow);
		_chart = drawing.createChart(anchor);
		XDDFChartLegend legend = _chart.getOrAddLegend();
		legend.setPosition(LegendPosition.TOP_RIGHT);
		_bottomAxis = _chart.createCategoryAxis(AxisPosition.BOTTOM);
		_leftAxis = _chart.createValueAxis(AxisPosition.LEFT);
		_data = (XDDFLineChartData) _chart.createData(ChartTypes.LINE, _bottomAxis, _leftAxis);
	}

	public void setTitle(String pTitle) {
		_chart.setTitleText(pTitle);
		_chart.setTitleOverlay(false);
	}

	public void setBottomAxisTitle(String pTitle) {
		_bottomAxis.setTitle(pTitle);
	}

	public void setLeftAxisTitle(String pTitle) {
		_leftAxis.setTitle(pTitle);
	}

	public void setLines(int pStartRow, int pEndRow, int pStartCol, int pEndCol) {
		_linesAxis = XDDFDataSourcesFactory.fromStringCellRange(_sheet,
				new CellRangeAddress(pStartRow, pEndRow, pStartCol, pEndCol));
	}

	public void addData(int pStartRow, int pEndRow, int pStartCol, int pEndCol, String pTitle, XSSFSheet pSheet) {
		XDDFNumericalDataSource<Double> reiheSheet = XDDFDataSourcesFactory.fromNumericCellRange(pSheet,
				new CellRangeAddress(pStartRow, pEndRow, pStartCol, pEndCol));
		XDDFLineChartData.Series series = (XDDFLineChartData.Series) _data.addSeries(_linesAxis, reiheSheet);
		series.setTitle(pTitle, null);
		series.setSmooth(false);
		series.setMarkerStyle(MarkerStyle.DOT);
	}

	public void addData(int pStartRow, int pEndRow, int pStartCol, int pEndCol, String pTitle) {
		addData(pStartRow, pEndRow, pStartCol, pEndCol, pTitle, _sheet);
	}

	public void printData() {
		_chart.plot(_data);
	}

}
