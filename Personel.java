import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;


class Personel extends Person {
  
    private int salary;
    private int paid_leave;
    private String status;
    private String job ;
	private Date registerDate;
	private int salaryPerCheckin ;
	
    public Personel(String firstName, String lastName, int ID, int salary, int paid_leave,
    String password, String status , String job , Date registerDate , int salaryPerDay  ) {

       super(firstName, lastName,ID, password);
       this.salary = salary;
       this.paid_leave = paid_leave;
       this.status = status;
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
    
    public String getStatus() {
        return status ;
    }
    
    public String getJob() {
    	return job ;
    }
    
    public void setStatus(String status) {
    	this.status = status;
        
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
    

    @Override
    public void View_personal_info() {
    	
    	System.out.println("ID  : " + this.getID() );
		System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
		System.out.println("Job : " + this.getJob());
		System.out.println("Salary Per Checkin : " + Personel.SalaryPerCheckin(job));
		System.out.println("Salary In Current Month  : " + this.getSalary());
		System.out.println("PaidLeave : " + this.paid_leave );
		
    }
    
}
