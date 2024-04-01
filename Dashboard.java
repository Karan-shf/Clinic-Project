// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;



public abstract class Dashboard {
	
	 
	
    static void Admin(Admin admin){
    	Scanner int_Input = new Scanner(System.in);
    	if(admin.HasMassage()) {
    		System.out.println("A Doctor Wants To Add A Nurse  ");
        	System.out.println("1) Lets Look At It  \t 2) Ignore");
        	
        	
        	int num = int_Input.nextInt();
        	
        	switch(num) {
        	case 1 : 
        		admin.Nurse_Entry_Confirmation();
        		break ;
        	case 2 : 
        		break ;
        	default :
        		break ;
        	}
        
        	
        	
    	}
    	
        System.out.println("WELCOME "+admin.getFirstName()+" "+admin.getLastName());
        System.out.println(" Press 1 to View or Press 2 to Edit Personal Information \n 3 ) Add Doctor \t 4 ) Add Nurse \n 5) Add Personel"
        		+ "\t 6 ) Fire Doctor \n 7 ) Fire Nurse \t 8 ) Fire Personel \n 9 ) View All Employee Information \t 10 ) quit \n 11 ) resignate"
        		+ "\t 10 ) View Fire Requests  ");
        
        Scanner int_input = new Scanner(System.in);
        int num = 0 ;
        boolean accepted = false ;
        
        while(!accepted) {
        	num = int_Input.nextInt();
        	
        	switch(num) {
				case 1 : 
					admin.View_personal_info();
					accepted = true ;
					break ;
					
				case 2 : 
					admin.Edit_personal_info("admins");
					accepted = true ;
					break ;
					
				case 3 : 
					admin.AddDoctor();
					accepted = true ;
					break ;
					
				case 4 : 
					admin.AddNurse();
					accepted = true ;
					break ;
					
				case 5 : 
					admin.AddPersonel();
					accepted = true ;
					break ;
					
				case 6 : 
					admin.FireDoctor();
					accepted = true ;
					break ;
					
				case 7 : 
					admin.FireNurse();
					accepted = true ;
					break ;
					
				case 8 : 
					admin.FirePersonel();
					accepted = true ;
					break ;
					
				case 9 : 
					admin.ViewAllEmployeeInformation();
					accepted = true ;
					break ;
					
				case 10 : 
					
					accepted = true ;
					break ;
					
				case 11 : 
					
					accepted = true ;
					break ;
				default :
					System.out.println("wrong input");
        		
            }
        	
        }
    
    }

