import java.sql.Date;

class Visit {

    private int ID;
    private Date date;
    private String prescription;
    private int PatientID;
    private int DoctorID;
    private int price;
    private boolean isRated ;
    private int NurseID ;
    
    public Visit(int iD, Date date, String prescription, int patientID, int doctorID, int price , boolean isRated , int NurseID ) {
        ID = iD;
        this.date = date;
        this.prescription = prescription;
        PatientID = patientID;
        DoctorID = doctorID;
        this.price = price;
        this.isRated = isRated;
        this.NurseID = NurseID ;
        
    }
    
    public boolean GetIsRated() {
    	return this .isRated;
    }
    
    public void SetIsRated(boolean IsRated) {
    	this.isRated = IsRated;
    }

    public int getID() {
        return ID;
    }

    public Date getDate() {
        return date;
    }

    public String getPrescription() {
        return prescription;
    }

    public int getPatientID() {
        return PatientID;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public int getPrice() {
        return price;
    } 
    
    public int getNurseID() {
    	return NurseID;
    }

    public void ShowVisit(){
        Patient patient = DataBase.Extract_Patient(this.getPatientID());
    	Doctor doctor = DataBase.Extract_Doctor(this.getDoctorID());
        String [] prescriptionss = prescription.split(",");

        System.out.println("Visit ID: "+this.getID());
        System.out.println("Date: "+this.getDate());
        System.out.println("Patient's Name: "+patient.getFirstName()+" "+ patient.getLastName());
        System.out.println("Doctor's Name: "+doctor.getFirstName()+" "+doctor.getLastName());
        System.out.println("Prescripton:");
        for (int i=1;i<=prescriptionss.length;i++) {
            System.out.println(""+i+". "+prescriptionss[i-1]);
        }
    }
    
}
