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
    private boolean hasMassage;
    public Patient(String firstName, String lastname, int iD, String insurance, String password , boolean hasMassage) {
        FirstName = firstName;
        Lastname = lastname;
        ID = iD;
        this.insurance = insurance;
        Password = password;
        this.hasMassage = hasMassage;
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
    
    public boolean HasMassage() {
    	return hasMassage;
    }
    public void HasMassage(boolean HasMassage) {
    	this.hasMassage = HasMassage;
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
            preparedStatement.setBoolean(6, false);
            preparedStatement.executeUpdate();

            Connector.close_connection();

            System.out.println("REGISTERY SUCCESFULL");

        } catch (Exception e) {
            e.printStackTrace();
        }

    
        

        new Patient(FirstName, Lastname, ID, insurance, Password , false);

    }

    

    void VisitDoctor() {
    	
    	Insert.InsertDoc();
    	
    	System.out.println("\n Choose Your Doctor");
    	
    	for(int i = 0  ; i < Insert.Doctors.size() ; i++) {
    		
    		System.out.println((i+1) + " ----------------------------------------------");
    		System.out.println(Insert.Doctors.get(i).toString());
    			
    	}
		
		Scanner int_input = new Scanner(System.in);
		int num = int_input.nextInt();
		
		Doctor doctor = Insert.Doctors.get(num-1);
		
		try {
			Connection connection = Connector.Connect();
			String sql = "insert into waitinglist( DoctorID , PatientID) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, doctor.getID() );
			preparedStatement.setInt(2, this.ID);
			
			preparedStatement.executeUpdate();
			
			String sql2 = "Update Doctors set HasMassage = 1 where ID = ? ";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, doctor.getID());
			preparedStatement2.executeUpdate();
			
			System.out.println("You Seccesfully Entered The Waiting List");
			
		}
		catch(Exception e) {
		
			e.printStackTrace();
		}
		
		
    }
    public void View_personal_info() {
    	System.out.println("View Visits");
    	Insert.InsertVisits();
    	for(int i = 0 ; Insert.Visits.size ()>i ; i++) {
    		if(Insert.Visits.get(i).getPatientID()== this.ID && !Insert.Visits.get(i).GetIsRated()) {
    			System.out.println(Insert.Visits.get(i).toString());
    		}	 	
    	}
    	System.out.println("Now TO Rate Enter The Prescription ID");
		Scanner intInput = new Scanner(System.in);
		int num = intInput.nextInt();
		
		for(int i = 0 ; Insert.Visits.size ()>i ; i++) {
    		if(Insert.Visits.get(i).getID()== num ) {
    			
    			Doctor doctor = Insert.Extract_Doctor(Insert.Visits.get(i).getDoctorID());
    			
    			System.out.println("Rate Your Experience Between 1 to 5");
    			
    			num = intInput.nextInt();
    			
    			doctor.CalculateRating(num);
    			
    			Insert.Visits.get(i).SetIsRated(true);
    			// Insert.RenderVisits(Insert.Visits.get(i));
                DataBase.Update("Visits", "IsRated", true, Insert.Visits.get(i).getID());
    			
    			
    			break;
    			
    		}
		}
    
    }
    //edit_personal_info()
    
}
