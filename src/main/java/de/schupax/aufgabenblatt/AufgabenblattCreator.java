package de.schupax.aufgabenblatt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import de.schupax.Constants;

public class AufgabenblattCreator {
	private List<ParameterValuePair> _values;
	private XWPFDocument _doc;
	private String _output;

	public AufgabenblattCreator(List<ParameterValuePair> pValues, String pOutput) {
		_values = pValues;
		if (pOutput != null) {
			_output = pOutput;
		} else {
			_output = Constants.cDefaultDoc;
		}
	}

	public AufgabenblattCreator(List<ParameterValuePair> pValues) {
		this(pValues, Constants.cDefaultDoc);
	}

	public void createAufgabenblatt() throws IOException {
		loadTemplate();
		replaceHeader();
		replaceBody();
		writeAufgabenblatt();
	}

	private void loadTemplate() throws IOException {
		_doc = new XWPFDocument(Files.newInputStream(Paths.get(Constants.cTemplate)));
	}

	private void writeAufgabenblatt() throws IOException {
		FileOutputStream out = new FileOutputStream(_output);
		_doc.write(out);
	}

	private void replaceHeader() {
		for (EParameter headerParameter : EParameter.values()) {
			if (headerParameter.isHeaderParameter()) {
				System.out.println(headerParameter);
				boolean gotParameter = false;
				for (ParameterValuePair parameterValuePair : _values) {
					if (parameterValuePair.getParameter().equals(headerParameter)) {
						gotParameter = true;
						replaceParameterInHeader(parameterValuePair);
					}
				}
				if (!gotParameter) {
					replaceParameterInHeader(new ParameterValuePair(headerParameter, null));
				}
			}
		}
	}

	private void replaceBody() {
		for (EParameter headerParameter : EParameter.values()) {
			if (!headerParameter.isHeaderParameter()) {
				System.out.println(headerParameter);
				boolean gotParameter = false;
				for (ParameterValuePair parameterValuePair : _values) {
					if (parameterValuePair.getParameter().equals(headerParameter)) {
						gotParameter = true;
						replaceParameterInHeader(parameterValuePair);
						replaceParameter(parameterValuePair);
					}
				}
				if (!gotParameter) {
					replaceParameterInHeader(new ParameterValuePair(headerParameter, null));
					replaceParameter(new ParameterValuePair(headerParameter, null));
				}
			}
		}
	}

	private void replaceParameter(ParameterValuePair pParameterValuePair) {
		List<XWPFParagraph> xwpfParagraphList = _doc.getParagraphs();
		// Iterate over paragraph list and check for the replaceable text in each
		// paragraph
		for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
			for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
				String docText = xwpfRun.getText(0);
				// replacement and setting position
				docText = docText.replace(pParameterValuePair.getParameter().name(),
						(pParameterValuePair.getValue() != null) ? pParameterValuePair.getValue()
								: pParameterValuePair.getParameter().getDefaultValue());
				xwpfRun.setText(docText, 0);
			}
		}
	}

	private void replaceParameterInHeader(ParameterValuePair pParameterValuePair) {
		List<XWPFHeader> headerList = _doc.getHeaderList();
		// Iterate over paragraph list and check for the replaceable text in each
		// paragraph
		for (XWPFHeader xwpfHeader : headerList) {
			List<XWPFParagraph> listParagraph = xwpfHeader.getListParagraph();
			for (XWPFParagraph xwpfParagraph : listParagraph) {

				for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
					String docText = xwpfRun.getText(0);
					System.err.println(xwpfParagraph.getText());
					// replacement and setting position
					docText = docText.replace(pParameterValuePair.getParameter().name(),
							(pParameterValuePair.getValue() != null) ? pParameterValuePair.getValue()
									: pParameterValuePair.getParameter().getDefaultValue());
					xwpfRun.setText(docText, 0);
				}
			}

		}
	}
}
