import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.midi.SysexMessage;


class Patient extends Person {

    
    private String insurance;
    
    private boolean hasMassage;
    public Patient(String firstName, String lastname, int iD, String insurance, String password , boolean hasMassage) {
    	super(firstName, lastname, iD, password);
        
        this.insurance = insurance;
      
        this.hasMassage = hasMassage;
    }

    
    public String getInsurance() {
        return insurance;
    }
    
    public boolean HasMassage() {
    	return hasMassage;
    }
    public void SetHasMassage(boolean HasMassage) {
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

        String sql = "INSERT INTO patients VALUES (?,?,?,?,?,?)";

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
            System.out.println("Your ID: "+ID);
            
            Patient patient = new Patient(FirstName , Lastname , ID , insurance , Password , false );
            
            Dashboard.Patient(patient);

        } catch (Exception e) {
            e.printStackTrace();
        }

    
        

        new Patient(FirstName, Lastname, ID, insurance, Password , false);

    }

    public String toString(){
        return "Name: "+this.getFirstName()+" "+this.getLastName()+"\n"+
            "Insurance:"+this.getInsurance();
    }

    

    void VisitDoctor() {

        if (DataBase.Doctors.size()==0) {
            DataBase.ImportDoctors(true);
        }
    	
    	System.out.println("\n Choose Your Doctor");
    	
    	for(int i = 0  ; i < DataBase.Doctors.size() ; i++) {
    		
    		System.out.println((i+1) + " ----------------------------------------------");
    		System.out.println(DataBase.Doctors.get(i).toString());
    			
    	}
		
		Scanner int_input = new Scanner(System.in);
        Scanner string_input = new Scanner(System.in);
		int num = int_input.nextInt();
		
		Doctor doctor = DataBase.Doctors.get(num-1);

        System.out.println("Details: ");

        String details = string_input.nextLine();
		
		try {
			Connection connection = Connector.Connect();

            String sql3 = "SELECT * FROM waitinglist WHERE DoctorID=?";
            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
            preparedStatement3.setInt(1, doctor.getID());
            ResultSet resultSet = preparedStatement3.executeQuery();
            int waitinglist_num = 0;
            while (resultSet.next()) {
                waitinglist_num++;
            }
            
			String sql = "insert into waitinglist(DoctorID,PatientID,Details) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, doctor.getID() );
			preparedStatement.setInt(2, this.getID());
            preparedStatement.setString(3, details);
			
			preparedStatement.executeUpdate();
			
			String sql2 = "Update Doctors set HasMassage = 1 where ID = ? ";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, doctor.getID());
			preparedStatement2.executeUpdate();
			
			System.out.println("You Seccesfully Entered The Waiting List");
            
            System.out.println("There Are "+waitinglist_num+" Patients in this doctor's waiting list");

			connection.close();
		}
		catch(Exception e) {
		
			e.printStackTrace();
		}
		
		
    }
    public void RateVisit() {
    	
    	// DataBase.ImportVisits();
        // System.out.println(DataBase.Visits.size());

        ArrayList <Visit> PatientVisits = new ArrayList<Visit>();

        String sql = "SELECT * FROM Visits WHERE PatientID=? AND IsRated=0"; // this.getid()
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            preparedStatement.setInt(1, this.getID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt(1);
                Date Date = resultSet.getDate(2);
                String Prescription = resultSet.getString(3);
                int PatientID = resultSet.getInt(4);
                int DoctorID = resultSet.getInt(5);
                int Price= resultSet.getInt(6);
                boolean IsRated = resultSet.getBoolean(7);
                int NurseID = resultSet.getInt(8);
                Visit visit = new Visit(ID , Date , Prescription , PatientID , DoctorID , Price , IsRated , NurseID);
                PatientVisits.add(visit);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Connector.close_connection();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }

        if (PatientVisits.size()==0) {
            System.out.println("You Have No Unratted Visits");
        } else {

            System.out.println("View Visits:");

            for (int i=0;i<PatientVisits.size();i++){
                PatientVisits.get(i).ShowVisit();
                if (i != PatientVisits.size()-1) {
                    System.out.println("---------------------------------------");
                }
            }
    
    
            // for(int i = 0 ; i<DataBase.Visits.size() ; i++) {
    
            // 	if(DataBase.Visits.get(i).getPatientID()== this.getID() && !DataBase.Visits.get(i).GetIsRated()) {
            //         DataBase.Visits.get(i).ShowVisit();
            //         if (i != DataBase.Visits.size()-1) {
            //             System.out.println("---------------------------------------");
            //         }
            // 	}
            // }
    
            System.out.println("Enter The Prescription ID To Rate:");

            Scanner intInput = new Scanner(System.in);
    
            boolean Check = true;
    
            while (Check) {
                
                int num = intInput.nextInt();
        
                for (int i=0;i<PatientVisits.size();i++){
                    
                    if (PatientVisits.get(i).getID()==num) {
        
                        Doctor doctor = DataBase.Extract_Doctor(PatientVisits.get(i).getDoctorID());
                        
                        System.out.println("Rate Your Experience Between 1 to 5");
        
                        num = intInput.nextInt();
        
                        while (num>5 || num<1) {num = intInput.nextInt();}
                        
                        doctor.CalculateRating(num);
                        
                        PatientVisits.get(i).SetIsRated(true);
        
                        DataBase.Update("visits", "IsRated ", true, PatientVisits.get(i).getID());
                        
                        Check = false;
    
                        break;
                    }
        
                }
    
                if (Check) {
                    System.out.println("ID Not Found :(");
                    System.out.println("Try Again");
                }
    
            }
            
        }


		
		// for(int i = 0 ; DataBase.Visits.size ()>i ; i++) {

    	// 	if(DataBase.Visits.get(i).getID()== num ) {
    			
    	// 		Doctor doctor = DataBase.Extract_Doctor(DataBase.Visits.get(i).getDoctorID());
    			
    	// 		System.out.println("Rate Your Experience Between 1 to 5");
    			
    	// 		num = intInput.nextInt();
    			
    	// 		doctor.CalculateRating(num);
    			
    	// 		DataBase.Visits.get(i).SetIsRated(true);

    	// 		DataBase.Update("visits", "IsRated ", true, DataBase.Visits.get(i).getID());
    			
    	// 		break;
    			
    	// 	}
		// }
    
    }
   

    
    @Override
    public void Edit_personal_info(String table) {
		
		System.out.println("Choose Which Field You Are Willing to Edit ");
		System.out.println(" 1 ) First Name \t 2 ) Last Name \n 3 ) Password \n 4 ) Change Insurance ");
		
		boolean accepted = false ;
		Scanner int_input = new Scanner(System.in);
		Scanner String_input = new Scanner(System.in);
		
		while(!accepted) {
			int num = int_input.nextInt();
			switch(num) {
			
			case 1 :
				String newFirstName = String_input.nextLine();
				this.setFirstName(newFirstName);
				
				DataBase.Update(table , "Firstname" , newFirstName , this.getID() );
				System.out.println("Firstname Was Changed Seccuessfully");
				accepted = true ;
				break ;
			
			case 2 :
				String newLastName = String_input.nextLine();
				this.setLastName(newLastName);
				
				DataBase.Update(table , "Lastname" , newLastName , this.getID() );
				System.out.println("Lastname Was Changed Seccuessfully");
				accepted = true ;
				break ;
			
			case 3 :
				
				System.out.println("Enter You Old Password : ");
				String password = String_input.nextLine();
				String sql = "SELECT Password FROM " + table + " WHERE ID = ?";
				try {
					PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
					preparedStatement.setInt(1, this.getID());
					ResultSet resultSet = preparedStatement.executeQuery();
					resultSet.next();
					boolean accepted2 = false ;
					while(!accepted2){
						if(password.equals(resultSet.getString(1))) {
							System.out.println("Now Enter the New Password");
							String newPassword = String_input.nextLine();
							System.out.println("Now Enter the New Password Again");
							String newPasswordComfirm = String_input.nextLine();
							if(newPassword.equals(newPasswordComfirm)) {
								this.setPassword(newPassword);
								DataBase.Update(table , "Password" , newPassword , this.getID() );
								accepted2 = true ;
								System.out.println("Password Was Changed Seccuessfully");
							}
							else {
								System.out.println("MissMatch Password Comfirmation \n Try Again");
							}
							
						}
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				accepted = true ;
				break ;
				
			case 4 :
					Scanner string_input =  new Scanner(System.in);
				    System.out.println("Choose Your Insurance");
			        System.out.println("1.Dana");
			        System.out.println("2.Talaie");
			        System.out.println("3.Parsian");
			        System.out.println("4.Others");

			        int insuranceNum = int_input.nextInt();

			        String newInsurance = null;
			        switch (insuranceNum) {
			            case 1:
			            	newInsurance = "Dana";
			                break;
			            case 2:
			            	newInsurance = "Talaie";
			                break;
			            case 3:
			            	newInsurance = "Parsian";
			                break;
			            case 4:
			                System.out.println("Enter The Name of Your insurance:");
			                newInsurance = string_input.nextLine();
			                break;
			            default:
			                System.out.println("Wrong Input!");
			        }
			      
				this.insurance = newInsurance ;
				
				DataBase.Update(table , "Insurance" , newInsurance , this.getID() );
				System.out.println("Insurance Was Changed Seccuessfully");
				accepted = true ;
				break ;
				
			default :
				System.out.println("Wrong Input");
			}
		}
			
	}

    @Override
    public void View_personal_info() {

        Scanner int_input = new Scanner(System.in);

        System.out.println("Personal Info:");
        System.out.println("Name: "+this.getFirstName()+" "+this.getLastName());
        System.out.println("ID: "+this.getID());
        System.out.println("Password: "+this.getPassword());
        System.out.println("Insurance: "+this.getInsurance());

        System.out.println("Enter [1] to View Your Visits History");
        System.out.println("Enter [2] to Go back");
        boolean Check = true;

        while (Check) {
            int answer = int_input.nextInt();

            switch (answer) {
                case 1:
                    //visit
                    System.out.println("\nYour Visit History:");
                    DataBase.ImportVisits();
                    for (int i=0;i<DataBase.Visits.size();i++){
                        
                        if (DataBase.Visits.get(i).getPatientID()==this.getID()) {
                            DataBase.Visits.get(i).ShowVisit();
                        }
                        if (i != DataBase.Visits.size()-1) {
                            System.out.println("------------------------------------");
                        }
                    }
                    Check = false;
                    break;
                case 2:
                    Check = false;
                    break;
                default:
                    System.out.println("wrong input!");
            }
        }

    }
    
}
