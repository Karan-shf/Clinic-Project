import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


class Nurse extends Person {
    
    private int WorkExperience;
    private int salary;
    private int paid_leave;
    private Date DateGuide ;
    private int salaryPerCheckin;

    public Nurse(String firstName, String lastName, int workExperience, int ID, int salary, int paid_leave,
    		String password, String status ,int salaryPerCheckin, Date DateGuide) {
    	
    	super(firstName, lastName, ID, password , status);

        WorkExperience = workExperience;
        this.DateGuide = DateGuide;
        this.salary = salary;
        this.paid_leave = paid_leave;
        
        this.salaryPerCheckin = salaryPerCheckin;

    }
    
    
    public int getSalaryPerCheckin() {
    	return salaryPerCheckin ;
    }
    
    public Date getDateGuide() {
    	return DateGuide;
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
    
   
    
    public static int SalaryPerCheckin(int WorkExperience ) {
    	 int salaryPerCheckin = 0 ;
    	 try {
        	 String sql = "SELECT Nurse FROM basesalary";
             
             PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();
             resultSet.next();
             int nurseBaseSalary = resultSet.getInt(1);
             
           

             salaryPerCheckin= (int)(nurseBaseSalary + ((WorkExperience ) *((0.1)*nurseBaseSalary)));
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    	 
    	return salaryPerCheckin;
    }
    
    public void WorkExperienceAdjuster(Date DateGuide) {
    	
    	 java.util.Date utilDate = new java.util.Date(DateGuide.getTime());
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String stringDateGuide = dateFormat.format(utilDate);
         
         java.util.Date nowDate = new java.util.Date();
         String stringNowDate = dateFormat.format(nowDate);
         
 	    int year_difference = 0;
 	    try {   
 	        java.util.Date date1 = dateFormat.parse(stringDateGuide);   
 	        java.util.Date date2 = dateFormat.parse(stringNowDate);     
 	        long time_difference = date2.getTime() - date1.getTime(); 

 	        year_difference = (int)(time_difference / (1000*60*60*24));
 	        year_difference = year_difference/365;
 	       
 	        
 	    }   
 	    // Catch parse exception   
 	    catch (ParseException excep) {   
 	        excep.printStackTrace();   
 	    }
 	    
 	    if(year_difference != 0) {
 	    	WorkExperience += year_difference;
 	    	salaryPerCheckin = Nurse.SalaryPerCheckin(WorkExperience);
 	    	try {
 	    		java.sql.Date TodayDateMySql = new java.sql.Date(nowDate.getTime());
 	    		String sql = "UPDATE nurses SET DateGuide = ? WHERE ID = ?";
 	 	    	PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql);
 				preparedStatement.setDate(1,TodayDateMySql );
 				preparedStatement.setInt(2, getID());
 				preparedStatement.executeUpdate();
 	    	}
 	    	catch(Exception e) {
 	    		e.printStackTrace();
 	    	}
 	    	
 	    	DataBase.Update("nurses", "WorkExperience", WorkExperience, this.getID());
 	    	
 	    
 	    }
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
    
    @Override
    public void setStatus(String status) {
    	
        this.SetStatusLIL(status);
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
    	
    	if(DataBase.VisitsFilteredByID.size() == 0) {
    		DataBase.Import_Filtered_Visits(this.getID() , "NurseID");
    	}
    	for(Visit visit :DataBase.VisitsFilteredByID ) {
    		visit.ShowVisit();
    		System.out.println("-----------------------------------" + "\n");
    	}
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
    	System.out.println("Nurse Status : " + getStatus() );
    	System.out.println("ID  : " + this.getID() );
		System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
		System.out.println("Work Experience : " + WorkExperience );
		System.out.println("Salary Per Checkin : " + Nurse.SalaryPerCheckin(WorkExperience));
		System.out.println("Salary In Current Month  : " + this.getSalary());
		System.out.println("PaidLeave : " + this.paid_leave );
		
    }
    
    
}
