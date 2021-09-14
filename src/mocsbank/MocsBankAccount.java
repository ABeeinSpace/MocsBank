package mocsbank;

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
	public MocsBankAccount(int accountNumber, int ID, String firstName, String lastName, double accountBalance) {
		this.accountNumber = accountNumber;
		this.ID = ID;
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

	public static void incrementNumAccounts() {
		numAccounts++;
	}
	public static void decrementNumAccounts() {
		numAccounts--;
	}
	//endregion
}
