import java.sql.*;
// import java.sql.PreparedStatement;
// import java.sql.Statement;
// import java.util.ArrayList;
import java.util.Scanner;

public class Login {

	static void Admin(){

		Scanner strnig_input = new Scanner(System.in);
		Scanner int_input = new Scanner(System.in);
		
		String sql = "SELECT ID FROM admins";
        try {
			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery(sql);

			boolean ID_check=false;
			while (!ID_check){
				System.out.println("Enter Your ID:");
				int ID = int_input.nextInt();
				resultSet.beforeFirst();
				// resultSet.absolute(1);
				while (resultSet.next()) {
					if (ID == resultSet.getInt(1)){
						ID_check = true;
						break;
					}
				}
				if (ID_check) {
					boolean pass_check = false; 
					while (!pass_check) {
						System.out.println("Enter Your Password:");
						String Password = strnig_input.nextLine();
						String pass_sql = "SELECT Password FROM admins WHERE ID = ? ";
						PreparedStatement preparedStatement_pass = Connector.Connect().prepareStatement(pass_sql);
						preparedStatement_pass.setInt(1, ID);
						ResultSet pass_resultSet = preparedStatement_pass.executeQuery();
						pass_resultSet.next();
						// System.out.println(pass_resultSet.getString(1));
						if (Password.equals(pass_resultSet.getString(1))){
							pass_check = true;
							
							// System.out.println("dash nabord :(");
							Dashboard.Admin(DataBase.Extract_Admin(ID));
						} else {
							System.out.println("wrong password");
						}
					}
				} else {
					System.out.println("wrong username");
				}
			}
			
		} catch (Exception e) {
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

	static void Doctor(){

		Scanner strnig_input = new Scanner(System.in);

		String sql = "SELECT ID,Status FROM doctors";

        try {

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery(sql);

			boolean ID_check=false;

			while (!ID_check){

				System.out.println("Enter Your ID:");

				String ID = strnig_input.nextLine();

				resultSet.beforeFirst();
		
				while (resultSet.next()) {

					if (ID.equals(resultSet.getString(1)) && resultSet.getString(2).equals("is working")){
						ID_check = true;
						break;
					}

				}

				if (ID_check) {

					boolean pass_check = false; 
					while (!pass_check) {

						System.out.println("Enter Your Password:");

						String Password = strnig_input.nextLine();

						String pass_sql = "SELECT Password FROM doctors WHERE ID = ? ";

						PreparedStatement preparedStatement_pass = Connector.Connect().prepareStatement(pass_sql);
						preparedStatement_pass.setString(1, ID);
						ResultSet pass_resultSet = preparedStatement_pass.executeQuery();

						pass_resultSet.next();

						if (Password.equals(pass_resultSet.getString(1))){
							pass_check = true;
							
							// System.out.println("dash docotor nabord :(");
							Dashboard.Doctor(DataBase.Extract_Doctor(Integer.parseInt(ID)));
						} else {
							System.out.println("wrong password");
						}
					}
				} else {
					System.out.println("wrong ID");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void Nurse(){

		Scanner strnig_input = new Scanner(System.in);

		String sql = "SELECT ID,Status FROM nurses";

        try {

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery(sql);

			boolean ID_check=false;

			while (!ID_check){

				System.out.println("Enter Your ID:");

				String ID = strnig_input.nextLine();

				resultSet.beforeFirst();
		
				while (resultSet.next()) {

					if (ID.equals(resultSet.getString(1)) && resultSet.getString(2).equals("is working")){
						ID_check = true;
						break;
					}

				}

				if (ID_check) {

					boolean pass_check = false; 
					while (!pass_check) {

						System.out.println("Enter Your Password:");

						String Password = strnig_input.nextLine();

						String pass_sql = "SELECT Password FROM nurses WHERE ID = ? ";

						PreparedStatement preparedStatement_pass = Connector.Connect().prepareStatement(pass_sql);
						preparedStatement_pass.setString(1, ID);
						ResultSet pass_resultSet = preparedStatement_pass.executeQuery();

						pass_resultSet.next();

						if (Password.equals(pass_resultSet.getString(1))){
							pass_check = true;
							
							// System.out.println("dash nurse nabord :(");
							Dashboard.Nurse(DataBase.Extract_Nurse(Integer.parseInt(ID)));
						} else {
							System.out.println("wrong password");
						}
					}
				} else {
					System.out.println("wrong ID");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void Personel(){

		Scanner strnig_input = new Scanner(System.in);

		String sql = "SELECT ID,Status FROM personel";

        try {

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery(sql);

			boolean ID_check=false;

			while (!ID_check){

				System.out.println("Enter Your ID:");

				String ID = strnig_input.nextLine();

				resultSet.beforeFirst();
		
				while (resultSet.next()) {

					if (ID.equals(resultSet.getString(1)) && resultSet.getString(2).equals("is working")){
						ID_check = true;
						break;
					}

				}

				if (ID_check) {

					boolean pass_check = false; 
					while (!pass_check) {

						System.out.println("Enter Your Password:");

						String Password = strnig_input.nextLine();

						String pass_sql = "SELECT Password FROM personel WHERE ID = ? ";

						PreparedStatement preparedStatement_pass = Connector.Connect().prepareStatement(pass_sql);
						preparedStatement_pass.setString(1, ID);
						ResultSet pass_resultSet = preparedStatement_pass.executeQuery();

						pass_resultSet.next();

						if (Password.equals(pass_resultSet.getString(1))){
							pass_check = true;
							// System.out.println("dash personel nabord :(");
							Dashboard.Personel(DataBase.Extract_Personel(Integer.parseInt(ID)));
						} else {
							System.out.println("wrong password");
						}
					}
				} else {
					System.out.println("wrong ID");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void Patient(){

		Scanner strnig_input = new Scanner(System.in);

		String sql = "SELECT ID FROM patients";

        try {

			PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = preparedStatement.executeQuery(sql);

			boolean ID_check=false;

			while (!ID_check){

				System.out.println("Enter Your ID:");

				String ID = strnig_input.nextLine();

				resultSet.beforeFirst();
		
				while (resultSet.next()) {

					if (ID.equals(resultSet.getString(1))){
						ID_check = true;
						break;
					}

				}

				if (ID_check) {

					boolean pass_check = false; 
					while (!pass_check) {

						System.out.println("Enter Your Password:");

						String Password = strnig_input.nextLine();

						String pass_sql = "SELECT Password FROM patients WHERE ID = ? ";

						PreparedStatement preparedStatement_pass = Connector.Connect().prepareStatement(pass_sql);
						preparedStatement_pass.setString(1, ID);
						ResultSet pass_resultSet = preparedStatement_pass.executeQuery();

						pass_resultSet.next();

						if (Password.equals(pass_resultSet.getString(1))){
							pass_check = true;
							
							// System.out.println("dash bimar nabord :(");
							Dashboard.Patient(DataBase.Extract_Patient(Integer.parseInt(ID)));
						} else {
							System.out.println("wrong password");
						}
					}
				} else {
					System.out.println("wrong ID");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// static void SignInDoc( ArrayList<Doctor> Doctors) {
	
	// 	boolean accepted = false ;
	// 	while (!accepted){

	// 		System.out.println("Enter Your ID :");
	// 		boolean correctID = false ;
	// 		Scanner s = new Scanner(System.in);
	// 		int ID = s.nextInt();
			
	// 		for(int i = 0 ; i <Doctors.size() ; i++) {
				
				
	// 			if(ID == Doctors.get(i).getID()) {
	// 				correctID = true ;
	// 				System.out.println("Enter Your Password :");
	// 				Scanner d = new Scanner(System.in);
	// 				String password = d.nextLine();

	// 				if(password.equals(Doctors.get(i).getPassword())) {
	// 					accepted = true;
	// 					// Dashboard.DashboardDoc();
	// 					System.out.println("Dashboard");
	// 				}
					
	// 			}
				
	// 		}
			
	// 		if(!accepted && !correctID) {
	// 			System.out.println("Wrong ID Try Again");
	// 		}
	// 		if(!accepted && correctID) {
	// 			System.out.println("Wrong Password Try Again");
	// 		}
	// 	}
		
	// }
	

	// static void SignInNur( ArrayList<Nurse> Nurse) {
	// 	boolean accepted = false ;
	// 	while (!accepted){
	// 		System.out.println("Enter Your ID :");
	// 		boolean correctID = false ;
	// 		Scanner s = new Scanner(System.in);
	// 		int ID = s.nextInt();
			
	// 		for(int i = 0 ; i <Nurse.size() ; i++) {
				
	// 			if(ID == Nurse.get(i).getID()) {
	// 				correctID = true ;
	// 				System.out.println("Enter Your Password :");
	// 				Scanner d = new Scanner(System.in);
	// 				String password = d.nextLine();

	// 				if(password.equals(Nurse.get(i).getPassword())) {
	// 					accepted = true;
	// 					// Dashboard.DashboardNur();
	// 					System.out.println("Dashboard");
	// 				}
					
	// 			}
				
	// 		}
			
	// 		if(!accepted && !correctID) {
	// 			System.out.println("Wrong ID Try Again");
	// 		}
	// 		if(!accepted && correctID) {
	// 			System.out.println("Wrong Password Try Again");
	// 		}
	// 	}
		
	// }
	

	// static void SignInPat( ArrayList<Patient> Patient) {

	// 	boolean accepted = false ;

	// 	while (!accepted){

	// 		Scanner int_input = new Scanner(System.in);

	// 		System.out.println("Enter Your ID :");
	// 		int ID = int_input.nextInt();

	// 		boolean correctID = false ;
			
	// 		int i=0;
	// 		for(i = 0 ; i <Patient.size() ; i++) {
	// 			if (ID == Patient.get(i).getID()) {
	// 				correctID = true ;
	// 				break;	
	// 			}
	// 		}
	// 		if (correctID) {
	// 			System.out.println("Enter Your Password :");
	// 			Scanner string_input = new Scanner(System.in);
	// 			String password = string_input.nextLine();
				
	// 			if(password.equals(Patient.get(i).getPassword())) {
	// 				accepted = true;
	// 				// Dashboard.DashboardPat();
	// 				System.out.println("Dashboard");
	// 			} else {
	// 				System.out.println("Wrong Password Try Again");
	// 			}
	// 		} 
	// 	}
	// }
}
