import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;



class Doctor extends Person {


    private int WorkExperience;
    private String Specialty;
    private int income_In_Month;
    private String status;
    private boolean has_massage;
    private float rating ;
    private int ratingNum ;
    private int VisitPrice ;
		
	

    public Doctor(String firstName, String lastName, int workExperience, String specialty, int ID, int income_In_Month,
    		 String password, String status , boolean has_massage ,float rating , int ratingNum ) {
        
    	super(firstName, lastName, ID, password);
    	
        WorkExperience = workExperience;
        Specialty = specialty;
        this.income_In_Month = income_In_Month;
        this.status = status ;
        this.has_massage = has_massage;
        this.rating = rating ;
        this.ratingNum = ratingNum;
    }

   
    public int getWorkExperience() {
        return WorkExperience;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public int getIncome_In_Month() {
        return income_In_Month;
    }

    public String getStatus() {
        return status;
    }
    
    public float GetRating() {
    	return this.rating;
    }
    
    public int GetRatingNum() {
    	return this.ratingNum;
    }
    
    public void IncreaseRatingNum() {
    	this.ratingNum++;
    }
    
    public void setStatus(String status) {
       this.status = status;

        String sql = "UPDATE doctors set Fired=? WHERE ID=?";
        
        int doctor_ID= this.getID();
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            preparedStatement.setInt(1,doctor_ID);
            preparedStatement.setString(2, status);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
			try {
				Connector.close_connection();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
    }

    public boolean Has_massage() {
        return has_massage;
    }
    
    public void  ViewPreviousePrescriptions() {
    	// extract prescriptions with same doctor id as ours and call the to string 
    	
    }
    
    public void TakeLeave() {
    	
    }
    
    public void Visit() {
    	try {

			String sql = "select * from waitinglist where DoctorID = ?";
    		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
    		preparedStatement.setInt(1, this.getID());
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		resultSet.next();
    		int waitID = resultSet.getInt(1);
			int PatientID = resultSet.getInt(3);
			String details = resultSet.getString(4);
    		int ID = ThreadLocalRandom.current().nextInt(10000000,99999999);
    		java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

			Patient patient = DataBase.Extract_Patient(PatientID);
    		
    		Scanner string_input = new Scanner(System.in);
    		
    		String prescription="";
			String prescriptionLine;
    		int price ;
    		 
    		System.out.println("Session Started");

			System.out.println("patient info:");
			System.out.println(patient);
			System.out.println("Details: "+details);

    		System.out.println("Your Prescription (Enter [0] to end writting):");
			boolean check = true;

			while (check) {

				prescriptionLine = string_input.nextLine();

				if (prescriptionLine.equals("0")) {
				    if (prescription.equals("")) {
						prescription = "-";
					} else {
						prescription = prescription.substring(0, prescription.length()-1);
					}
					check = false;
					
				} else {
					prescription += prescriptionLine+",";
				}
				
			}
    		
    		System.out.println("does your patient need a nurse ? ");
    		System.out.println("1 ) Yes \t 2 ) No ");
    		
    		Scanner int_input = new Scanner(System.in);
    		
    		int num = int_input.nextInt();
    		int nurseID = 0 ;
    		
    		switch(num) {
    		case 1 :
    			System.out.println("Choose Your Nurse");
    			
    			DataBase.ImportNurses(true);
    			for(int i = 0 ; i < DataBase.Nurses.size() ; i++) {
    				System.out.println((i+1) + " " + DataBase.Nurses.get(i).toString());
    			}
    			
    			int num2 = int_input.nextInt();
    			
    			nurseID = DataBase.Nurses.get(num2-1).getID();
    			break;
    		case 2 :
    			break ;
    		default :
    			break ;
    			
    		}
    		
    		
    		System.out.println("Price :  ");
    		
    		price = int_input.nextInt();
    		
    		this.VisitPrice = price ;
    		this.AddSalary();
    		
    		// Connection connection = Connector.Connect();
    		

    		Visit visit = new Visit(ID ,mySQLDate ,prescription ,resultSet.getInt(3), this.getID(), price ,false , nurseID  );
    		DataBase.InsertVisit(visit);
    		
       		// String sql5 = "select PatientID from waitinglist where DoctorID = ? ";
    		// preparedStatement = connection.prepareStatement(sql5);
    		// preparedStatement.setInt(1, this.getID());
    		// ResultSet resultSet3 = preparedStatement.executeQuery();
    		// resultSet3.next();
    		// int PatientID = resultSet3.getInt(1);
    		
    		
    		// String sql2 = "delete from waitinglist where ID = ?";
    		// preparedStatement = connection.prepareStatement(sql2);
    		// preparedStatement.setInt(1, waitID);
    		// preparedStatement.executeUpdate();

			DataBase.Delete("waitinglist", waitID);

    		System.out.println("Session Ended");
    		
 
    		
    		// String sql6 = "update patients set HasMassage = 1 where ID = ?";
    		// preparedStatement = connection.prepareStatement(sql6);
    		// preparedStatement.setInt(1, PatientID );
    		// preparedStatement.executeUpdate();
    		
    		
    		// String sql3 = "SELECT DoctorID FROM waitinglist WHERE DoctorID = ?";
    		// preparedStatement = Connector.Connect().prepareStatement(sql3);
    		// preparedStatement.setInt(1, this.getID());
    		// ResultSet resultSet2 = preparedStatement.executeQuery();
    		
    		if(!resultSet.next()) {
				this.has_massage = false;
    			String sql4 = "Update Doctors set HasMassage = 0 where ID = ? ";
    			preparedStatement = Connector.Connect().prepareStatement(sql4);
    			preparedStatement.setInt(1, getID());
    			preparedStatement.executeUpdate();
    			
    			
    			
    			
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	finally {
			try {
				Connector.close_connection();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
    }
    
    Calendar cal = Calendar.getInstance();
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    
    public void AddSalary() {
    	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    	if(dayOfMonth == 1) {
    		this.income_In_Month = this.VisitPrice;
    	}
    	else {
    		this.income_In_Month += this.VisitPrice;
    	}
    	
    	try {
    		Statement statement = Connector.statement();
    		String sql = "UPDATE doctors SET IncomeInThisMonth =? WHERE ID = ? ";
    		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
    		preparedStatement.setInt(1, this.income_In_Month);
    		preparedStatement.setInt(2, this.getID());
    		preparedStatement.executeUpdate();
    		
    		
    		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    
    public String toString() {
    	return "Spechialty : " + Specialty +" \n Name : " + getFirstName()+ " " + getLastName() + "\n Work Experience : " + WorkExperience  + "\n Rating : " + rating + " ("+(ratingNum-1)+")" ;
    }
    
    
    public float CalculateRating(int rate) {
    	
    	float rating ;
    	rating = (rate + this.GetRating() * (this.GetRatingNum()-1) ) / ( this.GetRatingNum()  );
    	this.rating = rating;
    	this.IncreaseRatingNum();
    	DataBase.Update("doctors", "RatingNum",this.GetRatingNum(), this.getID());

    	String sql = "UPDATE doctors SET Rating=? WHERE ID=?";
    	try {
    		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
    		preparedStatement.setFloat(1, this.GetRating());
    		preparedStatement.setInt(2, this.getID());
    		preparedStatement.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return rating;
    }
    
    public void ViewAndEditPersonalInfo() {
    
    	this.toString();
    	//also add income 
    	System.out.println("Choose The Field You Want to Edit");
    	
    	
    }
    
    public void AddNurse(){
        Scanner string_input = new Scanner(System.in);
        Scanner int_input = new Scanner(System.in);

        String FirstName;
        String LastName;
        int WorkExperiance;
        int ID = ThreadLocalRandom.current().nextInt(10000000,99999999);
        int salary = 0; 
        int paid_leave = 7;
        String Password = "12345678";
        String status = "waiting for confirmation";
        java.util.Date javDate = new java.util.Date();
        Date registerDate = new Date(javDate.getTime());
        

        System.out.println("please fill in the required fields");

        System.out.print("first name: ");
        FirstName = string_input.nextLine();

        System.out.print("last name: ");
        LastName = string_input.nextLine();

        System.out.println("Work Experience: ");
        WorkExperiance = int_input.nextInt();
        
        int salaryPerCheckin = Nurse.SalaryPerCheckin(WorkExperiance);
        Nurse new_nurse = new Nurse(FirstName, LastName, WorkExperiance, ID, salary, paid_leave, Password, status,salaryPerCheckin , registerDate );

        DataBase.InsertNurse(new_nurse);

        String sql2 = "UPDATE Admins SET HasMassage=1";
        try {
            PreparedStatement preparedStatement2 = Connector.Connect().prepareStatement(sql2);
            preparedStatement2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("your entry has been submited");
        System.out.println("please wait for admin confirmation");
       
    }


	@Override
	public void View_personal_info() {
		
		System.out.println("ID  : " + this.getID() );
		System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
		System.out.println("Specialty : " + this.getSpecialty());
		System.out.println("Income In Current Month  : " + this.getIncome_In_Month());
		System.out.println("Work Experience : " + this.getWorkExperience());
		System.out.println("Rating : " + this.GetRating() + "(" + (this.GetRatingNum()-1) + ")");
		
	}


}
