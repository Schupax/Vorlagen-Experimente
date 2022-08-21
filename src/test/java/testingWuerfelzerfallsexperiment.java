import java.io.IOException;
import java.util.Arrays;

import de.schupax.experimente.Wuerfelzerfallsexperiment;
import de.schupax.experimentede.models.MReihe;
import de.schupax.start.ConsoleStart;

public class testingWuerfelzerfallsexperiment {
	public static void main(String[] args) {
		Wuerfelzerfallsexperiment exp = new Wuerfelzerfallsexperiment(8,
				Arrays.asList(new MReihe(50, 1),new MReihe(30, 2)), 30);
		try {
			ConsoleStart.printExperiment(exp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}