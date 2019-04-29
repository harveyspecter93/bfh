package gruppenarbeit3_bankomatsimulator;

import java.util.Scanner;
import java.util.logging.Logger;

public class Bankomat {
	private static final Logger LOGGER = Logger.getLogger(Bankomat.class.getName());

	public static void main(String[] args) {
		// Formatting logger
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		
		LOGGER.info("Bankomat startet...");
		LOGGER.info("Bitte schieben Sie die Bankkarte ein.");
		String input = readInput();
		LOGGER.info(input);

	}

	private static String readInput() {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("");
		
		while(input.hasNextLine()) {
			sb.append(input.nextLine());
		}
		input.close();
		return sb.toString();
	}

}
