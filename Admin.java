import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Admin extends Person  {

    private boolean hasMassage;

    public Admin(int ID, String password,String firstName,String lastName , boolean hasMassage) {
    	super(firstName, lastName, ID, password);
        this.hasMassage = hasMassage;
        
    }

    public boolean HasMassage() {
    	return hasMassage ;
    }
    
    public void SetHasMassage(boolean hasMassage) {
    	this.hasMassage = hasMassage;
    }
    
    public void Nurse_Entry_Confirmation(){

        ArrayList <Nurse> undecided_Nurses = new ArrayList<Nurse>(); 

        String sql = "SELECT * FROM nurses WHERE status='waiting for confirmation' ";

        try {
            Statement statement = Connector.statement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
            	String Firstname = resultSet.getString(1);
				String Lastname = resultSet.getString(2);
				int WorkExperiance = resultSet.getInt(3);
				int ID = resultSet.getInt(4);
				int Salary = resultSet.getInt(5);
				int PaidLeave = resultSet.getInt(6);
				String Password = resultSet.getString(7);
				String Status = resultSet.getString(8);
				int SalaryPerCheckin = resultSet.getInt(9);
				Date RegisterDate = resultSet.getDate(10);
				
				Nurse nurse = new Nurse (Firstname, Lastname, WorkExperiance, ID , Salary ,  PaidLeave ,  Password , Status , SalaryPerCheckin , RegisterDate);
                undecided_Nurses.add(nurse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner string_input = new Scanner(System.in);

        System.out.println("There are "+undecided_Nurses.size()+" Nurses Waiting to be added to clinic\n");

        for (int i=0;i<undecided_Nurses.size();i++){
            System.out.println("Nurse no."+(i+1)+":");
            System.out.println(undecided_Nurses.get(i));
            System.out.println("choose nurse entry:");
            System.out.println("[1].Accept\n[2].Reject");
            boolean check = true;
            while (check) {
                String answer = string_input.next();
                switch (answer) {
                    case "1":
                        undecided_Nurses.get(i).setStatus("is working"); 
                        check=false;
                        break;
                    case "2":
                        undecided_Nurses.get(i).setStatus("rejected"); 
                        check=false;
                        break;
                    default:
                        System.out.println("Wrong Input");
                }
            }

            // String nurse_sql = "UPDATE nurses set status=? WHERE ID=?";
// 
//             try {
//                 PreparedStatement preparedStatement = Connector.Connect().prepareStatement(nurse_sql);
//                 preparedStatement.setString(1, undecided_Nurses.get(i).getStatus());
//                 preparedStatement.setInt(2, undecided_Nurses.get(i).getID());
//                 preparedStatement.executeUpdate();
//                 System.out.println("Entry updated succesfully");
//             } 
//             catch (ClassNotFoundException | SQLException e) {
//                 e.printStackTrace();
//             }

            if (i != undecided_Nurses.size()-1) {
                System.out.println("---------------------------------------------------------------------");
            }
            
        }

        String sql2 = "UPDATE Admins SET HasMassage=0";
        try {
            PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql2);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void View_personal_info() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'View_personal_info'");
    }
    
    public void AddDoctor() {

    	System.out.println("Please Fill In the Required Fields");
    	Scanner string_input = new Scanner(System.in);
    	Scanner int_input = new Scanner(System.in);
    	
    	System.out.println("First Name : ");
    	String firstName = string_input.nextLine();
    	System.out.println("Last Name : ");
    	String lastName = string_input.nextLine();
    	System.out.println("Work Experience ");
    	int workExperience = int_input.nextInt();
    	System.out.println("Specialty ");
    	String specialty = string_input.nextLine();
    	
    	int ID = ThreadLocalRandom.current().nextInt(10000000,99999999);
    	String password = "12345678";
    	String status = "is working";
    	
    	Doctor doctor = new Doctor(firstName , lastName , workExperience , specialty , ID , 0 , password ,status , false , 0 ,1);
    	DataBase.InsertDoctor(doctor);
    	
    	System.out.println("Doctor Was Added Sucsessfully ");
    	
    }
    
    public void AddNurse() {

        Scanner string_input = new Scanner(System.in);
        Scanner int_input = new Scanner(System.in);

        String FirstName;
        String LastName;
        int WorkExperiance;
        int ID = ThreadLocalRandom.current().nextInt(10000000,99999999);
        int salary = 0; 
        int paid_leave = 7;
        String Password = "12345678";
        String status = "is working";
        java.util.Date javDate = new java.util.Date();
        Date registerDate = new Date(javDate.getTime());
        

        System.out.println("please fill in the required fields");

        System.out.print("first name: ");
        FirstName = string_input.nextLine();

        System.out.print("last name: ");
        LastName = string_input.nextLine();

        System.out.println("Work Experience: ");
        WorkExperiance = int_input.nextInt();
        
        int salaryPerCheckin = Nurse.SalaryPerCheckin(WorkExperiance);
        Nurse new_nurse = new Nurse(FirstName, LastName, WorkExperiance, ID, salary, paid_leave, Password, status,salaryPerCheckin , registerDate );

        DataBase.InsertNurse(new_nurse);

        System.out.println("Nurse Was Added Sucsessfully ");
    
    
    }
    

	public void AddPersonel() {
        Scanner string_input = new Scanner(System.in);
        Scanner int_input = new Scanner(System.in);

        String FirstName;
        String LastName;
        int ID = ThreadLocalRandom.current().nextInt(10000000,99999999);
        int salary = 0; 
        int paid_leave = 7;
        String Password = "12345678";
        String status = "is working";
        java.util.Date javDate = new java.util.Date();
        Date registerDate = new Date(javDate.getTime());
        String job = null ;
        int num;
        
        System.out.println("please fill in the required fields");

        System.out.print("First Name: ");
        FirstName = string_input.nextLine();
        
        System.out.print("Last Name: ");
        LastName = string_input.nextLine();
        
        System.out.print("Job :  1 ) Pharmacist 2) Secretary 3) Chef 4) Gaurd 5)Janitor ");
        boolean accepted = false ;
        
        while(!accepted) {
        num = int_input.nextInt();
        switch(num) {
            
        case 1 :
                job = "Pharmacist";
            accepted = true ;
            break;
        
        case 2 :
                job = "Secretary";
            accepted = true ;
            break;
        case 3 :
            job = "Chef";
            accepted = true ;
            break;
        
        case 4 :
            job = "Gaurd";
            accepted = true ;
            break;
        case 5 :
            job = "Janitor";
            accepted = true ;
            break;
            
        default :
            
        }
        }
    
        
        int salaryPerCheckin = Personel.SalaryPerCheckin(job);
        
        Personel personel = new Personel(FirstName, LastName, ID, salary, paid_leave, Password, status, job , registerDate ,salaryPerCheckin );

        DataBase.InsertPersonel(personel);

        System.out.println( job + " Was Added Sucsessfully ");
	}
	
	
	
	public void FireDoctor() {

        if (DataBase.Doctors.size() != 0) {DataBase.ImportDoctors(true);}

        Scanner int_input = new Scanner(System.in);

        System.out.println("Doctors List:");
        for (int i=0;i<DataBase.Doctors.size();i++){
            System.out.println(DataBase.Doctors.get(i));
            if (i != DataBase.Doctors.size()-1) {
                System.out.println("------------------------------------");
            }
        }

        System.out.print("Enter Doctors ID to Fire From Clinic:");

        boolean check = true;
        int docID;
        while (check) {

            try {
                docID=int_input.nextInt();
    
                for (int i=0;i<DataBase.Doctors.size();i++) {
                    if (DataBase.Doctors.get(i).getID()==docID) {
                        DataBase.Update("doctors","Status", "fired", docID);
                        System.out.println("doctor was fired succesfully");
                        check = false;
                        break;
                    }
                }
    
                if (check) {
                    System.out.println("ID not found :(");
                    System.out.println("Please Try Again");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("please enter an integer");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

	}
	
	public void FireNurse() {

		if (DataBase.Nurses.size() != 0) {DataBase.ImportNurses(true);}

        Scanner int_input = new Scanner(System.in);

        System.out.println("Nurses List:");
        for (int i=0;i<DataBase.Nurses.size();i++){
            System.out.println(DataBase.Nurses.get(i));
            if (i != DataBase.Nurses.size()-1) {
                System.out.println("------------------------------------");
            }
        }

        System.out.print("Enter Nurses ID to Fire From Clinic:");

        boolean check = true;
        int nurseID;
        while (check) {

            try {
                nurseID=int_input.nextInt();
    
                for (int i=0;i<DataBase.Nurses.size();i++) {
                    if (DataBase.Nurses.get(i).getID()==nurseID) {
                        DataBase.Update("nurses","Status", "fired", nurseID);
                        System.out.println("doctor was fired succesfully");
                        check = false;
                        break;
                    }
                }
    
                if (check) {
                    System.out.println("ID not found :(");
                    System.out.println("Please Try Again");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("please enter an integer");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
	}
	
	public void FirePersonel() {

		if (DataBase.PersonelList.size() != 0) {DataBase.ImportPersonel(true);}

        Scanner int_input = new Scanner(System.in);

        System.out.println("Personel List:");
        for (int i=0;i<DataBase.PersonelList.size();i++){
            System.out.println(DataBase.PersonelList.get(i));
            if (i != DataBase.PersonelList.size()-1) {
                System.out.println("------------------------------------");
            }
        }

        System.out.print("Enter personel ID to Fire From Clinic:");

        boolean check = true;
        int personelID;
        while (check) {

            try {
                personelID=int_input.nextInt();
    
                for (int i=0;i<DataBase.PersonelList.size();i++) {
                    if (DataBase.PersonelList.get(i).getID()==personelID) {
                        DataBase.Update("personel","Status", "fired", personelID);
                        System.out.println("doctor was fired succesfully");
                        check = false;
                        break;
                    }
                }
    
                if (check) {
                    System.out.println("ID not found :(");
                    System.out.println("Please Try Again");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("please enter an integer");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
	}
	
	
	
	public void ViewAllEmployeeInformation() {
		System.out.println("Enter [1] to view alllll employee records");
        System.out.println("Enter [2] to view all working doctors records");
        System.out.println("Enter [3] to view all working nurses records");
        System.out.println("Enter [4] to view all working personal records");
        System.out.println("Enter [5] to view all patients records");
        
        Scanner int_input = new Scanner(System.in);

        boolean check = true;

        while (check) {

            try {
                int num = int_input.nextInt();

                switch (num) {
                    case 1:
                        DataBase.Doctors.clear();
                        DataBase.Nurses.clear();
                        DataBase.PersonelList.clear();
                        DataBase.ImportDoctors(false);
                        DataBase.ImportNurses(false);
                        DataBase.ImportPersonel(false);

                        System.out.println("Doctors Record:");
                        for (int i=0;i<DataBase.Doctors.size();i++) {
                            DataBase.Doctors.get(i).View_personal_info();
                            if (i != DataBase.Doctors.size()-1) {
                                System.out.println("-------------------------------------");
                            }
                        }
                        System.out.println("==========================================");

                        System.out.println("Nurses Record:");
                        for (int i=0;i<DataBase.Nurses.size();i++) {
                            // System.out.println(DataBase.Nurses.get(i));
                            DataBase.Nurses.get(i).View_personal_info();
                            if (i != DataBase.Nurses.size()-1) {
                                System.out.println("-------------------------------------");
                            }
                        }
                        System.out.println("==========================================");

                        System.out.println("Personel Record:");
                        for (int i=0;i<DataBase.PersonelList.size();i++) {
                            // System.out.println(DataBase.PersonelList.get(i));
                            DataBase.PersonelList.get(i).View_personal_info();
                            if (i != DataBase.PersonelList.size()-1) {
                                System.out.println("-------------------------------------");
                            }
                        }
                                            
                        DataBase.Doctors.clear();
                        DataBase.Nurses.clear();
                        DataBase.PersonelList.clear();

                        check=false;
                        break;
                    case 2:
                        if (DataBase.Doctors.size() == 0) {DataBase.ImportDoctors(true);}
                        System.out.println("Working Doctors :");
                        for (int i=0;i<DataBase.Doctors.size();i++) {
                            // System.out.println(DataBase.Doctors.get(i));
                            DataBase.Doctors.get(i).View_personal_info();
                            if (i != DataBase.Doctors.size()-1) {
                                System.out.println("-------------------------------------");
                            }
                        }
                        check=false;
                        break;
                    case 3:
                        if (DataBase.Nurses.size() == 0) {DataBase.ImportNurses(true);}
                        System.out.println("Working Nurses :");
                        for (int i=0;i<DataBase.Nurses.size();i++) {
                            // System.out.println(DataBase.Nurses.get(i));
                            DataBase.Nurses.get(i).View_personal_info();
                            if (i != DataBase.Nurses.size()-1) {
                                System.out.println("-------------------------------------");
                            }
                        }
                        check=false;
                        break;
                    case 4:
                        if (DataBase.PersonelList.size() == 0) {DataBase.ImportPersonel(true);}
                        System.out.println("Working Personel :");
                        for (int i=0;i<DataBase.PersonelList.size();i++) {
                            // System.out.println(DataBase.PersonelList.get(i));
                            DataBase.PersonelList.get(i).View_personal_info();
                            if (i != DataBase.PersonelList.size()-1) {
                                System.out.println("-------------------------------------");
                            }
                        }
                        check=false;
                        break;
                    case 5:
                        if (DataBase.Patients.size() == 0) {DataBase.ImportPatients();}
                        System.out.println("Patients Record:");
                        for (int i=0;i<DataBase.Patients.size();i++) {
                            // System.out.println(DataBase.Patients.get(i));
                            DataBase.Patients.get(i).View_personal_info();
                            if (i != DataBase.Patients.size()-1) {
                                System.out.println("-------------------------------------");
                            }
                        }
                    default:
                        System.out.println("Wrong Input!");
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Please Enter an Integer");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
        }


	}
	
	


    
}
