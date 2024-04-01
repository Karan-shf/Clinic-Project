import java.sql.PreparedStatement;
import java.sql.SQLException;

class Doctor {

    private String FirstName;
    private String LastName;
    private int WorkExperiance;
    private String Specialty;
    private int ID;
    private int income_In_Month;
    private int paid_leave;
    private int unpaid_leave;
    private String Password;
    private boolean Fired;
    private boolean has_massage;

    public Doctor(String firstName, String lastName, int workExperiance, String specialty, int iD, int income_In_Month,
            int paid_leave, int unpaid_leave, String password, boolean fired, boolean has_massage) {
        
        FirstName = firstName;
        LastName = lastName;
        WorkExperiance = workExperiance;
        Specialty = specialty;
        ID = iD;
        this.income_In_Month = income_In_Month;
        this.paid_leave = paid_leave;
        this.unpaid_leave = unpaid_leave;
        Password = password;
        Fired = fired;
        this.has_massage = has_massage;
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

    public String getSpecialty() {
        return Specialty;
    }

    public int getID() {
        return ID;
    }

    public int getIncome_In_Month() {
        return income_In_Month;
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

    public boolean isFired() {
        return Fired;
    }
    public void setFired(boolean fired) {
        Fired = fired;

        String sql = "UPDATE doctors set Fired=1 WHERE ID=?";
        int doctor_ID=this.ID;
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            preparedStatement.setInt(1,doctor_ID);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean Has_massage() {
        return has_massage;
    }
    

}
