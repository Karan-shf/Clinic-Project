import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;



class Doctor {

    private String FirstName;
    private String LastName;
    private int WorkExperience;
    private String Specialty;
    private int ID;
    private int income_In_Month;
    private int paid_leave;
    private int unpaid_leave;
    private String Password;
    private boolean Fired;
    private boolean has_massage;
    private int rating ;
    private int ratingNum ;
   

    public Doctor(String firstName, String lastName, int workExperience, String specialty, int iD, int income_In_Month,
            int paid_leave, int unpaid_leave, String password, boolean fired, boolean has_massage ,int rating , int ratingNum ) {
        
        FirstName = firstName;
        LastName = lastName;
        WorkExperience = workExperience;
        Specialty = specialty;
        ID = iD;
        this.income_In_Month = income_In_Month;
        this.paid_leave = paid_leave;
        this.unpaid_leave = unpaid_leave;
        Password = password;
        Fired = fired;
        this.has_massage = has_massage;
        this.rating = rating ;
        this.ratingNum = ratingNum;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public int getWorkExperience() {
        return WorkExperience;
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
    
    public int GetRating() {
    	return this.rating;
    }
    
    public int GetRatingNum() {
    	return this.ratingNum;
    }
    
    public void IncreaseRatingNum() {
    	this.ratingNum++;
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
    
    public void Visit() {
    	try {
    		
    		String prescription ;
    		int price ;
    		 
    		System.out.println("Session Started");
    		System.out.println("Your Prescription :  ");
    		
    		Scanner string_input = new Scanner(System.in);
    		
    		prescription = string_input.nextLine();
    		
    		System.out.println("Price :  ");
    		
    		Scanner int_input = new Scanner(System.in);
    		
    		price = int_input.nextInt();
    		
    		Connection connection = Connector.Connect();
    		String sql = "select * from WaitingList where DoctorID = ?";
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, this.ID);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		resultSet.next();
    		int waitID = resultSet.getInt(1);
    		int ID = ThreadLocalRandom.current().nextInt(10000000,99999999);
    		java.util.Date javaDate = new java.util.Date();
            java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

    		Visit visit = new Visit(ID ,mySQLDate ,prescription ,resultSet.getInt(3), this.ID, price ,false );
    		DataBase.InsertVisit(visit);
    		
       		String sql5 = "select PatientID from WaitingList where DoctorID = ? ";
    		preparedStatement = connection.prepareStatement(sql5);
    		preparedStatement.setInt(1, this.ID);
    		ResultSet resultSet3 = preparedStatement.executeQuery();
    		resultSet3.next();
    		int PatientID = resultSet3.getInt(1);
    		
    		
    		String sql2 = "delete from WaitingList where ID = ?";
    		preparedStatement = connection.prepareStatement(sql2);
    		preparedStatement.setInt(1, waitID);
    		preparedStatement.executeUpdate();
    		System.out.println("Session Ended");
    		
 
    		
    		String sql6 = "update patients set HasMassage = 1 where ID = ?";
    		preparedStatement = connection.prepareStatement(sql6);
    		preparedStatement.setInt(1, PatientID );
    		preparedStatement.executeUpdate();
    		
    		
    		String sql3 = "SELECT DoctorID FROM WaitingList WHERE DoctorID = ?";
    		preparedStatement = connection.prepareStatement(sql3);
    		preparedStatement.setInt(1, this.ID);
    		ResultSet resultSet2 = preparedStatement.executeQuery();
    		
    		if(!resultSet2.next()) {
    			String sql4 = "Update Doctors set HasMassage = 0 where ID = ? ";
    			preparedStatement = connection.prepareStatement(sql4);
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
    
    public String toString() {
    	return "Spechialty : " + Specialty +" \n Name : " + FirstName+ " " + LastName + "\n Work Experience : " + WorkExperience  + "\n Rating : " + rating ;
    }
    
    
    public int CalculateRating(int rate) {
    	
    	int rating ;
    	rating = (rate + this.GetRating() * (this.GetRatingNum()-1) ) / ( this.GetRatingNum()  );
    	this.rating = rating;
    	this.IncreaseRatingNum();
    	DataBase.Update("doctors", "RatingNum",this.GetRatingNum(), this.getID());

        String sql = "UPDATE doctors SET Rating=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            preparedStatement.setFloat(1, this.GetRating());
            preparedStatement.setInt(1, this.ID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    	return rating;
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

        int salaryPerCheckin=0; // = Nurse.baseSalary + (WorkExperiance*((0.1)*Nurse.baseSalary))

        // Nurse new_nurse = new Nurse(FirstName, LastName, WorkExperiance, ID, salary, paid_leave, Password, status,salaryPerCheckin);

        // DataBase.InsertNurse(new_nurse);

        String sql = "UPDATE Admins SET HasMassage=1";
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("your entry has been submited");
        System.out.println("please wait for admin confirmation");
       
    }

}
