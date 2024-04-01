// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;



public abstract class Dashboard {
	

    static void Admin(Admin admin){

		System.out.print("\033[H\033[2J");
    	System.out.println("WELCOME "+admin.getFirstName()+" "+admin.getLastName());

    	if(!admin.getStatus().equals("is resignated")) {
    		
			
			if(admin.HasMassage()) {
				
				System.out.println("A Doctor Wants To Add A Nurse  ");
            	System.out.println("1) Lets Look At It  \t 2) Ignore");
            	boolean accepted = false ;
            	
            	while(!accepted) {
					try {
						Scanner int_Input = new Scanner(System.in);
            			int num = int_Input.nextInt();
                    	
                    	switch(num) {
                    	case 1 : 
                    		accepted = true ;
                    		admin.Nurse_Entry_Confirmation();
                    		break ;
                    	case 2 : 
                    		accepted = true ;
                    		break ;
                    	default :
                    		System.out.println("Wrong Input");
                    		break ;
                    	}
            		}
            		catch(InputMismatchException e) {
            			System.out.println("Plese Enter an Integer ");
            		} catch (Exception e) {
						e.printStackTrace();
					}

            	}
            	
        	}
        	
            boolean isDone = false ;

            while(!isDone) {
				System.out.println("----------------------------------------------");
				System.out.println(" 1 ) View Personal Information \n 2 ) Edit Personal Information \n 3 ) Add Doctor \n 4 ) Add Nurse \n 5 ) Add Personel"
					+ " \n 6 ) Fire Doctor \n 7 ) Fire Nurse \n 8 ) Fire Personel \n 9 ) View All Employee Information \n 10 )change employee base salary \n 11 )resignate \n 12 )quit");
				
				int num = 0 ;
				boolean accepted = false ;
				
				while(!accepted) {
					try {
						Scanner int_Input = new Scanner(System.in);
	
						num = int_Input.nextInt();
						 
						switch(num) {
							case 1 :
								System.out.print("\033[H\033[2J");
								admin.View_personal_info();
								accepted = true ;
								break ;
								
							case 2 : 
								System.out.print("\033[H\033[2J");
								admin.Edit_personal_info("admins");
								accepted = true ;
								break ;
								
							case 3 : 
								System.out.print("\033[H\033[2J");
								admin.AddDoctor();
								accepted = true ;
								break ;
								
							case 4 : 
								System.out.print("\033[H\033[2J");
								admin.AddNurse();
								accepted = true ;
								break ;
								
							case 5 : 
								System.out.print("\033[H\033[2J");
								admin.AddPersonel();
								accepted = true ;
								break ;
								
							case 6 : 
								System.out.print("\033[H\033[2J");
								admin.FireDoctor();
								accepted = true ;
								break ;
								
							case 7 : 
								System.out.print("\033[H\033[2J");
								admin.FireNurse();
								accepted = true ;
								break ;
								
							case 8 : 
								System.out.print("\033[H\033[2J");
								admin.FirePersonel();
								accepted = true ;
								break ;
								
							case 9 : 
								System.out.print("\033[H\033[2J");
								admin.ViewAllEmployeeInformation();
								accepted = true ;
								break ;
								
							case 10 :
								System.out.print("\033[H\033[2J");
								admin.ChangeBaseSalary();
								accepted = true;
								break;
							
							case 11 : 
								System.out.print("\033[H\033[2J");
								System.out.println("Are You Sure You Want To Resign ? \n 1 ) Yes \t 2 ) No");
								int num2 = int_Input.nextInt();
								boolean accepted2  = false ;
								
								while(!accepted2) {
									switch(num2) {
										case 1 :
										admin.Resignate("admins");
										isDone = true ;
										accepted2 = true ;
										break;
									case 2 :
										accepted2 = true ;
										break;
									default :
										System.out.println("Wrong Input ");
											
									}
								}
									
									accepted = true ;
									break ;
	
							case 12:
								isDone = true ;
								accepted = true ;
								break ;
							 default :
								System.out.println("wrong input");
						}
						
					} catch (InputMismatchException e) {
						System.out.println("please enter an integer");
					} catch (Exception e) {
						e.printStackTrace();
					}
                 	
                }

            }
        
    	} else {
    		System.out.println("Admin Not Found !!");
    	}
    	
    }

