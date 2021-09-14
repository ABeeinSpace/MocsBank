package mocsbank;

public class FSCmember {

	//region Data Members
	private int ID;
	private String firstName;
	private String lastName;
	//endregion

	//region Constructors
	public FSCmember(int ID, String firstName, String lastName) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// Default empty constructor
	public FSCmember() {

	}
	//endregion

	//region Getters and Setters
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	//endregion


}
