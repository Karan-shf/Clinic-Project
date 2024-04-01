

public abstract class Dashboard {
    
    static void Admin(Admin admin){
        System.out.println("WELCOME "+admin.getFirstName()+" "+admin.getLastName());
    }

    static void Doctor(Doctor doctor){
        System.out.println("Welcome "+doctor.getFirstName()+" "+doctor.getLastName());
    }

    static void Nurse(Nurse nurse){
        System.out.println("Welcome "+nurse.getFirstName()+" "+nurse.getLastName());
    }

    static void Personel(Personel personel){
        System.out.println("Welcome "+personel.getFirstName()+" "+personel.getLastName());
    }

    static void Patient(Patient patient){
        System.out.println("Welcome "+patient.getFirstName()+" "+patient.getLastname());
    }
}
