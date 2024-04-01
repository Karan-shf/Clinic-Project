import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;


class Personel extends Person {
  
    private int salary;
    private int paid_leave;
   
    private String job ;
	private Date registerDate;
	private int salaryPerCheckin ;
	
    public Personel(String firstName, String lastName, int ID, int salary, int paid_leave,
    String password, String status , String job , Date registerDate , int salaryPerDay  ) {

       super(firstName, lastName,ID, password , status);
       this.salary = salary;
       this.paid_leave = paid_leave;
       this.registerDate = registerDate;
       this.salaryPerCheckin = salaryPerDay;
       this.job = job ;
    }
    
    public Date getRegisterDate(){
    	return registerDate;
    }

    public int getSalary() {
        return salary;
    }
    public int getPaid_leave() {
        return paid_leave;
    }
    
  
    public String getJob() {
    	return job ;
    }
    
    
    
    
    
    public static int SalaryPerCheckin(String job ) {
   	 int salaryPerCheckin = 0 ;
   	 try {
       	 String sql = "SELECT " + job + " FROM basesalary";
            
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            salaryPerCheckin = resultSet.getInt(1);
            

            
       }
   	 
   	 
       catch(Exception e) {
       	e.printStackTrace();
       }
   	 
   	return salaryPerCheckin;
   }
    
    Calendar cal = Calendar.getInstance();
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    
    public void AddSalary() {
    	this.salaryPerCheckin = this.SalaryPerCheckin(this.job);
    	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    	if(dayOfMonth == 1) {
    		
    		this.salary = this.salaryPerCheckin;
    	}
    	else {
    		this.salary += this.salaryPerCheckin;
    	}
    	
    	try {
    		Statement statement = Connector.statement();
    		String sql = "UPDATE personel SET Salary=? WHERE Job = ? AND ID = ? ";
    		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
    		preparedStatement.setInt(1, this.salary);
    		preparedStatement.setString(2, this.job);
    		preparedStatement.setInt(3, this.getID());
    		preparedStatement.executeUpdate();
    		
    		
    		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    public String toString() {
    	
    	return "Name " + getFirstName() + " " + getLastName() + " \n Job : " + job ; 
    }
    
    public boolean TakeLeave() {
    	if(paid_leave > 0) {
    		
    		paid_leave --;
    		AddSalary();
        	DataBase.Update("personel", "PaidLeave", paid_leave, this.getID());
        	return true ;
        	
    	}
    	else {
    		return false ;
    	}
    	
    	
    }
    
    public void SecretaryAcsess() {
    	
    	
    	System.out.println(" 1 ) See ALL Waiting List \n 2 ) See Waiting List Filtered By Doctor ID ");
    	
    	Scanner int_intput = new Scanner(System.in);
    	
    	int num = int_intput.nextInt();
		boolean accepted  = false ;			
		while(!accepted) {
			switch(num) {
			case 1 :
				try {
					String sql3 = "SELECT * FROM waitinglist";
					PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql3);
					ResultSet resultSet3 = preparedStatement.executeQuery();
				
					
					
					while(resultSet3.next()) {
						int DoctorID = resultSet3.getInt(2);
						String sql4 = "SELECT Firstname , Lastname FROM doctors WHERE ID = ? ";
						preparedStatement = Connector.Connect().prepareStatement(sql4);
						preparedStatement.setInt(1, DoctorID);
						ResultSet resultSet4 = preparedStatement.executeQuery();
						resultSet4.next();
						System.out.println("Doctor ID : " + DoctorID );
						System.out.println("Doctor Name : " + resultSet4.getString(1) + " " +resultSet4.getString(2));
						System.out.println("Patient ID : " +  resultSet3.getInt(3) );
						System.out.println("___________________________");
					}
					
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				accepted = true ;
				break;
			case 2 :
				System.out.println("Enter Dcotor ID : ");
				
				int DoctorID = int_intput.nextInt();
		    	
				String sql = "SELECT * FROM waitinglist WHERE DoctorID = ? ";
				accepted = true ;
				
				
				try {
					PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
					preparedStatement.setInt(1, DoctorID);
					ResultSet resultSet = preparedStatement.executeQuery();
					
					String sql2 = "SELECT Firstname , Lastname FROM doctors WHERE ID = ?";
					preparedStatement = Connector.Connect().prepareStatement(sql2);
					preparedStatement.setInt(1, DoctorID);
					ResultSet resultSet2 = preparedStatement.executeQuery();
					resultSet2.next();
					int i = 0 ;
					while(resultSet.next()  ) {
						i++;
						
						
						
						System.out.println("Doctor ID : " + resultSet.getInt(2));
						System.out.println("Doctor Name : " + resultSet2.getString(1) + " " +resultSet2.getString(2));
						System.out.println("Patient ID : " +  resultSet.getInt(3) );
						System.out.println("___________________________");
					}
					System.out.println("There Are " + i + " Patients in The Waiting List In Total");
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			default :
				System.out.println("Wrong Input ");
			
			}
		}
   }
    
    public void PharmacistAccess() {
    	try {
    		Scanner int_intput = new Scanner(System.in);
        	
        	System.out.println("Enter Visit ID : ");
    		
    		int VisitID = int_intput.nextInt();
        	
    		String sql = "SELECT * FROM visits WHERE ID = ? ";
    		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
    		preparedStatement.setInt(1, VisitID);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		resultSet.next();
    		System.out.println("________________________________________");
    		System.out.println("Prescription : " +  resultSet.getString(3));
    		System.out.println("Date : " +  resultSet.getDate(2));
    		System.out.println("NurseID : " +  resultSet.getInt(8));
    		System.out.println("________________________________________");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
		
    }

    @Override
    public void View_personal_info() {
    	System.out.println("-------------------------------------------------");
    	System.out.println("ID  : " + this.getID() );
		System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
		System.out.println("Job : " + this.getJob());
		System.out.println("Salary Per Checkin : " + Personel.SalaryPerCheckin(job));
		System.out.println("Salary In Current Month  : " + this.getSalary());
		System.out.println("PaidLeave : " + this.paid_leave );
		System.out.println("-------------------------------------------------");
		
    }
    
}
