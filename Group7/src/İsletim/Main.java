package İsletim;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Dispatcher dl = new Dispatcher();
		ProcessReader process = new ProcessReader(dl);
		process.processread();
	}

}
