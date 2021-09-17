/*
 Aidan Border
 09/10/2021
 CSC 2290
 Honor Code: I will practice academic and personal integrity and excellence of character and expect the same from
 others
 https://www.youtube.com/watch?v=dQw4w9WgXcQ
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
		int numCommands = in.nextInt();
		int numCommandsExecuted = 0;
		int numDaysSimulated = 0;

		MocsBankAccount[] accounts = new MocsBankAccount [maxAccounts];
		MocsBankTransaction[] transactions = new MocsBankTransaction[maxTransactions];

//		Command execution loop follows on next line. This will probably need to be broken out into a separate
//		function so that we can call it inside a loop for numDays.
		while (numDaysSimulated <= numDays) {
			do {

				switch (in.next()) {
					case "OPENACCOUNT":
						openAccount(in, accounts);
						break;
					case "PRINTBALANCE":
						printBalance(accounts, in);
						break;
					case "DEPOSIT":
						deposit(accounts, transactions, in);
						break;
					case "WITHDRAW":
						withdraw(accounts, in);
						break;
					case "TRANSFER":
						transfer(accounts, transactions, in);
						break;
					case "CLOSEACCOUNT":

						break;
					case "TRANSACTIONREPORT":
						printTransactionReport(accounts, in);
						break;
				}

			} while(numCommandsExecuted <= numCommands);

		numDaysSimulated++;

		}


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

		accounts[MocsBankAccount.getNumAccounts()] = new MocsBankAccount(accountNumber, firstName, lastName, accountNumber);

		System.out.println("    New Account Opened");
		String stringValue = accounts[0].toString();
		stringValue += String.format("\tOpening Balance: %f\n", balance);
		System.out.println(stringValue);
	}



	public static void deposit(MocsBankAccount[] accounts, MocsBankTransaction[] transactions, Scanner in) {
		System.out.println("DEPOSIT:");
		// Checking for accounts in the bank. If there's no accounts, we need to print a separate error message.
		if (MocsBankAccount.getNumAccounts() == 0) {
			System.out.println("    Error: cannot make deposit. There are no open accounts in MocsBank.");
			return;
		}

		int accountNum = in.nextInt();
		double depositAmount = in.nextDouble();
		int accountIndex = binarySearch(accounts, accountNum);
		if (accountIndex == -1) { //Check to make sure account actually exists. If not, print an
			// error.
			System.out.printf("    Error: cannot make deposit. Account # %d was not found in the system.\n",
					accountNum);
			return;
		}

		double newBalance = accounts[accountIndex].getAccountBalance() + depositAmount;
		accounts[accountIndex].setAccountBalance(newBalance);
		MocsBankTransaction deposit = new MocsBankTransaction("Deposit", accountNum, depositAmount,
				accounts[accountIndex].getAccountBalance(), newBalance);
		transactions[MocsBankTransaction.getNumTransactions() + 1] = deposit;

		String stringValue = accounts[MocsBankAccount.getNumAccounts() - 1].toString();
		stringValue += String.format("\tDeposit Amount: %f\n", depositAmount);
		stringValue += String.format("\tNew Balance: %f\n", newBalance);
		System.out.println(stringValue);
	}

	public static void withdraw(MocsBankAccount[] accounts, Scanner in) {
		System.out.println("WITHDRAW:");
		// Checking for accounts in the bank. If there's no accounts, we need to print a separate error message.
		if (MocsBankAccount.getNumAccounts() == 0) {
			System.out.println("    Error: cannot make withdrawal. There are no open accounts in MocsBank.");
			return;
		}

		int accountNum = in.nextInt();
		double withdrawnAmount = in.nextDouble();
		if (binarySearch(accounts, accountNum) == -1) { //Check to make sure account actually exists. If not, print an
			// error.
			System.out.printf("    Error: cannot make withdrawal. Account # %d was not found in the system.\n",
					accountNum);
			return;
		}
		int accountIndex = binarySearch(accounts, accountNum);

		double newBalance = accounts[accountIndex].getAccountBalance() - withdrawnAmount;

		if (newBalance < 0) {
			System.out.println("    Error: Insufficient funds.");
			return;
		}

		MocsBankTransaction withdrawal = new MocsBankTransaction("Withdrawal", accountNum, withdrawnAmount,
				accounts[accountIndex].getAccountBalance(), newBalance);


	}

	public static void transfer(MocsBankAccount[] accounts, MocsBankTransaction[] transactions, Scanner in){

	}

	public static void closeAccount() {
		System.out.println("CLOSEACCOUNT:");
	}

	public static void printTransactionReport(MocsBankAccount[] accounts, Scanner in) {
		System.out.println("MocsBank Transaction Report");

		System.out.printf("Day: ");
	}

	public static void printBalance(MocsBankAccount[] accounts, Scanner in) {
		System.out.println("PRINTBALANCE:");
		int accountNum = in.nextInt();
		if (binarySearch(accounts, accountNum) == -1) {
			System.out.printf("Error: cannot print balance. Account #%i was not found in the system.", accountNum);
			return;
		}
		int index = binarySearch(accounts, accountNum);
		String stringValue = accounts[index].toString();
		stringValue += String.format("Current Balance: %f", accounts[index].getAccountBalance());
		System.out.println(stringValue);
	}

	public static int binarySearch(MocsBankAccount[] accounts, int value) {
		int low = 0;
		int high = MocsBankAccount.getNumAccounts();
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
