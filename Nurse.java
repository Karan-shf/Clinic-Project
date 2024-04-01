import java.sql.PreparedStatement;
import java.sql.SQLException;

class Nurse {
    
    private String FirstName;
    private String LastName;
    private int WorkExperiance;
    private int ID;
    private int salary;
    private int paid_leave;
    private int unpaid_leave;
    private String Password;
    private boolean Fired;

    public Nurse(String firstName, String lastName, int workExperiance, int iD, int salary, int paid_leave,
    int unpaid_leave, String password, boolean fired) {

        FirstName = firstName;
        LastName = lastName;
        WorkExperiance = workExperiance;
        ID = iD;
        this.salary = salary;
        this.paid_leave = paid_leave;
        this.unpaid_leave = unpaid_leave;
        Password = password;
        Fired = fired;

    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public int getWorkExperiance() {
        return WorkExperiance;
    }
    public int getID() {
        return ID;
    }
    public int getSalary() {
        return salary;
    }
    public int getPaid_leave() {
        return paid_leave;
    }
    public int getUnpaid_leave() {
        return unpaid_leave;
    }
    public String getPassword() {
        return Password;
    }
    public boolean getFired(){
        return Fired;
    }
    public void setFired(boolean fired) {
        Fired = fired;

        String sql = "UPDATE nurses set Fired=1 WHERE ID=?";
        int nurse_ID=this.ID;
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            preparedStatement.setInt(1,nurse_ID);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
}
