import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


class Admin {

    private String UserName;
    private String Password;
    private String FirstName;
    private String LastName;

    public Admin(String userName, String password,String firstName,String lastName) {
        UserName = userName;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void Nurse_Entry_Confirmation(){

        ArrayList <Nurse> undecided_Nurses = new ArrayList<Nurse>(); 

        String sql = "SELECT * FROM nurses WHERE status='waiting for confirmation' ";

        try {
            Statement statement = Connector.statement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String Firstname = resultSet.getString(1);
				String Lastname = resultSet.getString(2);
				int WorkExperiance = resultSet.getInt(3);
				int ID = resultSet.getInt(4);
				int Salary = resultSet.getInt(5);
				int PaidLeave = resultSet.getInt(6);
				int UnpaidLeave= resultSet.getInt(7);
				String Password = resultSet.getString(8);
				boolean Fired = resultSet.getBoolean(9);
				Nurse nurse = new Nurse (Firstname, Lastname, WorkExperiance, ID , Salary ,  PaidLeave , UnpaidLeave , Password , Fired);
				undecided_Nurses.add(nurse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner string_input = new Scanner(System.in);

        System.out.println("There are "+undecided_Nurses.size()+" Nurses Waiting to be added to clinic\n");

        for (int i=0;i<undecided_Nurses.size();i++){
            System.out.println("Nurse no."+i+":");
            System.out.println(undecided_Nurses.get(i));
            System.out.println("choose nurse entry:");
            System.out.println("[1].Accept\n[2].Reject");
            boolean check = true;
            while (check) {
                String answer = string_input.next();
                switch (answer) {
                    case "1":
                        undecided_Nurses.get(i).setFired(false); //set status("working")
                        check=false;
                        break;
                    case "2":
                        undecided_Nurses.get(i).setFired(true); //set status("rejected")
                        check=false;
                        break;
                    default:
                        System.out.println("Wrong Input");
                }
            }

            String nurse_sql = "UPDATE nurses set status=? WHERE ID=?";

            try {
                PreparedStatement preparedStatement = Connector.Connect().prepareStatement(nurse_sql);
                // preparedStatement.setString(1, undecided_Nurses.get(i).getFired());
                preparedStatement.setInt(2, undecided_Nurses.get(i).getID());
                preparedStatement.executeUpdate();
                System.out.println("Entry updated succesfully");
            } 
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            if (i != undecided_Nurses.size()-1) {
                System.out.println("---------------------------------------------------------------------");
            }
            
        }

        String sql2 = "UPDATE Admins SET HasMassage=0";
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql2);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //nurse entry confirmation()
    //add nurse()
    //add doc()
    //add personel()
    //add admin()
    //view all visits()
    //view all employees()
    //fire all employees()
    

    
}
