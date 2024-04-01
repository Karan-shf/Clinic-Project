import java.sql.*;
import java.util.ArrayList;

public abstract class DataBase implements IDataBase {
	
	static void InsertVisit(Visit visit) {
		try {

			String sql = " insert into visits values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
		
			preparedStatement.setInt(1, visit.getID());
			preparedStatement.setDate(2, visit.getDate());
			preparedStatement.setString(3, visit.getPrescription());
			preparedStatement.setInt(4, visit.getPatientID());
			preparedStatement.setInt(5, visit.getDoctorID());
			preparedStatement.setInt(6, visit.getPrice());
			preparedStatement.setBoolean(7, false);
			preparedStatement.setInt(8,visit.getNurseID());
			preparedStatement.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void InsertNurse(Nurse nurse) {
		String sql = " insert into nurses values(?,?,?,?,?,?,?,?,?)";
			
		try {
			
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			
			preparedStatement.setString(1,nurse.getFirstName());
			preparedStatement.setString(2, nurse.getLastName());
			preparedStatement.setInt(3, nurse.getWorkExperiance());
			preparedStatement.setInt(4, nurse.getID());
			preparedStatement.setInt(5, nurse.getSalary());
			preparedStatement.setInt(6, nurse.getPaid_leave());
			preparedStatement.setString(7,nurse.getPassword());
			preparedStatement.setString(8, nurse.getStatus());
			// preparedStatement.setInt(9, nurse.getSalaryPerCheckin());
			preparedStatement.setDate(9,nurse.getDateGuide());
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	static void InsertDoctor(Doctor doctor ) {
		String sql = " INSERT INTO doctors VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			
		try {
			
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			
			preparedStatement.setString(1,doctor.getFirstName());
			preparedStatement.setString(2, doctor.getLastName());
			preparedStatement.setInt(3, doctor.getWorkExperience());
			preparedStatement.setString(4, doctor.getSpecialty());
			preparedStatement.setInt(5, doctor.getID());
			preparedStatement.setInt(6, doctor.getIncome_In_Month());
			preparedStatement.setString(7,doctor.getPassword());
			preparedStatement.setString(8, doctor.getStatus());
			preparedStatement.setBoolean(9, doctor.Has_massage());
			preparedStatement.setFloat(10,doctor.GetRating());
			preparedStatement.setInt(11,doctor.GetRatingNum());
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	static public void InsertPersonel(Personel personel){
		String sql = " insert into personel values(?,?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			
			preparedStatement.setString(1,personel.getFirstName());
			preparedStatement.setString(2, personel.getLastName());
			preparedStatement.setInt(3, personel.getID());
			preparedStatement.setInt(4, personel.getSalary());
			preparedStatement.setInt(5, personel.getPaid_leave());
			preparedStatement.setString(6,personel.getPassword());
			preparedStatement.setString(7, personel.getStatus());
			preparedStatement.setString(8, personel.getJob());
			preparedStatement.setDate(9,personel.getRegisterDate());
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	static ArrayList <Doctor> Doctors = new ArrayList<Doctor>();
	static ArrayList <Nurse> Nurses = new ArrayList<Nurse>();
	static ArrayList <Patient> Patients = new ArrayList<Patient>();
	static ArrayList <Personel> PersonelList = new ArrayList<Personel>();
	static ArrayList <Visit> Visits = new ArrayList<Visit>();
	static ArrayList<Visit> VisitsFilteredByID = new ArrayList<>();
	
	static void ImportDoctors(boolean filter) {
	
		String FirstName=null;
		String LastName=null;
		int WorkExperiance=0;
		String Specialty=null;
		int ID=0;
		int income_In_Month=0;
		String Password=null;
		String Status=null;
		boolean has_massage=false;
		int ratingNum = 1 ;
		float rating = 0;

		String sql = "SELECT * FROM doctors ORDER BY Specialty , Rating DESC";
		
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
				Password = resultSet.getString(7);
				Status = resultSet.getString(8);
				has_massage = resultSet.getBoolean(9);
				rating = resultSet.getFloat(10);
				ratingNum = resultSet.getInt(11);

				if (filter) {
					if (Status.equals("is working")) {
						Doctor doctor = new Doctor(FirstName, LastName, WorkExperiance, Specialty, ID, income_In_Month,
								Password, Status, has_massage , rating , ratingNum);
						Doctors.add(doctor);
					}
				} else {
					Doctor doctor = new Doctor(FirstName, LastName, WorkExperiance, Specialty, ID, income_In_Month,
								Password, Status, has_massage , rating , ratingNum);
					Doctors.add(doctor);
				}
				
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	static void ImportNurses(boolean filter) {

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
				String Password = resultSet.getString(7);
				String Status = resultSet.getString(8);
				int SalaryPerCheckin = Nurse.SalaryPerCheckin(WorkExperiance);
				Date DateGuide = resultSet.getDate(9);
				
				if (filter) {
					if (Status.equals("is working")){
						Nurse nurse = new Nurse (Firstname, Lastname, WorkExperiance, ID , Salary ,  PaidLeave ,  Password , Status , SalaryPerCheckin , DateGuide);
						Nurses.add(nurse);
					}
				} else {
					Nurse nurse = new Nurse (Firstname, Lastname, WorkExperiance, ID , Salary ,  PaidLeave ,  Password , Status , SalaryPerCheckin , DateGuide);
					Nurses.add(nurse);
				}
			}
			
			Connector.close_connection();
		
		}

		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e);
		}
		
		
	
		
	}
	
	static void ImportPatients() {

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
				String  Status = rs.getString(6) ;
				Patient patient = new Patient ( Firstname , Lastname , ID , Insurance,Password ,Status );
				Patients.add(patient);
			}
			
			Connector.close_connection();
		
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e);
		}
		
	}
	
	static void ImportPersonel(boolean filter) {

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
				String Password = resultSet.getString(6);
				String Status = resultSet.getString(7);
				String Job = resultSet.getString(8);
				Date RegisterPerDay = resultSet.getDate(9);
				int SalaryPerDay = Personel.SalaryPerCheckin(Job);

				if (filter) {
					if (Status.equals("is working")) {
						Personel personel = new Personel ( Firstname , Lastname , ID , Salary ,  PaidLeave , Password , Status , Job , RegisterPerDay , SalaryPerDay);
						PersonelList.add(personel);
					}
				} else {
					Personel personel = new Personel ( Firstname , Lastname , ID , Salary ,  PaidLeave , Password , Status , Job , RegisterPerDay , SalaryPerDay);
					PersonelList.add(personel);
				}

			}
			
			Connector.close_connection();
		
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e);
		}
	}
	
	
	static void ImportVisits() {
		
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
				int NurseID = resultSet.getInt(8);
				Visit visit = new Visit(ID , Date , Prescription , PatientID , DoctorID , Price , IsRated , NurseID);
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

	static Admin Extract_Admin(int primary_key){

		int ID =0;
		String Password=null;
		String Firstname=null;
		String Lastname=null;
		boolean hasMassage = false ;
		String status = null ;
		String sql = "SELECT * FROM admins WHERE ID=?";
			
		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, primary_key);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			ID = resultSet.getInt(1);
			Password = resultSet.getString(2);
			Firstname = resultSet.getString(3);
			Lastname = resultSet.getString(4);
			hasMassage = resultSet.getBoolean(5);
			status = resultSet.getString(6);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Admin(ID, Password, Firstname, Lastname , hasMassage , status);
	}
	
	static void Import_Filtered_Visits(int EmployeeID , String column ) {
		String sql = "SELECT * FROM visits WHERE " + column +  "= ?";
		
		int ID = 0;
		Date Date = null;
		String Prescription = null;
		int PatientID = 0;
		int DoctorID = 0;
		int Price= 0;
		boolean IsRated = false;
		int NurseID = 0 ;
		
		try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, EmployeeID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ID = resultSet.getInt(1);
				Date = resultSet.getDate(2);
				Prescription = resultSet.getString(3);
				PatientID = resultSet.getInt(4);
				DoctorID = resultSet.getInt(5);
				Price= resultSet.getInt(6);
				IsRated = resultSet.getBoolean(7);
				NurseID = resultSet.getInt(8);
				
				Visit visit =  new Visit(ID , Date , Prescription , PatientID , DoctorID , Price , IsRated , NurseID);
				
				VisitsFilteredByID.add(visit);
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	static Doctor Extract_Doctor(int primary_key){
		String FirstName=null;
		String LastName=null;
		int WorkExperiance=0;
		String Specialty=null;
		int ID=0;
		int income_In_Month=0;
		String Password=null;
		String Status =null;
		boolean has_massage=false;
		int ratingNum = 1 ;
		float rating = 0;

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
			Password = resultSet.getString(7);
			Status = resultSet.getString(8);
			has_massage = resultSet.getBoolean(9);
			rating = resultSet.getFloat(10);
			ratingNum = resultSet.getInt(11);
			
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Doctor(FirstName, LastName, WorkExperiance, Specialty, ID, income_In_Month, Password, Status , has_massage , rating , ratingNum);
	}

	static Nurse Extract_Nurse(int primary_key){
		String FirstName=null;
		String LastName=null;
		int WorkExperiance=0;
		int ID=0;
		int salary=0;
		int paid_leave=0;
		String Password=null;
		String Status=null;
		int SalaryPerDay = 0 ;
		Date DateGuide = null ;
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
			Password = resultSet.getString(7);
			Status = resultSet.getString(8);
			SalaryPerDay = Nurse.SalaryPerCheckin(WorkExperiance);
			DateGuide = resultSet.getDate(9);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Nurse(FirstName, LastName, WorkExperiance, ID, salary, paid_leave, Password, Status , SalaryPerDay , DateGuide);
	}

	static Personel Extract_Personel(int primary_key){
		String Firstname = null;
		String Lastname = null;
		int ID = 0;
		int Salary = 0;
		int PaidLeave = 0;
		String Password = null;
		String Status = null;
		String Job = null ;
		Date RegisterPerDay = null ;
		int SalaryPerDay = 0 ;
		
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
			Password = resultSet.getString(6);
			Status = resultSet.getString(7);
			Job = resultSet.getString(8);
			RegisterPerDay = resultSet.getDate(9);
			SalaryPerDay =  Personel.SalaryPerCheckin(Job);
					
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new Personel(Firstname, Lastname, ID, Salary, PaidLeave, Password, Status , Job , RegisterPerDay , SalaryPerDay);
	}

	
	
	static Patient Extract_Patient(int primary_key){
		
		String Firstname = null;
		String Lastname = null;
		int ID = 0;
		String Insurance = null;
		String Password = null;
		String Status = null ;
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
			Status = resultSet.getString(6);
		}
		
		catch (Exception e) {
			System.out.println("WARNING ! ");
			System.out.println("You Probably have a null field in your accound if not so un comment the e.printstacktrace ");
			//e.printStackTrace();
		}
		return new Patient(Firstname, Lastname, ID, Insurance, Password ,Status);
	}

	static void Update(String table_name,String updatedParameter,String updatedValue,int ID) {
		try {
			String sql = "UPDATE "+table_name+" SET "+updatedParameter+"=? WHERE ID=?";

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setString(1, updatedValue);
			preparedStatement.setInt(2, ID);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void Update(String table_name,String updatedParameter,int updatedValue,int ID) {
		try {
			String sql = "UPDATE "+table_name+" SET "+updatedParameter+"=? WHERE ID=?";

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, updatedValue);
			preparedStatement.setInt(2, ID);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void Update(String table_name,String updatedParameter,boolean updatedValue,int ID) {
		try {
			String sql = "UPDATE "+table_name+" SET "+updatedParameter+"=? WHERE ID=?";

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setBoolean(1, updatedValue);
			preparedStatement.setInt(2, ID);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void Delete(String table_name,int ID) {
		try {
			String sql = "DELETE FROM "+table_name+" WHERE ID=?";

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
			preparedStatement.setInt(1, ID);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}