    static void Doctor(Doctor doctor){
    	
        System.out.println("Welcome "+doctor.getFirstName()+" "+doctor.getLastName());

		// System.out.println(doctor.GetRating());

		if (doctor.GetRating()<=1.5 && (doctor.GetRatingNum()-1) >=5) {
			System.out.println("Your Rating Droped under 1.5");
			System.out.println("You Have been Fired From the Clinic Automatically");
			System.out.println("Try to Behave Better in the Future");
			// DataBase.Delete("doctors", doctor.getID());
			DataBase.Update("doctors","Status","fired",doctor.getID());
		} else {

			if (doctor.GetRating()<=2.5 && (doctor.GetRatingNum()-1) >=5) {
				System.out.println("WARNING!");
				System.out.println("Your Rating is Blow 2.5");
				System.out.println("Try to Improve Yourself or You'll be Fired From the Clinic Soon..");
			}
			if(doctor.Has_massage()) {
	
				System.out.println(" ----------------------------------");
				System.out.println("| You Have Some Patients To Visit  |");
				System.out.println("| 1) Lets Get To Work \t 2) Ignore |");
				System.out.println(" ----------------------------------");
				
				Scanner int_Input = new Scanner(System.in);
				
				int num;
				
				
				boolean accepted = false ;
				int i = 0 ;
				while(!accepted) {
					
					num = int_Input.nextInt();
			   
					switch(num) {
					case 1:
						while (doctor.Has_massage()) {doctor.Visit();}
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

			boolean cheBoolean = true;

			while (cheBoolean) {

				System.out.println(" 1 ) View Previouse Prescriptions \n "
        		+ "2 ) Add Nurses \n 3 ) View Personal Information \n 4 ) Edit Personal Information \n 5 ) Checkout ");
        
				Scanner int_input = new Scanner(System.in);
				boolean accepted = false ;
				try {
					while(!accepted){
						int num = int_input.nextInt();
						
						switch(num) {
						
						case 1 : 
							doctor.ViewPreviousePrescriptions();
							accepted = true ;
							break;
						case 2 : 
							doctor.AddNurse();
							accepted = true ;
							break ;
			   
						case 3 :
							doctor.View_personal_info();
							accepted = true ;
							break ;
						case 4 :
							doctor.Edit_personal_info("doctors");
							accepted = true ;
							break ;
						case 5 :
							
							accepted = true ;
							break ;
							
						default :
							System.out.println("Wrong Input");
							break ;
						}
					  
					}
					
				} catch (InputMismatchException e) {
					System.out.println("Please enter an integer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			

		}
       
    }

    static void Nurse(Nurse nurse) throws InterruptedException{

		switch (nurse.getStatus()) {
			case "is working":
				nurse.AddSalary();
				System.out.println("Welcome "+nurse.getFirstName()+" "+nurse.getLastName());
			
				System.out.println(" 1 ) View Personal Information \n 2 ) Edit Personal Information \n 3 ) View Patients Prescriptions \n 4 ) Take Leave \n 5 ) Checkout ");
				
				Scanner int_input = new Scanner(System.in);
				boolean accepted = false ;
				while(!accepted){
		
					int num = int_input.nextInt();
					
					switch(num) {
					
					case 1 : 
						nurse.View_personal_info();
						accepted = true ;
						break;
					case 2 : 
						nurse.Edit_personal_info("nurses");
						accepted = true ;
						break;
					case 3 :
						nurse.ViewPatientsPrescriptions();
						accepted = true ;
						break ;
					case 4 :
						if(nurse.TakeLeave()) {
							accepted = true ;
							System.out.println("Have a Good Time See You Next Time ");
							
						}
						else {
							System.out.println("You Dont Have Any Paid Leave Left \n Do You Want to Take an Unpaid Leave Instead ?"
									+ "\n 1 ) Yes       2 ) No ");
							num = int_input.nextInt();
							switch(num) {
							case 1 :
								accepted = true ;
								break;
							case 2 : 
								System.out.println(" 1 ) View Personal Information \n 2 ) Edit Personal Information \n 3 ) View Patients Prescriptions \n 4 ) Take Leave \n 5 ) Checkout ");
								
							}
							
							
							}
						break;
					case 5 :
						accepted = true ;
						break;
					default :
						System.out.println("?????");
					}
				}
				break;
			case "waiting for confirmation":
				System.out.println("We Are Considering Your Request");
				System.out.println("Please Wait for Admin Confirmation");
				System.out.println("Thanks for being patient :)");
				break;
			case "rejected":
				System.out.println("Your Entry Has been Rejected by Admin");
				System.out.println("hope you have a better luck next time");
				System.out.println("\nP.S. If You Are Narjes Don't Contact us Again");
				DataBase.Delete("nurses", nurse.getID());
				break;
			case "fired":
				System.out.print("\033[H\033[2J");
				System.out.println("You Have been Fired From the Clinic by Admin");
				System.out.println("try to behave better next time\n");
				System.out.println("self destructing in:");
				for (int i=5;i>0;i--) {
					System.out.print(". ");
					Thread.sleep(1000);
					// System.out.print("\033[H\033[2J"); //system(clear)
				}
				System.out.print("\033[H\033[2J");
				System.out.println("\nGoodBye "+nurse.getFirstName());
				System.out.println("\n(◣ ͜ʖ◢)ψ");
				Thread.sleep(3000);
				System.out.print("\033[H\033[2J");
				System.out.println("lol..");
				Thread.sleep(1000);
				System.out.print("\033[H\033[2J");
				DataBase.Delete("nurses", nurse.getID());
				break;
			default:
				System.out.println("?????");
		}


        
    }

    static void Personel(Personel personel){
        System.out.println("Welcome "+personel.getFirstName()+" "+personel.getLastName());
		personel.AddSalary();
		System.out.println(" 1 ) View Personal Information \n 2 ) Edit Personal Information \n 3 ) Take Leave \n 4 ) quit \n 5 ) Resignate ");
		
		Scanner intinput = new Scanner(System.in);
		boolean accepted = false ;
		while(!accepted) {
			int num = intinput.nextInt();
			switch(num) {
				case 1 :
					accepted = true ;
					personel.View_personal_info();
					break;
					
				case 2 :
					accepted = true ;
					personel.Edit_personal_info("personel");
					break;
					
				case 3 :
					if(personel.TakeLeave()) {
						accepted = true ;
						System.out.println("Have a Good Time See You Next Time ");
						
					}
					else {
						System.out.println("You Dont Have Any Paid Leave Left \n Do You Want to Take an Unpaid Leave Instead ?"
								+ "\n 1 ) Yes       2 ) No ");
						num = intinput.nextInt();
						switch(num) {
						case 1 :
							accepted = true ;
							break;
						case 2 : 
							System.out.println(" 1 ) View Personal Information \n 2 ) Edit Personal Information \n 3 ) Take Leave \n 4 ) quit \n 5 ) Resignate ");
							
						}
						
						
						}
					break;
					
				case 4 :
					
					break;
					
				case 5 :
					
					break;
					
				default : 
					break ;
			}
		}
    }

    static void Patient(Patient patient){
        System.out.println("Welcome "+patient.getFirstName()+" "+patient.getLastName());
        
    
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
        
        while(true) {
			System.out.println("What YOu Wanna DO ? \n");
			System.out.println(" 1 ) Visit \n 2 ) View Personal Information \n 3 ) Edit Personal Information \n 4 ) Rate Visits ");
			Scanner intinput = new Scanner(System.in);
			int num = intinput.nextInt();
			switch(num) {
				case 1 :
					patient.VisitDoctor();
					break;
					
				case 2 :
					patient.View_personal_info();
					break;
					
				case 3 :
					patient.Edit_personal_info("patients");
					break;
					
				case 4 :
					patient.RateVisit();
					break;
					
				default : 
					break ;
			}
        }
        
       
       
       
    }
    
    
}
