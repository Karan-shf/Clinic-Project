
import java.util.*;
import java.sql.*;

public class Insert {
	
	static ArrayList <Doctor> Doctors = new ArrayList<Doctor>();
	static ArrayList <Nurse> Nurses = new ArrayList<Nurse>();
	static ArrayList <Patient> Patients = new ArrayList<Patient>();
	static ArrayList <Personel> Personel = new ArrayList<Personel>();
	
	static void InsertDoc() {
	
		try {	
		
			Statement statement = Connector.statement();
			String sql = "SELECT * FROM doctors";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String Firstname = resultSet.getString(1);
				String Lastname = resultSet.getString(2);
				int WorkExperience = resultSet.getInt(3);
				String Specialty = resultSet.getString(4);
				int ID = resultSet.getInt(5);
				int IncomeInThisMonth = resultSet.getInt(6);
				int PaidLeave = resultSet.getInt(7);
				int UnpaidLeave= resultSet.getInt(8);
				String Password = resultSet.getString(9);
				boolean Fired = resultSet.getBoolean(10);
				// Boolean hasmassage = resultSet.getBoolean(11);
				Boolean hasmassage = false;
				Doctor doctor = new Doctor (Firstname, Lastname, WorkExperience, Specialty, ID, IncomeInThisMonth, PaidLeave, UnpaidLeave, Password, Fired, hasmassage);
				Doctors.add(doctor);
			}

			Connector.close_connection();
			
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e);
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
				
				Patient patient = new Patient ( Firstname , Lastname , ID , Insurance,Password);
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
			// Boolean hasmassage = resultSet.getBoolean(11);
			has_massage = false;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Doctor(FirstName, LastName, WorkExperiance, Specialty, ID, income_In_Month, paid_leave, unpaid_leave, Password, Fired, has_massage);
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
		System.out.println(primary_key);
		String Firstname = null;
		String Lastname = null;
		int ID = 0;
		String Insurance = null;
		String Password = null;

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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Patient(Firstname, Lastname, ID, Insurance, Password);
	}
}