    static void Doctor(Doctor doctor){
    	if(!doctor.getStatus().equals("is resignated")) {
			System.out.print("\033[H\033[2J");
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
						System.out.println("-----------------------------------------------");
    					System.out.println(" 1 ) View Previouse Prescriptions \n "
    	        		+ "2 ) Add Nurses \n 3 ) View Personal Information \n 4 ) Edit Personal Information \n 5 ) Checkout \n 6 ) Resignate");
    	        
    					Scanner int_input = new Scanner(System.in);
    					boolean accepted = false ;
    					try {
    						while(!accepted){
    							int num = int_input.nextInt();
    							
    							switch(num) {
    							
    							case 1 : 
									System.out.print("\033[H\033[2J");
    								doctor.ViewPreviousePrescriptions();
    								accepted = true ;
    								break;
    							case 2 : 
									System.out.print("\033[H\033[2J");
    								doctor.AddNurse();
    								accepted = true ;
    								break ;
    				   
    							case 3 :
									System.out.print("\033[H\033[2J");
    								doctor.View_personal_info();
    								accepted = true ;
    								break ;
    							case 4 :
									System.out.print("\033[H\033[2J");
    								doctor.Edit_personal_info("doctors");
    								accepted = true ;
    								break ;
    							case 5 :
									System.out.print("\033[H\033[2J");
    								cheBoolean = false;
    								accepted = true ;
    								break ;
    								
    							case 6 :
									System.out.print("\033[H\033[2J");
    								System.out.println("Are You Sure You Want To Resign ? \n 1 ) Yes \t 2 ) No");
    								 
    	         					int num2 = int_input.nextInt();
    	         					boolean accepted2  = false ;
    	         					
    	         					while(!accepted2) {
    	         						switch(num2) {
    	             					case 1 :
    	             						doctor.Resignate("doctors");
    	                 					cheBoolean = false ;
    	                 					accepted2 = true ;
    	             						break;
    	             					case 2 :
    	             						accepted2 = true ;
    	             						break;
    	             					default :
    	             						System.out.println("Wrong Input ");
    	             					
    	             					}
    	         					}
    	         					
    								accepted = true ;
    								break ;   					

    								
    							default :
    								System.out.println("Wrong Input");
    								break ;
    							}
    						  
    						}
    						
    					} catch (InputMismatchException e) {
							System.out.print("\033[H\033[2J");
    						System.out.println("Please enter an integer");
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
    				}
    				

    			}
    	}else {
    		System.out.println("Doctor Not Found !!");
    	}
    		
       
       
    }

    static void Nurse(Nurse nurse) throws InterruptedException{
    	boolean isDone = false ;
    	boolean oneToggle = true ;
		System.out.print("\033[H\033[2J");
		switch (nurse.getStatus()) {
			case "is working":
			while(!isDone) {
				if(oneToggle) {
					nurse.WorkExperienceAdjuster(nurse.getDateGuide());
					nurse.AddSalary();
					System.out.println("Welcome "+nurse.getFirstName()+" "+nurse.getLastName());
					oneToggle = false ;
				}

				System.out.println("-------------------------------------------");
				
				System.out.println(" 1 ) View Personal Information \n 2 ) Edit Personal Information \n 3 ) View Patients Prescriptions \n 4 ) Take Leave \n 5 ) Checkout \n 6 ) Resignate ");
				
				Scanner int_input = new Scanner(System.in);
				boolean accepted = false ;
				while(!accepted){
		
					int num = int_input.nextInt();
					
					switch(num) {
					
					case 1 : 
						System.out.print("\033[H\033[2J");
						nurse.View_personal_info();
						accepted = true ;
						break;
					case 2 : 
						System.out.print("\033[H\033[2J");
						nurse.Edit_personal_info("nurses");
						accepted = true ;
						break;
					case 3 :
						System.out.print("\033[H\033[2J");
						nurse.ViewPatientsPrescriptions();
						accepted = true ;
						break ;
					case 4 :
						System.out.print("\033[H\033[2J");
						if(nurse.TakeLeave()) {
							accepted = true ;
							isDone = true ;
							System.out.println("Have a Good Time See You Next Time ");
							
						}
						else {
							System.out.println("You Dont Have Any Paid Leave Left \n Do You Want to Take an Unpaid Leave Instead ?"
									+ "\n 1 ) Yes       2 ) No ");
							num = int_input.nextInt();
							switch(num) {
							case 1 :
								accepted = true ;
								isDone = true ;
								System.out.println("Have a Good Time See You Next Time ");
								break;
							case 2 : 
								break;
								
							}
							
							
							}
						break;
					case 5 :
						isDone = true ;
						accepted = true ;
						break;
						
					case 6 :
						System.out.print("\033[H\033[2J");
						System.out.println("Are You Sure You Want To Resign ? \n 1 ) Yes \t 2 ) No");
						 
     					int num2 = int_input.nextInt();
     					boolean accepted2  = false ;
     					
     					while(!accepted2) {
     						switch(num2) {
         					case 1 :
         						nurse.Resignate("nurses");
         						isDone = true ;
             					accepted2 = true ;
         						break;
         					case 2 :
         						accepted2 = true ;
         						break;
         					default :
         						System.out.println("Wrong Input ");
         					
         					}
     					}
     					
						accepted = true ;
						break ;   			
						
					default :
						System.out.println("wrong input!!");
					}
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
				// DataBase.Delete("nurses", nurse.getID());
				break;
				
			case "resignated":
				System.out.println("Nurse Not Found !!");
				break;
				
			default:
				System.out.println("?????");
		


    	}
		
        
    }

    static void Personel(Personel personel){
    	
    	boolean isDone = false ;
		System.out.print("\033[H\033[2J");
       	
    	if(!personel.getStatus().equals("is resignated")) {
    		
				personel.AddSalary();
				System.out.println("Welcome "+personel.getFirstName()+" "+personel.getLastName());
			
			while(!isDone) {
			       
	    		System.out.println(" 1 ) View Personal Information \n 2 ) Edit Personal Information \n 3 ) Secretary/Pharmacist Accress \n 4 ) Take Leave \n 5 ) quit \n 6 ) Resignate ");
	    		
	    		Scanner intinput = new Scanner(System.in);
	    		boolean accepted = false ;
	    		while(!accepted) {
	    			int num = intinput.nextInt();
	    			switch(num) {
	    				case 1 :
	    					accepted = true ;
							System.out.print("\033[H\033[2J");
	    					personel.View_personal_info();
	    					break;
	    					
	    				case 2 :
	    					accepted = true ;
							System.out.print("\033[H\033[2J");
	    					personel.Edit_personal_info("personel");
	    					break;
	    				
	    				case 3 :
							accepted = true ;
							System.out.print("\033[H\033[2J");
	    					if(personel.getJob().equals("Secretary")) {
	    						personel.SecretaryAcsess()	;					
	    					}else if(personel.getJob().equals("Pharmacist")){
	    						personel.PharmacistAccess();
	    					}else {
	    						System.out.println("You Can Not Have Access To This Field");
	    					}
	    					break;
	    				case 4 :
							System.out.print("\033[H\033[2J");
	    					if(personel.TakeLeave()) {
	    						accepted = true ;
	    						System.out.println("Have a Good Time See You Next Time ");
	    						isDone = true ;
	    						
	    					}
	    					else {
	    						System.out.println("You Dont Have Any Paid Leave Left \n Do You Want to Take an Unpaid Leave Instead ?"
	    								+ "\n 1 ) Yes       2 ) No ");
	    						num = intinput.nextInt();
	    						switch(num) {
	    						case 1 :
	    							isDone = true ;
	    							accepted = true ;
	    							break;
	    						case 2 : 
	    							accepted = true ;
	    							break;
	    						
	    						default :
	    							System.out.println("Wrong Input");	    				
	    						}
	    						
	    						
	    					}
	    					break;
	    					
	    				case 5 :
	    					isDone = true ;
	    					accepted = true ;
	    					break;
	    					
	    				case 6 :
							System.out.print("\033[H\033[2J");
	    					System.out.println("Are You Sure You Want To Resign ? \n 1 ) Yes \t 2 ) No");
							 
         					int num2 = intinput.nextInt();
         					boolean accepted2  = false ;
         					
         					while(!accepted2) {
         						switch(num2) {
             					case 1 :
             						personel.Resignate("personel");
                 					isDone = true ;
                 					accepted2 = true ;
             						break;
             					case 2 :
             						accepted2 = true ;
             						break;
             					default :
             						System.out.println("Wrong Input ");
             					
             					}
         					}
							accepted = true ;
							break ;   			
	    					
	    				default : 
	    					System.out.println("Wrong Input");
	    					break ;
	    			}
	    		}
			}
   
    	}else {
    		System.out.println("personel Not Found !!");
    	}


    }

    static void Patient(Patient patient){

		System.out.print("\033[H\033[2J");
    	
    	if(!patient.getStatus().equals("is resignated")) {
    		 System.out.println("Welcome "+patient.getFirstName()+" "+patient.getLastName());
				boolean Check = true;
    	        while(Check) {
    				System.out.println("-----------------------------------------------");
    				System.out.println(" 1 ) Visit \n 2 ) View Personal Information \n 3 ) Edit Personal Information \n 4 ) Rate Visits \n 5 ) Delete Account 6 ) quit");
    				Scanner intinput = new Scanner(System.in);
    				int num = intinput.nextInt();
    				switch(num) {
    					case 1 :
							System.out.print("\033[H\033[2J");
    						patient.VisitDoctor();
    						break;
    						
    					case 2 :
							System.out.print("\033[H\033[2J");
    						patient.View_personal_info();
    						break;
    						
    					case 3 :
							System.out.print("\033[H\033[2J");
    						patient.Edit_personal_info("patients");
    						break;
    						
    					case 4 :
							System.out.print("\033[H\033[2J");
    						patient.RateVisit();
    						break;
    					case 5:
							System.out.print("\033[H\033[2J");
							patient.Resignate("patients");
							System.out.println("Acount Deleted Succesfully");
							Check = false;
							break;
						case 6:
							Check = false;
							break;
    					default : 
    						System.out.println("wrong input");
    				}
    	        }
    	        
    	}else {
    		System.out.println("Patient Not Found !!");
    	}

    }
    
}
