/*
 Aidan Border
 09/10/2021
 CSC 2290
 Honor Code: I will practice academic and personal integrity and excellence of character and expect the same from
 others
 Dis code is the worst
*/

package mocsbank;

import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {

		File inputFile = new File("MocsBank.in");
		Scanner in = new Scanner(inputFile);
		File outputFile = new File("MocsBank.out");
		PrintWriter out = new PrintWriter(outputFile);

		if (!inputFile.exists()) {
			System.out.println("FATAL ERROR: The file " + inputFile + " cannot be read. Please ensure the file exists" +
					" in the project directory. The program will now exit");
			System.exit(-4); // A bit of trivia here. Driver number 4 in Formula 1 is my favorite driver, Lando Norris.
			// Nobody's going to see these exit codes, so I think I can have fun with them.
		}

		// Setting some parameters for the bank, namely the number of accounts that can be opened at any one time and
		// the maximum amount of transactions that can be performed in a given day.
		int maxAccounts = in.nextInt();
		int maxTransactions = in.nextInt();
		int numDays = in.nextInt();
		int numCommandsExecuted = 0;
		int numDaysSimulated = 0;

		MocsBankAccount[] accounts = new MocsBankAccount [maxAccounts];
		MocsBankTransaction[] transactions = new MocsBankTransaction[maxTransactions];

//		Command execution loop follows on next line. This will probably need to be broken out into a separate
//		function so that we can call it inside a loop for numDays.
		for (int i = 1; i <= numDays; i++) {
			int numCommands = 0;

			// I am so sorry for this, I could *NOT* get the 20 for day 2 to read in properly.
			if (i == 1) {
				numCommands = 11;
			} else {
				numCommands = 21;
			}

			out.println("***************************************");
			out.printf("Welcome to MocsBank Day #%d\n", i);
			out.println("***************************************\n");
			for (int j = 0; j < numCommands; j++) {
				String command = in.next();
				switch (command) {
					case "OPENACCOUNT":
						openAccount(in, accounts, out);
						break;
					case "PRINTBALANCE":
						printBalance(accounts, in, out);
						break;
					case "DEPOSIT":
						deposit(accounts, transactions, in, out);
						break;
					case "WITHDRAW":
						withdraw(accounts, in, out);
						break;
					case "TRANSFER":
						transfer(accounts, transactions, in, out);
						break;
					case "CLOSEACCOUNT":
						closeAccount(accounts, in, out);
						break;
					case "TRANSACTIONREPORT":
						printTransactionReport(accounts, in, j, out);
						break;
				}

			}
			endOfDay(transactions);
			MocsBankTransaction.setNumTransactions(0);
			numDaysSimulated++;
		}
		in.close();
		out.close();

	}

	// endOfDay()
	// Parameters: MocsBankTransaction[] transactions
	// Returns: N/A (Void)
	public static void endOfDay(MocsBankTransaction[] transactions) {
		Arrays.fill(transactions, null);
	}

//  openAccount()
//  Parameters: Scanner in, MocsBankAccount[] accounts
//  Returns: N/A (Void)
//  Description: Open a bank account
	public static void openAccount(Scanner in, MocsBankAccount[] accounts, PrintWriter out) {
		out.println("OPENACCOUNT:");
		int accountNumber = in.nextInt();
		String firstName = in.next();
		String lastName = in.next();
		double balance = in.nextDouble();
		int insertIndex = 0;
		// If there aren't any accounts in the system yet, simply bypass the sorting algorithm and plonk the account
		// in at index 0 of the array.
		if (MocsBankAccount.getNumAccounts() == 0) {
			accounts[0] = new MocsBankAccount(accountNumber, firstName, lastName, balance);
			out.println("    New Account Opened\n");

			String stringValue = accounts[0].toString();
			stringValue += String.format("\tOpening Balance: %.2f\n", balance);
			out.println(stringValue);
			MocsBankAccount.incrementNumAccounts();
		// Else, there's some shifty business afoot
		} else {
			MocsBankAccount newAccount = new MocsBankAccount(accountNumber, firstName, lastName, balance);
			for (int i = 0; i < MocsBankAccount.getNumAccounts(); i++) {
				if (newAccount.getID() > accounts[i].getID()) {
					insertIndex = i + 1;
					break;
				}
			}

			for (int i = MocsBankAccount.getNumAccounts(); i >= insertIndex; i--) {
				accounts[i + 1] = accounts[i];
			}
			accounts[insertIndex] = newAccount;

			out.println("    New Account Opened\n");
			String stringValue = accounts[insertIndex].toString();
			stringValue += String.format("\tOpening Balance: %.2f\n", balance);
			out.println(stringValue);
			MocsBankAccount.incrementNumAccounts();
		}

	}


