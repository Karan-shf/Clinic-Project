import java.util.*;
import java.sql.*;
import java.sql.Date;

public class Insert {
	
	static ArrayList <Doctor> Doctors = new ArrayList<Doctor>();
	static ArrayList <Nurse> Nurses = new ArrayList<Nurse>();
	static ArrayList <Patient> Patients = new ArrayList<Patient>();
	static ArrayList <Personel> Personel = new ArrayList<Personel>();
	static ArrayList <Visit> Visits = new ArrayList<Visit>();
	
	static void InsertDoc() {
	
		String FirstName=null;
		String LastName=null;
		int WorkExperiance=0;
		String Specialty=null;
		int ID=0;
		int income_In_Month=0;
		int paid_leave=0;
		int unpaid_leave=0;
		String Password=null;
		boolean Fired=false;
		boolean has_massage=false;
		int ratingNum = 1 ;
		int rating = 0;

		String sql = "SELECT * FROM doctors";
		
		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				FirstName = resultSet.getString(1);
				LastName = resultSet.getString(2);
				WorkExperiance = resultSet.getInt(3);
				Specialty = resultSet.getString(4);
				ID = resultSet.getInt(5);
				income_In_Month = resultSet.getInt(6);
				paid_leave = resultSet.getInt(7);
				unpaid_leave= resultSet.getInt(8);
				Password = resultSet.getString(9);
				Fired = resultSet.getBoolean(10);
				has_massage = resultSet.getBoolean(11);
				ratingNum = resultSet.getInt(12);
				rating = resultSet.getInt(13);
				
				 Doctor doctor = new Doctor(FirstName, LastName, WorkExperiance, Specialty, ID, income_In_Month, paid_leave, unpaid_leave,
						 Password, Fired, has_massage , rating , ratingNum);
				Doctors.add(doctor);
			}
				
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	static void InsertNurses() {

		try {
			
			Statement statement = Connector.statement();
			String sql = "SELECT * FROM nurses";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
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
				Nurses.add(nurse);
			}
			
			Connector.close_connection();
		
		}

		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e);
		}
		
		
	
		
	}
	
	static void InsertPatients() {

		try {	
			
			Statement statement = Connector.statement();
			String sql = "SELECT * FROM patients";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				String Firstname = rs.getString(1);
				String Lastname = rs.getString(2);
				int ID = rs.getInt(3);
				String Insurance = rs.getString(4);
				String Password = rs.getString(5);
				boolean  HasMassage = rs.getBoolean(6) ;
				Patient patient = new Patient ( Firstname , Lastname , ID , Insurance,Password ,HasMassage );
				Patients.add(patient);
			}
			
			Connector.close_connection();
		
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e);
		}
		
	}
	
	static void InsertPersonel() {

		try {	
			
			Statement statement = Connector.statement();
			String sql = "SELECT * FROM personel";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String Firstname = resultSet.getString(1);
				String Lastname = resultSet.getString(2);
				int ID = resultSet.getInt(3);
				int Salary = resultSet.getInt(4);
				int PaidLeave = resultSet.getInt(5);
				int UnpaidLeave= resultSet.getInt(6);
				String Password = resultSet.getString(7);
				boolean Fired = resultSet.getBoolean(8);
				Personel personel = new Personel ( Firstname , Lastname , ID , Salary ,  PaidLeave , UnpaidLeave , Password , Fired);
				Personel.add(personel);
			}
			
			Connector.close_connection();
		
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e);
		}
	}
	
	
	static void InsertVisits() {
		
		try {
			
		
			Statement statement = Connector.statement();
			String sql = "SELECT * FROM visits";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
				int ID = resultSet.getInt(1);
				Date Date = resultSet.getDate(2);
				String Prescription = resultSet.getString(3);
				int PatientID = resultSet.getInt(4);
				int DoctorID = resultSet.getInt(5);
				int Price= resultSet.getInt(6);
				boolean IsRated = resultSet.getBoolean(7);
				Visit visit = new Visit(ID , Date , Prescription , PatientID , DoctorID , Price , IsRated);
				Visits.add(visit);
				
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

	static Admin Extract_Admin(String primary_key){

		String Username=null;
		String Password=null;
		String Firstname=null;
		String Lastname=null;

		String sql = "SELECT * FROM admins WHERE Username=?";

		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setString(1, primary_key);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Username = resultSet.getString(1);
			Password = resultSet.getString(2);
			Firstname = resultSet.getString(3);
			Lastname = resultSet.getString(4);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Admin(Username, Password, Firstname, Lastname);
	}

	static Doctor Extract_Doctor(int primary_key){
		String FirstName=null;
		String LastName=null;
		int WorkExperiance=0;
		String Specialty=null;
		int ID=0;
		int income_In_Month=0;
		int paid_leave=0;
		int unpaid_leave=0;
		String Password=null;
		boolean Fired=false;
		boolean has_massage=false;
		int ratingNum = 1 ;
		int rating = 0;

		String sql = "SELECT * FROM doctors WHERE ID=?";
		
		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, primary_key);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			FirstName = resultSet.getString(1);
			LastName = resultSet.getString(2);
			WorkExperiance = resultSet.getInt(3);
			Specialty = resultSet.getString(4);
			ID = resultSet.getInt(5);
			income_In_Month = resultSet.getInt(6);
			paid_leave = resultSet.getInt(7);
			unpaid_leave= resultSet.getInt(8);
			Password = resultSet.getString(9);
			Fired = resultSet.getBoolean(10);
			has_massage = resultSet.getBoolean(11);
			ratingNum = resultSet.getInt(12);
			rating = resultSet.getInt(13);
			
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Doctor(FirstName, LastName, WorkExperiance, Specialty, ID, income_In_Month, paid_leave, unpaid_leave, Password, Fired, has_massage , rating , ratingNum);
	}

	static Nurse Extract_Nurse(int primary_key){
		String FirstName=null;
		String LastName=null;
		int WorkExperiance=0;
		int ID=0;
		int salary=0;
		int paid_leave=0;
		int unpaid_leave=0;
		String Password=null;
		boolean Fired=false;

		String sql = "SELECT * FROM nurses WHERE ID=?";
		
		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, primary_key);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			FirstName = resultSet.getString(1);
			LastName = resultSet.getString(2);
			WorkExperiance = resultSet.getInt(3);
			ID = resultSet.getInt(4);
			salary = resultSet.getInt(5);
			paid_leave = resultSet.getInt(6);
			unpaid_leave= resultSet.getInt(7);
			Password = resultSet.getString(8);
			Fired = resultSet.getBoolean(9);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Nurse(FirstName, LastName, WorkExperiance, ID, salary, paid_leave, unpaid_leave, Password, Fired);
	}

	static Personel Extract_Personel(int primary_key){
		String Firstname = null;
		String Lastname = null;
		int ID = 0;
		int Salary = 0;
		int PaidLeave = 0;
		int UnpaidLeave= 0;
		String Password = null;
		boolean Fired = false;

		String sql = "SELECT * FROM personel WHERE ID=?";

		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, primary_key);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Firstname = resultSet.getString(1);
			Lastname = resultSet.getString(2);
			ID = resultSet.getInt(3);
			Salary = resultSet.getInt(4);
			PaidLeave = resultSet.getInt(5);
			UnpaidLeave= resultSet.getInt(6);
			Password = resultSet.getString(7);
			Fired = resultSet.getBoolean(8);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Personel(Firstname, Lastname, ID, Salary, PaidLeave, UnpaidLeave, Password, Fired);
	}

	static Patient Extract_Patient(int primary_key){
		
		String Firstname = null;
		String Lastname = null;
		int ID = 0;
		String Insurance = null;
		String Password = null;
		boolean HasMassage = false ;
		String sql = "SELECT * FROM patients WHERE ID=?";

		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, primary_key);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Firstname = resultSet.getString(1);
			Lastname = resultSet.getString(2);
			ID = resultSet.getInt(3);
			Insurance = resultSet.getString(4);
			Password = resultSet.getString(5);
			HasMassage = resultSet.getBoolean(6);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Patient(Firstname, Lastname, ID, Insurance, Password ,HasMassage);
	}
	
	
	// static void RenderDoctor( Doctor doctor ) {
		
	// 	try {
	// 		String sql = "update doctors set FirstName = ? ,LastName = ? ,WorkExperience = ? , "
	// 				+ "Specialty = ? , ID = ? ,IncomeInThisMonth = ? , Paidleave = ?,UnPaidleave = ?,Password = ? ,"
	// 				+ "Fired = ? ,HasMassage = ? ,RatingNum = ? ,Rating = ? where ID = ?  ";
	// 		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			
	// 		preparedStatement.setInt(14, doctor.getID());
		
	// 		preparedStatement.setString(1, doctor.getFirstName());
	// 		preparedStatement.setString(2,doctor.getLastName() );
	// 		preparedStatement.setInt(3, doctor.getWorkExperience() );
	// 		preparedStatement.setString(4,doctor.getSpecialty() );
	// 		preparedStatement.setInt(5, doctor.getID());
	// 		preparedStatement.setInt(6,doctor.getIncome_In_Month() );
	// 		preparedStatement.setInt(7,doctor.getPaid_leave() );
	// 		preparedStatement.setInt(8,doctor.getUnpaid_leave() );
	// 		preparedStatement.setString(9,doctor.getPassword() );
	// 		preparedStatement.setBoolean(10, doctor.isFired() );
	// 		preparedStatement.setBoolean(11,doctor.Has_massage() );
	// 		preparedStatement.setInt(12,doctor.GetRatingNum() );
	// 		preparedStatement.setInt(13,doctor.GetRating() );
	
	// 	    preparedStatement.executeUpdate();
		
	// 	}
	// 	catch(Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }
	
	// static void RenderPatient(Patient patient) {
	// 	try {
	// 		String sql = "update patients set FirstName = ? ,LastName = ? , "
	// 				+ " ID = ?,Insurance = ? ,Password = ? "
	// 				+ ",HasMassage = ?  where ID = ?  ";
	// 		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			
	// 		preparedStatement.setInt(7, patient.getID());
			
	// 		preparedStatement.setString(1, patient.getFirstName());
	// 		preparedStatement.setString(2,patient.getLastname() );
	// 		preparedStatement.setInt(3, patient.getID());
	// 		preparedStatement.setString(4,patient.getInsurance() );
	// 		preparedStatement.setString(5,patient.getPassword() );
	// 		preparedStatement.setBoolean(6,patient.HasMassage() );
			
	
	// 	    preparedStatement.executeUpdate();
		
	// 	}
	// 	catch(Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }
	
	// static void RenderVisits(Visit visit) {
	// 	try {
	// 		String sql = "UPDATE visits SET ID = ? ,Date = ? , "
	// 				+ " Prescription = ?,PatientID = ? ,DoctorID = ? "
	// 				+ ",Price = ? ,IsRated = ?  where ID = ?  ";
	// 		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			
	// 		preparedStatement.setInt(8, visit.getID());
			
	// 		preparedStatement.setInt(1, visit.getID());
	// 		preparedStatement.setDate(2,visit.getDate() );
	// 		preparedStatement.setString(3, visit.getPrescription());
	// 		preparedStatement.setInt(4,visit.getPatientID() );
	// 		preparedStatement.setInt(5,visit.getDoctorID() );
	// 		preparedStatement.setInt(6,visit.getPrice() );
	// 		preparedStatement.setBoolean(7,visit.GetIsRated() );

	// 	    preparedStatement.executeUpdate();
	
	// 	}
	// 	catch(Exception e) {
	// 	e.printStackTrace();
	// 	}
	// }

}
