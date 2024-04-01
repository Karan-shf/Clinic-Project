import java.util.InputMismatchException;
import java.util.Scanner;

class Clinic {

    static void Welcome(){

		System.out.print("\033[H\033[2J");

		System.out.println(" ______ _      ____       _______    _____ _      _____ _   _ _____ _____ ");
		System.out.println("|  ____| |    / __ \\   /\\|__   __|  / ____| |    |_   _| \\ | |_   _/ ____|");
		System.out.println("| |__  | |   | |  | | /  \\  | |    | |    | |      | | |  \\| | | || |     ");
		System.out.println("|  __| | |   | |  | |/ /\\ \\ | |    | |    | |      | | | . ` | | || |     ");
		System.out.println("| |    | |___| |__| / ____ \\| |    | |____| |____ _| |_| |\\  |_| || |____ ");
		System.out.println("|_|    |______\\____/_/    \\_\\_|     \\_____|______|_____|_| \\_|_____\\_____|\n");

    }

    static void ShowMenu(){

    	boolean accepted = false;

    	boolean accepted2 = false;
    	
		
    	while(!accepted) {
			
			System.out.println("=======================================");
			System.out.println("Please Choose Your Entry");
			System.out.println("1.Admin");
			System.out.println("2.Doctor");
			System.out.println("3.Nurse");
			System.out.println("4.Clinic Personel");
			System.out.println("5.Patient");

    		try {

    			Scanner int_input = new Scanner(System.in);

    	        int EnteryNum = int_input.nextInt();

    	        switch (EnteryNum) {
    	            case 1:
    	            	accepted = true;
						System.out.print("\033[H\033[2J");
    	                Login.Admin();
    	                break;
    	            case 2:
    	            	accepted = true;
						System.out.print("\033[H\033[2J");
    	                Login.Doctor();
    	                break;
    	            case 3:
    	            	accepted = true;
						System.out.print("\033[H\033[2J");
    	                Login.Nurse();
    	                break;
    	            case 4:
						accepted = true;
						System.out.print("\033[H\033[2J");
						Login.Personel();
    	                break;
    	            case 5:
    	            	
    	                System.out.println("1.Login");
    	                System.out.println("2.Sign Up");
    	                
    	                while(!accepted2) {
							int patientEntry = int_input.nextInt();
							switch (patientEntry) {
								case 1:
									accepted2 = true;
									System.out.print("\033[H\033[2J");
									Login.Patient();
									break;
								case 2:
									accepted2 = true;
									System.out.print("\033[H\033[2J");
									Patient.registery();
									break;
								default:
									System.out.println("Wrong Input!");
							}
    	                }
    	                accepted = true;
    	                break;
    	            default:
    	                System.out.println("Wrong Input!");
    	        }
        	}
        	catch(InputMismatchException e) {
				System.out.print("\033[H\033[2J");
        		System.out.println("Please Enter an Integer ");
        	} catch (Exception e) {
				e.printStackTrace();
			}
    	        
    	}

		GoodBye();

    }

	static void GoodBye(){
		System.out.println("THANKS FOR USING OUR CLINIC :)");
		System.out.println("HOPE WE MADE GOOD USE OF YOUR TIME!");
	}

}