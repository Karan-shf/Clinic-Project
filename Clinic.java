import java.util.Scanner;

class Clinic {

    static void Welcome(){
        System.out.println("WELCOME!");
    }

    static void ShowMenu(){

        Scanner int_input = new Scanner(System.in);
     
        System.out.println("Please Choose Your Entry");
        System.out.println("1.Admin");
        System.out.println("2.Doctor");
        System.out.println("3.Nurse");
        System.out.println("4.Clinic Personel");
        System.out.println("5.Patient");

        int EnteryNum = int_input.nextInt();

        switch (EnteryNum) {
            case 1:
                Login.Admin();
                break;
            case 2:
                // Insert.InsertDoc();
                // Login.SignInDoc(Insert.Doctors);
                Login.Doctor();
                break;
            case 3:
                // Insert.InsertNurses();
                // Login.SignInNur(Insert.Nurses);
                Login.Nurse();
                break;
            case 4:
                Login.Personel();
                break;
            case 5:
                // System.out.println("Do You Already have an Account?");
                System.out.println("1.Login");
                System.out.println("2.Sign Up");
                int patientEntry = int_input.nextInt();
                switch (patientEntry) {
                    case 1:
                        // Insert.InsertPatients();
                        // Login.SignInPat(Insert.Patients);
                        Login.Patient();
                        break;
                    case 2:
                        Patient.registery();
                        break;
                    default:
                        System.out.println("Wrong Input!");
                }
                break;
            default:
                System.out.println("Wrong Input!");
        }
        
    }
    
}