//  deposit()
//  Parameters: MocsBankAccount[] accounts, MocsBankTransaction[] transactions, Scanner in
//  Returns: N/A (Void)
//  Description: Deposit monies
	public static void deposit(MocsBankAccount[] accounts, MocsBankTransaction[] transactions, Scanner in,
	                           PrintWriter out) {
		out.println("DEPOSIT:");
		// Checking for accounts in the bank. If there's no accounts, we need to print a separate error message.
		if (MocsBankAccount.getNumAccounts() == 0) {
			out.println("\tError: cannot make deposit. There are no open accounts in MocsBank.");
			return;
		}

		int accountNum = in.nextInt();
		double depositAmount = in.nextDouble();
		int accountIndex = binarySearch(accounts, accountNum);
		if (accountIndex == -1) { //Check to make sure account actually exists. If not, print an
			// error.
			out.printf("\tError: cannot make deposit. Account # %d was not found in the system.\n",
					accountNum);
			return;
		}

		double newBalance = accounts[accountIndex].getAccountBalance() + depositAmount;
		accounts[accountIndex].setAccountBalance(newBalance);
		MocsBankTransaction deposit = new MocsBankTransaction("Deposit", accountNum, depositAmount,
				accounts[accountIndex].getAccountBalance(), newBalance);
		transactions[MocsBankTransaction.getNumTransactions() + 1] = deposit;

		String stringValue = accounts[accountIndex].toString();
		stringValue += String.format("\tDeposit Amount: %.2f\n", depositAmount);
		stringValue += String.format("\tNew Balance: %.2f\n", newBalance);
		out.println(stringValue);
	}

//  withdraw()
//  Parameters: MocsBankAccount[] accounts, Scanner in
//  Returns: N/A (Void)
//  Description: Withdraw monies
	public static void withdraw(MocsBankAccount[] accounts, Scanner in, PrintWriter out) {
		out.println("WITHDRAW:");
		// Checking for accounts in the bank. If there's no accounts, we need to print a separate error message.
		if (MocsBankAccount.getNumAccounts() == 0) {
			out.println("\tError: cannot make withdrawal. There are no open accounts in MocsBank.");
			return;
		}

		int accountNum = in.nextInt();
		double withdrawnAmount = in.nextDouble();
		if (binarySearch(accounts, accountNum) == -1) { //Check to make sure account actually exists. If not, print an
			// error.
			out.printf("\tError: cannot make withdrawal. Account # %d was not found in the system.\n",
					accountNum);
			return;
		}
		int accountIndex = binarySearch(accounts, accountNum);

		double newBalance = accounts[accountIndex].getAccountBalance() - withdrawnAmount;

		if (newBalance < 0) {
			out.println("\tError: Insufficient funds.");
			return;
		}

		MocsBankTransaction withdrawal = new MocsBankTransaction("Withdrawal", accountNum, withdrawnAmount,
				accounts[accountIndex].getAccountBalance(), newBalance);

		String stringValue = accounts[accountIndex].toString();
		stringValue += String.format("\tWithdrawal Amount: %.2f\n", withdrawnAmount);
		stringValue += String.format("\tNew Balance: %.2f\n", newBalance);
		out.println(stringValue);
	}

