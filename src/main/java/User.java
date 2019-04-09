
import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;


    private String name;
    private String lastName;
    private int balance;
//    private ArrayList<Object> courses;
    private Map< Integer,respa> courses = new HashMap();

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    private int buy;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

//    public void setCourse(Object courses) {this.courses =courses;}


//    public ArrayList<respa> getCourses(){
//        return courses;
//    }
//    public ArrayList<Object> getCourses(){
//        return courses;
//    }
    public void setCourse(respa respaOBJ) {
        courses.put(respaOBJ.getId(),respaOBJ);
    }

    public User(int id, String name, String lastName, int balance) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.balance = balance;
    }
    public User(int id, String name, String lastName, int balance, respa courses) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.balance = balance;
        setCourse(courses);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
