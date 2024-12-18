package backend.entity;

public class UserCustomData {
    private String name;
    private String email;
    private customcar custom;
    private testdriveEntity test;
    // Constructor, getters, and setters
    public UserCustomData(String name, String email, customcar custom, testdriveEntity test) {
        this.name = name;
        this.email = email;
        this.custom = custom;
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public customcar getCustom() {
        return custom;
    }

	public testdriveEntity getTest() {
		return test;
	}
    
    
}