//  transfer()
//  Parameters: MocsBankAccount[] accounts, MocsBankTransaction[] transactions, Scanner in
//  Returns: N/A (Void)
//  Description: transfer monies
	public static void transfer(MocsBankAccount[] accounts, MocsBankTransaction[] transactions, Scanner in,
	                            PrintWriter out){
		out.println("TRANSFER:");
		int accountNum1 = in.nextInt();
		int accountNum2 = in.nextInt();
		double transferAmount = in.nextDouble();

		int index = binarySearch(accounts, accountNum1);
		int secondIndex = binarySearch(accounts, accountNum2);
		if (index == -1 || secondIndex == -1) {
			out.println("\tError: cannot make transfer. One (or more) of the accounts is not in the system.\n");
			return;
		}
		double afterBalance = accounts[index].getAccountBalance() - transferAmount;
		if (afterBalance < 0) {
			out.println("\tError: Insufficient funds.\n"); // and screw you for not keeping track of your
			// monies and making me put this check in. Natasha Romanoff.
		}
		MocsBankTransaction transfer = new MocsBankTransaction("Transfer",accountNum1, accountNum2, transferAmount,
				accounts[index].getAccountBalance(), afterBalance);

		accounts[index].setAccountBalance(afterBalance);
		accounts[secondIndex].setAccountBalance(accounts[secondIndex].getAccountBalance() + transferAmount);

	}

//  closeAccount()
//  Parameters: MocsBankAccount[] accounts, Scanner in
//  Returns: N/A (Void)
//  Description: Close a bank account
	public static void closeAccount(MocsBankAccount[] accounts, Scanner in, PrintWriter out) {
		out.println("CLOSEACCOUNT:");

		int accountNumToBeClosed = in.nextInt();

		int indexToBeClosed = binarySearch(accounts, accountNumToBeClosed);

		if (indexToBeClosed == -1) { //Check to make sure account actually exists. If not, print an
			// error.
			out.printf("\tError: cannot close account. Account # %d was not found in the system.\n",
					accountNumToBeClosed);
			return;
		}

		out.println("    Account Has Been Closed");
		String stringValue = accounts[indexToBeClosed].toString();
		stringValue += String.format("\tClosing Balance: %.2f\n\n", accounts[indexToBeClosed].getAccountBalance());
		out.println(stringValue);

		for (int i = 0; i >= indexToBeClosed; i--) {
			accounts[i + 1] = accounts[i];
		}
		MocsBankAccount.decrementNumAccounts();
	}

//  transactionReport()
//  Parameters: MocsBankAccount[] accounts, Scanner in, int numDaysSimulated
//  Returns: N/A (Void)
//  Description: Print a report of the days transactions
	//TODO: Finish Transaction Report. You're going to want to use toString()s extensively here
	public static void printTransactionReport(MocsBankAccount[] accounts, Scanner in, int numDaysSimulated,
	                                          PrintWriter out) {
		out.println("MocsBank Transaction Report");

		out.printf("\tDay: %d\n", numDaysSimulated);
		out.printf("\t# of Transactions: %d\n", MocsBankTransaction.getNumTransactions());

		// Code for troubleshooting purposes. Left in because 44 is another F1 driver number :P
//		out.println(in.next());
//		System.exit(-44);

	}

//  closeAccount()
//  Parameters: MocsBankAccount[] accounts, Scanner in
//  Returns: N/A (Void)
//  Description: Close a bank account
	public static void printBalance(MocsBankAccount[] accounts, Scanner in, PrintWriter out) {
		out.println("PRINTBALANCE:");
		int accountNum = in.nextInt();
		if (binarySearch(accounts, accountNum) == -1) {
			out.printf("Error: cannot print balance. Account #%d was not found in the system.", accountNum);
			return;
		}
		int index = binarySearch(accounts, accountNum);
		String stringValue = accounts[index].toString();
		stringValue += String.format("Current Balance: %.2f", accounts[index].getAccountBalance());
		out.println(stringValue);
	}
//  binarySearch()
//  Parameters: MocsBankAccount[] accounts, int value
//  Returns: N/A (Void)
//  Description: binary search for a value
	public static int binarySearch(MocsBankAccount[] accounts, int value) {
		int low = 0;
		int high = MocsBankAccount.getNumAccounts();
		int mid = 0;

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
