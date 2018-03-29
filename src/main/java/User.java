public class User {
    private int id;
    private String name;
    private String lastName;
    private int balance;

    public User(int id, String name, String lastName, int balance) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
