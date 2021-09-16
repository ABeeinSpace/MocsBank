/*
 Aidan Border
 09/10/2021
 CSC 2290
 Honor Code: I will practice academic and personal integrity and excellence of character and expect the same from
 others
 https://www.youtube.com/watch?v=dQw4w9WgXcQ
*/

package mocsbank;

import jdk.swing.interop.SwingInterOpUtils;

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
			System.exit(-4); // Bit of trivia here. Driver number 4 in Formula 1 is my favorite driver, Lando Norris.
			// Nobody's gonna see these exit codes, so I think I can have fun with them.
		}

		// Setting some parameters for the bank, namely the number of accounts that can be opened at any one time and
		// the maximum amount of transactions that can be performed in a given day.
		int maxAccounts = in.nextInt();
		int maxTransactions = in.nextInt();
		int numDays = in.nextInt();
		int numCommands = in.nextInt();
		int numCommandsExecuted = 0;

		MocsBankAccount[] accounts = new MocsBankAccount [maxAccounts];
		MocsBankTransaction[] transactions = new MocsBankTransaction[maxTransactions];

//		Command execution loop follows on next line. This will probably need to be broken out into a separate
//		function so that we can call it inside a loop for numDays.
		do {

			switch (in.next()) {
				case "OPENACCOUNT":
					openAccount(in, accounts);
					break;
				case "PRINTBALANCE":
					printBalance(accounts);
					break;
				case "DEPOSIT":
					break;
				case "WITHDRAW":
					break;
				case "TRANSFER":
					break;
				case "CLOSEACCOUNT":

					break;
				case "TRANSACTIONREPORT":
					printTransactionReport();
					break;
			}

		} while(numCommandsExecuted <= numCommands);

	}

	public static void endOfDay() {
		System.out.println("Implement meh");
	}

	public static void openAccount(Scanner in, MocsBankAccount[] accounts) {
		System.out.println("OPENACCOUNT:");
		int accountNumber = in.nextInt();
		String firstName = in.next();
		String lastName = in.next();
		double balance = in.nextDouble();

		accounts[MocsBankAccount.getNumAccounts() + 1] = new MocsBankAccount(accountNumber, firstName, lastName, accountNumber);

	}



	public static void deposit(MocsBankAccount[] accounts, Scanner in) {
		System.out.println("DEPOSIT:");
		// Checking for accounts in the bank. If there's no accounts, we need to print a separate error message.
		if (MocsBankAccount.getNumAccounts() == 0) {
			System.out.println("    Error: cannot make deposit. There are no open accounts in MocsBank.");
		}

		int accountNum = in.nextInt();
		double depositAmount = in.nextDouble();
		int accountIndex = binarySearch(accounts, accountNum);
		if (accountIndex == -1) { //Check to make sure account actually exists. If not, print an
			// error.
			System.out.printf("    Error: cannot make deposit. Account # %d was not found in the system.\n",
					accountNum);
		}

		double newBalance = accounts[accountIndex].getAccountBalance() + depositAmount;
		accounts[accountIndex].setAccountBalance(newBalance);
		MocsBankTransaction deposit = new MocsBankTransaction("Deposit", accountNum, depositAmount,
				accounts[accountIndex].getAccountBalance(), newBalance);

		System.out.println();

	}

	public static void withdraw(MocsBankAccount[] accounts, Scanner in) {
		System.out.println("WITHDRAW:");
		// Checking for accounts in the bank. If there's no accounts, we need to print a separate error message.
		if (MocsBankAccount.getNumAccounts() == 0) {
			System.out.println("    Error: cannot make withdrawal. There are no open accounts in MocsBank.");
		}

		int accountNum = in.nextInt();
		double withdrawnAmount = in.nextDouble();
		if (binarySearch(accounts, accountNum) == -1) { //Check to make sure account actually exists. If not, print an
			// error.
			System.out.printf("    Error: cannot make withdrawal. Account # %d was not found in the system.\n",
					accountNum);
		}

	}


	public static void closeAccount() {
		System.out.println("CLOSEACCOUNT:");
	}

	public static void printTransactionReport () {
		System.out.println("TRANSACTIONREPORT:");

	}

	public static void printBalance(MocsBankAccount[] accounts) {
		System.out.println("PRINTBALANCE:");
	}

	public static int binarySearch(MocsBankAccount[] accounts, int value) {
		int low = 0;
		int high = accounts.length;
		int mid = (low + high) / 2;

		while (low <=high ) {
			mid = (low + high) / 2;
			if (value == accounts[mid].getAccountNumber()) {
				return mid;
			} else if (value < accounts[mid].getAccountNumber()) {
				high = mid - 1;
			} else if (value > accounts[mid].getAccountNumber()) {
				low = mid + 1;
			}
		}
		return -1;
	}

}
