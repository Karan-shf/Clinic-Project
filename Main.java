// import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        // Patient.registery();

        // Insert.InsertPatients();
        // Insert.InsertDoc();
        // Login.SignInPat(Insert.Patients);
        // Login.SignInDoc(Insert.Doctors);

        // String sql = "SELECT Lastname FROM patients WHERE Firstname='mmd' ";
        // Statement statement = Connector.statement();
        // PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
        // preparedStatement.executeUpdate();
        // ResultSet resultSet = preparedStatement.executeQuery(sql);
        // resultSet.next();
        // System.out.println(resultSet.getString(1));
           
        // String sql = "UPDATE personel set Firstname='ahmad' WHERE Firstname =?";
        // String name = "hossein";
        // PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
        // preparedStatement.setString(1,name);
        // preparedStatement.executeUpdate();
        // System.out.println("ok!");
        
        

        Clinic.Welcome();
        Clinic.ShowMenu();




    }
}
