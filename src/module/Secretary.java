package module;

public class Secretary extends User{

	public Secretary(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	@Override
	public String getDetails() {
		return "Secretary: " + getFirstName() + " " + getLastName();
	}
}
