import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public abstract class Dashboard {
    
    static void Admin(Admin admin){
        System.out.println("WELCOME "+admin.getFirstName()+" "+admin.getLastName());
        
    }

    static void Doctor(Doctor doctor){
    	
        System.out.println("Welcome "+doctor.getFirstName()+" "+doctor.getLastName());
       
        if(doctor.Has_massage()) {
        	
        	System.out.println("You Have Some Patients To Visited ");
        	System.out.println("1) Lets Get To Work \t 2) Ignore");
        	
        	Scanner int_Input = new Scanner(System.in);
        	
        	int num;
        	
        	
        	boolean accepted = false ;
        	int i = 0 ;
        	while(!accepted) {
        		
        		num = int_Input.nextInt();
           
        		switch(num) {
            	case 1:
            		doctor.Visit();
            		accepted = true;
            		break ;
            	case 2 :
            		accepted = true;
            		break;
            	default :
            		i++;
            		switch(i) {
            		case 1:
            			System.out.println("Wrong Input");
                		break ;
                	case 2 :
                		System.out.println("Agnn Wrong Inputttt");
                		break;
                	case 3:
                		System.out.println("WTF HOW YOU ARE A FUCKING DOCTOR ??!! FOCUSSSS IT IS NOT THAT HARD!");
                		break ;
                	case 4 :
                		System.out.println("YOU FUCKING SERIOUS ????? IT'S THE 4TH TIME!!!!!");
                		break;
                	case 5 :
                		System.out.println("The Answer To Dumb Ppl Is Silence..");
                		break;
                	
                	default	:
                		System.out.println("...");
                		break;
            		}
            		
            	}
        	}
        	
        }
     
		Scanner int_Input = new Scanner(System.in);
    	System.out.println("enter 1 to add nurse:");
		int i = int_Input.nextInt();
		if (i==1) {
			doctor.AddNurse();
		}
    }

    static void Nurse(Nurse nurse){
        System.out.println("Welcome "+nurse.getFirstName()+" "+nurse.getLastName());
    }

    static void Personel(Personel personel){
        System.out.println("Welcome "+personel.getFirstName()+" "+personel.getLastName());
    }

    static void Patient(Patient patient){
        System.out.println("Welcome "+patient.getFirstName()+" "+patient.getLastname());
        
    
       /* if(patient.HasMassage()) {
        	System.out.println("Please Rate Your Experience With Our Doctor In Your Last Sesseion From 1 to 5 ");
        		        	
        	try {
        		String sql2 = "select IsRated from visits where PatientID = ?";
        		PreparedStatement preparedStatement = Connector.Connect().prepareStatement(sql2);
        		preparedStatement.setInt(1,patient.getID());
        		ResultSet resultSet2 = preparedStatement.executeQuery();
        		resultSet2.next();
        		boolean isRated = resultSet2.getBoolean(1);
        		
        		boolean accepted = false ;
            	
            	while(!accepted) {
            		if(!isRated) {
            			String sql3 = "update visits set IsRated = 1 where  "
            			accepted = true ;
            			String sql = "select DoctorID from visits where PatientID = ?";
                		preparedStatement = Connector.Connect().prepareStatement(sql);
                		preparedStatement.setInt(1, patient.getID());
                		ResultSet resultSet = preparedStatement.executeQuery();
                		resultSet.next();
                		int doctorID = resultSet.getInt(1);
                		Doctor doctor = Insert.Extract_Doctor(doctorID);
                		
                		
                		System.out.println("Doctor's Name : " + doctor.getFirstName() + " " + doctor.getLastName());
                		System.out.println("Doctor's Specialty : " + doctor.getSpecialty());
                		
                		Scanner intInput = new Scanner(System.in);
                    	int num = intInput.nextInt();
                    	
                    	doctor.CalculateRating(num, doctor);
                    	
                    	System.out.println("Thank You For Responding");
                    	System.out.println("To Rate Your Previous Doctors Go to Visit Informations In The Personal Information Field");
                    	
                    	patient.HasMassage(false);
                    	
                    	Insert.RenderPatient(patient);
            		}else {
            			resultSet2.next();
                		isRated = resultSet2.getBoolean(1);
            		}
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
        	
        	
        }*/
        
        System.out.println("What YOu Wanna DO ?");
        System.out.println("1)Visit    2)Viwe Information");
        Scanner intinput = new Scanner(System.in);
        int num = intinput.nextInt();
        switch(num) {
        case 1 :
        	 patient.VisitDoctor();
        	 break;
        case 2 :
        	 patient.View_personal_info();
        	 break;
        }
       
       
    }
    
    
}
