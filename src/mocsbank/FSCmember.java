/*
 Aidan Border
 09/10/2021
 CSC 2290
 Honor Code: I will practice academic and personal integrity and excellence of character and expect the same from
 others
*/

package mocsbank;

//TODO: COMMENT YA CODE

@SuppressWarnings("SpellCheckingInspection")
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
