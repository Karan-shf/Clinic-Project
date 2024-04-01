import java.sql.Date;

class Visit {

    private int ID;
    private Date date;
    private String prescription;
    private int PatientID;
    private int DoctorID;
    private int price;
    private boolean isRated ;

    public Visit(int iD, Date date, String prescription, int patientID, int doctorID, int price , boolean isRated ) {
        ID = iD;
        this.date = date;
        this.prescription = prescription;
        PatientID = patientID;
        DoctorID = doctorID;
        this.price = price;
        this.isRated = isRated;
        
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
    
    public String toString() {
    	Patient patient = Insert.Extract_Patient(PatientID);
    	Doctor doctor = Insert.Extract_Doctor(DoctorID);
    	
    	return "Date : " + date + "\n Visit ID : " + ID + "Patient's Name : " + patient.getFirstName() + " " + patient.getLastname()+
    			"\t Doctor's Name : " + doctor.getFirstName() + " " + doctor.getLastName()
    			+ "\n Prescripton : " + prescription;
    }
    
    
    
    
}
