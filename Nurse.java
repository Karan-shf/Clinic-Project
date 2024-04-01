import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


class Nurse extends Person {
    
    private int WorkExperience;
    private int salary;
    private int paid_leave;
    private String status;
    private Date registerDate ;
    private int salaryPerCheckin;

    public Nurse(String firstName, String lastName, int workExperience, int ID, int salary, int paid_leave,
    		String password, String status ,int salaryPerCheckin, Date registerDate) {
    	
    	super(firstName, lastName, ID, password);

        WorkExperience = workExperience;
        this.registerDate = registerDate;
        this.salary = salary;
        this.paid_leave = paid_leave;
        this.status = status;
        this.salaryPerCheckin = salaryPerCheckin;

    }
    
    
    public int getSalaryPerCheckin() {
    	return salaryPerCheckin ;
    }
    
    public Date getRegisterDate() {
    	return registerDate;
    }
    
    public int getWorkExperiance() {
        return WorkExperience;
    }
   
    public int getSalary() {
        return salary;
    }
    public int getPaid_leave() {
        return paid_leave;
    }
   
    public String getStatus(){
        return status;
    }
    
   
    
    public static int SalaryPerCheckin(int WorkExperience ) {
    	 int salaryPerCheckin = 0 ;
    	 try {
        	 String sql = "SELECT Nurse FROM basesalary";
             
             PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();
             resultSet.next();
             int nurseBaseSalary = resultSet.getInt(1);
             

             salaryPerCheckin= (int)(nurseBaseSalary + (WorkExperience *((0.1)*nurseBaseSalary)));
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    	 
    	return salaryPerCheckin;
    }
    Calendar cal = Calendar.getInstance();
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    
    public void AddSalary() {
    	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    	if(dayOfMonth == 1) {
    		
    		this.salary = this.salaryPerCheckin;
    	}
    	else {
    		this.salary += this.salaryPerCheckin;
    	}
    	
    	
    	DataBase.Update("nurses", "Salary", salary , this.getID());
    }
    
    public void setStatus(String status) {
        this.status = status;

        String sql = "UPDATE nurses set Status =? WHERE ID=?";
        int nurse_ID=this.getID();
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2,nurse_ID);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void ViewPatientsPrescriptions() {
    	
    }
    
    public boolean TakeLeave() {
    	if(paid_leave > 0) {
    		
    		paid_leave --;
    		AddSalary();
        	DataBase.Update("nurses", "PaidLeave", paid_leave, this.getID());
        	return true ;
        	
    	}
    	else {
    		return false ;
    	}
    }
    
    public String toString() {
    	return " \n Name : " + getFirstName()+ " " + getLastName() + "\t Work Experience : " + WorkExperience +	"\n Nurse ID : "  + getID();
    }


    @Override
    public void View_personal_info() {
    	System.out.println("ID  : " + this.getID() );
		System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
		System.out.println("Work Experience : " + WorkExperience );
		System.out.println("Salary Per Checkin : " + Nurse.SalaryPerCheckin(WorkExperience));
		System.out.println("Salary In Current Month  : " + this.getSalary());
		System.out.println("PaidLeave : " + this.paid_leave );
		
    }
    
    
}
