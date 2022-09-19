public class User {
    private String firstname;
    private String lastname;
    private int age;
    private String profession;

    public User(String firstname, String lastname, int age, String profession) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.profession = profession;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }
}
