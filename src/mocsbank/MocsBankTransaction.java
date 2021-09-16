package mocsbank;

public class MocsBankTransaction {

	//region Data Members
	private String transactionType;
	private int accountNumber1;
	private int accountNumber2;
	private double amount;
	private double balanceBefore;
	private double balanceAfter;
	private static int numTransactions;
	//endregion


	//region Constructors
	public MocsBankTransaction(String transactionType, int accountNumber1, int accountNumber2, double amount, double balanceBefore, double balanceAfter) {
		this.transactionType = transactionType;
		this.accountNumber1 = accountNumber1;
		this.accountNumber2 = accountNumber2;
		this.amount = amount;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
		numTransactions++;
	}

	public MocsBankTransaction(String transactionType, int accountNumber1, double amount, double balanceBefore,
	                           double balanceAfter) {
		this.transactionType = transactionType;
		this.accountNumber1 = accountNumber1;
		this.amount = amount;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
		numTransactions++;
	}

	public MocsBankTransaction() {
		numTransactions++;
	}
	//endregion

	//region Getters and Setters
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getAccountNumber1() {
		return accountNumber1;
	}

	public void setAccountNumber1(int accountNumber1) {
		this.accountNumber1 = accountNumber1;
	}

	public int getAccountNumber2() {
		return accountNumber2;
	}

	public void setAccountNumber2(int accountNumber2) {
		this.accountNumber2 = accountNumber2;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalanceBefore() {
		return balanceBefore;
	}

	public void setBalanceBefore(double balanceBefore) {
		this.balanceBefore = balanceBefore;
	}

	public double getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(double balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public static int getNumTransactions() {
		return numTransactions;
	}

//	public static void incrementNumTransactions() {
//		numTransactions++;
//	}
	//endregion
}
