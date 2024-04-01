
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

enum Insurance {
    dana,
    talaie,
    parsian,
    others
};

class Patient {

    private String FirstName;
    private String Lastname;
    private int ID;
    private String insurance;
    private String Password;

    public Patient(String firstName, String lastname, int iD, String insurance, String password) {
        FirstName = firstName;
        Lastname = lastname;
        ID = iD;
        this.insurance = insurance;
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastname() {
        return Lastname;
    }

    public int getID() {
        return ID;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getPassword() {
        return Password;
    }
    
    //registery
    // public patient(){
    //     Scanner string_input = new Scanner(System.in);
    //     System.out.println("enter your fistname");
    //     FirstName = string_input.nextLine();
    //     new patient();
        
    // }
    public static void registery(){

        Scanner string_input = new Scanner(System.in);
        Scanner int_input = new Scanner(System.in);

        System.out.println("Enter Your First Name:");
        String FirstName = string_input.nextLine();

        System.out.println("Enter Your Last Name:");
        String Lastname = string_input.nextLine();

        System.out.println("Choose Your Insurance");
        System.out.println("1.Dana");
        System.out.println("2.Talaie");
        System.out.println("3.Parsian");
        System.out.println("4.Others");

        int insuranceNum = int_input.nextInt();

        String insurance = null;
        switch (insuranceNum) {
            case 1:
                insurance = "Dana";
                break;
            case 2:
                insurance = "Talaie";
                break;
            case 3:
                insurance = "Parsian";
                break;
            case 4:
                System.out.println("Enter The Name of Your insurance:");
                insurance = string_input.nextLine();
                break;
            default:
                System.out.println("Wrong Input!");
        }

        System.out.println("Last Step :) \nSet a Password for Yourself:");
        String Password = string_input.nextLine();

        int ID = ThreadLocalRandom.current().nextInt(10000000,99999999);

        // boolean Check = true;
        // while ()

        String sql = "INSERT INTO patients VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);

            preparedStatement.setString(1, FirstName);
            preparedStatement.setString(2, Lastname);
            preparedStatement.setInt(3, ID);
            preparedStatement.setString(4, insurance);
            preparedStatement.setString(5, Password);

            preparedStatement.executeUpdate();

            Connector.close_connection();

            System.out.println("REGISTERY SUCCESFULL");

        } catch (Exception e) {
            e.printStackTrace();
        }

        string_input.close();
        int_input.close();
        

        new Patient(FirstName, Lastname, ID, insurance, Password);

    }

    

    //visit doctor()
    //view_personal_info()
    //edit_personal_info()
    
}
