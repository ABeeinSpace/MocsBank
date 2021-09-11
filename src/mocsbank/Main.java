// Aidan Border
// 09/10/2021
// CSC 2290
// Honor Code: I will practice academic and personal integrity and excellence of character and expect the same from
// others

package mocsbank;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		File inputFile = new File("MocsBank.in");
		Scanner in = new Scanner(System.in);
		File outputFile = new File("MocsBank.out");
		PrintWriter out = new PrintWriter(outputFile);

		if (!inputFile.exists()) {
			System.out.println("FATAL ERROR: The file " + inputFile + " cannot be read. Please ensure the file exists" +
					" in the project directory. The program will now exit");
			System.exit(-1);
		}

		// Setting some parameters for the bank, namely the number of accounts that can be opened at any one time and
		// the maximum amount of transactions that can be performed in a given day.
		int maxAccounts = in.nextInt();
		int maxTransactions = in.nextInt();
		int numDays = in.nextInt();
		int numCommands = in.nextInt();
		int numCommandsExecuted = 0;

//		Command execution loop follows on next line. This will probably need to be broken out into a separate
//		function so that we can call it inside a loop for numDays.
		do {

		} while(numCommandsExecuted <= numCommands);

		// endOfDay(); // Function call to do a couple of things necessary for closeout of a given day.
	}
}
