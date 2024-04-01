import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public abstract class Person {
	
	private String firstName;
	private String lastName;
	private int ID;
	private String password;
	 private String status;
	 
	public Person ( String firstName ,String lastName ,int ID ,String password , String status) {
		this.firstName = firstName;
		this.lastName = lastName ;
		this.ID = ID;
		this.password = password ;
		this.status = status;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	 public void setStatus(String status) {
	    	this.status = status;
	        
	 }
	
	public int getID() {
		return ID ;
	}
	
	public String getPassword() {
		return password ;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public abstract void View_personal_info();

	public void Edit_personal_info(String table) {
		
		System.out.println("Choose Which Field You Are Willing to Edit ");
		System.out.println(" 1 ) First Name \t 2 ) Last Name \n 3 ) Password");
		
		boolean accepted = false ;
		Scanner int_input = new Scanner(System.in);
		Scanner String_input = new Scanner(System.in);
		
		while(!accepted) {
			int num = int_input.nextInt();
			switch(num) {
			
			case 1 :
				System.out.println("Enter the New Firstname");
				String newFirstName = String_input.nextLine();
				this.setFirstName(newFirstName);
				
				DataBase.Update(table , "Firstname" , newFirstName , this.getID() );
				System.out.println("Firstname Was Changed Seccuessfully");
				accepted = true ;
				break ;
			
			case 2 :
				System.out.println("Enter the New Lirstname");
				String newLastName = String_input.nextLine();
				this.setLastName(newLastName);
				
				DataBase.Update(table , "Lastname" , newLastName , this.getID() );
				System.out.println("Lastname Was Changed Seccuessfully");
				accepted = true ;
				break ;
			
			case 3 :
				
				
				String sql = "SELECT Password FROM " + table + " WHERE ID = ?";
				try {
					PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
					preparedStatement.setInt(1, this.getID());
					ResultSet resultSet = preparedStatement.executeQuery();
					resultSet.next();
					boolean accepted2 = false ;
					while(!accepted2){
						System.out.println("Enter You Old Password : ");
						String password = String_input.nextLine();
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
							
						}else {
							System.out.println("Wrong Password Try Again \n");
						}
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				accepted = true ;
				break ;
				
			default :
				System.out.println("Wrong Input");
			
			}
		}
			
	}

	public void Resignate(String table) {
		
		DataBase.Update(table, "Status", "is resignated" , this.getID());
	}
	
	public void SetStatusLIL(String status){
		this.status = status;
	}
	  
}
