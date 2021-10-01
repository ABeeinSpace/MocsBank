/*
 Aidan Border
 09/10/2021
 CSC 2290
 Honor Code: I will practice academic and personal integrity and excellence of character and expect the same from
 others
 Dis code kinda sux
*/

package mocsbank;

//TODO: COMMENT YA CODE

public class MocsBankAccount {

	//region Data Members
	private int accountNumber;
	private int ID;
	private String firstName;
	private String lastName;
	private FSCmember customer;
	private double accountBalance;
	private static int numAccounts;
	//endregion

	//region Constructors
	// Full constructor that instantiates all data members
	public MocsBankAccount(int accountNumber, String firstName, String lastName, double accountBalance) {
		this.accountNumber = accountNumber;
		this.ID = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountBalance = accountBalance;
		FSCmember customer = new FSCmember(ID, firstName, lastName);
	}

	// default empty constructor
	public MocsBankAccount() {
	}
	//endregion

	//region Getters and Setters
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public FSCmember getCustomer() {
		return customer;
	}

	public void setCustomer(FSCmember customer) {
		this.customer = customer;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public static int getNumAccounts() {
		return numAccounts;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public static void incrementNumAccounts() {
		numAccounts++;
	}
	public static void decrementNumAccounts() {
		numAccounts--;
	}

	@Override
	public String toString() {
		String returnValue = String.format("\tAccount: %d\n", accountNumber);
		returnValue += String.format("\tName: %s %s\n", firstName, lastName);

		return returnValue;
	}

	//endregion
}
