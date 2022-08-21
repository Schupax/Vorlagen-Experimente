import java.util.ArrayList;
import java.util.List;

import de.schupax.aufgabenblatt.AufgabenblattCreator;
import de.schupax.aufgabenblatt.ParameterValuePair;

public class testingWordCreation {

	public static void main(String[] args) throws Exception {
		List<ParameterValuePair> values = new ArrayList<ParameterValuePair>();
		AufgabenblattCreator aufgabenblattCreator = new AufgabenblattCreator(values);
		aufgabenblattCreator.createAufgabenblatt();
	}
}
